using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using dotnetlab2.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using dotnetlab2.Models.HomeViewModels;
using dotnetlab2.Data;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;

namespace dotnetlab2.Controllers
{

    [Authorize]

    public class HomeController : Controller
    {
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly SignInManager<ApplicationUser> _signInManager;
        private readonly ApplicationDbContext _context;

        public HomeController(UserManager<ApplicationUser> userManager,
          SignInManager<ApplicationUser> signInManager,ApplicationDbContext context)
        {
            _context = context;
            _userManager = userManager;
            _signInManager = signInManager;
        }

        [HttpGet]
        public async Task<IActionResult> Index()
        {
            var user = await _userManager.GetUserAsync(User);
            if (user == null)
            {
                throw new ApplicationException($"Unable to load user with ID '{_userManager.GetUserId(User)}'.");
            }

            //get login information
            var logins = from s in _context.Logins select s;
            
            logins = logins.Where(s => s.ApplicationUser.Id == user.Id);
            var oneMonthAgo = DateTime.Now.AddMonths(-1);
            logins = logins.Where(s => s.DateTime > oneMonthAgo);
            var loginsThisMonth = logins.Count();

            //Get number of unread messages
            var messages = from s in _context.Messages select s;
            messages = messages.Where(s => s.Receiver.Id == user.Id);
            messages = messages.Where(s => s.IsRead == false);
            int numberUnreadMessages = messages.Count();

            if (logins.Any())
            {
                var model = new IndexViewModel
                {
                    Username = user.UserName,
                    LastLogin = logins.Last().DateTime,
                    LoginsThisMonth = loginsThisMonth,
                    NumberUnreadMessages = numberUnreadMessages
                };
                return View(model);
            }
            else
            {
                var model = new IndexViewModel
                {
                    Username = user.UserName,
                    LastLogin = DateTime.Now,
                    LoginsThisMonth = 1,
                    NumberUnreadMessages = numberUnreadMessages
                };
                return View(model);
            }           

            
        }


        public IActionResult About()
        {
            ViewData["Message"] = ".NET Laboration i kursen Distribuerade Informationssystem";
            ViewData["Text"] = "Av Jakob Danielsson & Michael Hjälmö";
            return View();
        }

        public IActionResult Contact()
        {
            ViewData["Message"] = "Your contact page.";

            return View();
        }

        [HttpGet]
        public async Task<IActionResult> Readpage()
        {
            ViewData["Message"] = "Your read page.";
            var user = await _userManager.GetUserAsync(User);
            if (user == null)
            {
                throw new ApplicationException($"Unable to load user with ID '{_userManager.GetUserId(User)}'.");
            }


            await _userManager.Users.LoadAsync();
            await _context.Messages.LoadAsync();
            var messages = from s in _context.Messages select s;
            //messages = messages.Where(m => m.IsRead == false); 
            messages = messages.Where(M => M.Receiver.Id == user.Id);
            messages = messages.Where(m => !m.IsRemoved);
            messages = messages.OrderBy(s => s.Sender);
            


            List<UserInfo> usersList = new List<UserInfo>();


            string previousMessageUser = "blablabla";

            foreach (var m in messages)
            {
                if (m.Sender.Id != previousMessageUser)
                {
                    var userSpecificMessages = from s in _context.Messages select s;
                    userSpecificMessages = userSpecificMessages.Where(s => s.Sender.Id == m.Sender.Id);
                    userSpecificMessages = userSpecificMessages.Where(s => s.Receiver.Id == user.Id);
                    userSpecificMessages = userSpecificMessages.Where(s => s.IsRead == false);
                    userSpecificMessages = userSpecificMessages.Where(s => s.IsRemoved == false);
                    var numberUnreadMessages = userSpecificMessages.Count();

                    var ui = new UserInfo
                    {
                        Username = m.Sender.UserName,
                        UserId = m.Sender.Id,
                        NumberUnreadMessages = numberUnreadMessages
                    };
                    usersList.Add(ui);
                }
                
                previousMessageUser = m.Sender.Id;
            }

            var model = new ReadpageViewModel();
            model.Users = usersList;

            var messages2 = from s in _context.Messages select s;
            messages2 = messages2.Where(ms => ms.Receiver.Id == user.Id);
            var messages3 = messages2.Where(ms => !ms.IsRemoved);
            var removedMessages = messages2.Where(ms => ms.IsRemoved);
            var readMessages = messages2.Where(ms => ms.IsRead);
            readMessages = readMessages.Where(ms => !ms.IsRemoved);
            var numberOfRead = readMessages.Count();
            var numberOfRemoved = removedMessages.Count();

            model.totalNumberRead = numberOfRead;
            model.totalNumberRemoved = numberOfRemoved;
            model.totalNumberMessages = messages3.Count();

            return View(model);
        }


        //en metod i kontrollern som tar emot ett meddelandeID, och returnerar info om det meddelandet 



        [HttpPost]
        public async Task<IActionResult> GetMsg(GetMsgViewModel model)
        {
            _context.Messages.Load();
            _userManager.Users.Load();
            throw new Exception("id is: " + model.senderId);
            
            try
            {
                var message = (from m in _context.Messages where m.ID == model.MessageId select m).FirstOrDefault();
                message.IsRemoved = true;
                int num = _context.SaveChanges();
            }catch(Exception e)
            {
                ViewData["StatusMessage"] = "Failed to remove message";
                return RedirectToAction(nameof(Usermessages));
            }
            

            ViewData["StatusMessage"] = "Successfully removed message!";

            string s = model.senderId;
            throw new Exception("id is: " + s);
           return RedirectToAction(nameof(Usermessages),new { userID = s});
        }



        [HttpGet]
        public async Task<IActionResult> GetMsg(string modelUsername, int? messageID)
        {
            //throw new Exception(modelUsername);
            _userManager.Users.Load();

            if(messageID == null)
            {
                throw new Exception("No message ID passed");
            }
            var messages = from s in _context.Messages select s;
            // throw new Exception(messageID.ToString());
            

            try
            {
                var message = (from m in _context.Messages where m.ID == messageID select m).FirstOrDefault();
                message.IsRead = true;
                int num = _context.SaveChanges();
            }
            catch (Exception e)
            {
                throw new Exception(e.ToString());
            }

            foreach (Message m in messages)
            {

                if(m.ID == messageID)
                {
                    var otherMessages = getOtherMessages(m.ID, m.Sender.Id);
                    var model = new GetMsgViewModel
                    {
                        UserMessagesViewModel = await otherMessages,
                        Topic = m.Topic,
                        Msg = m.Msg,
                        dateTime = m.DateTime,
                        MessageId = m.ID,
                        senderId = m.Sender.Id
                    };
                    return View(model);
                }
            }

            throw new Exception("Finns inget meddelande med det ID:t ?????");
            
        }           
        
        private async Task<UsermessagesViewModel> getOtherMessages(int excludingMessageId,string userID)
        {

            var user = await _userManager.GetUserAsync(User);
            if (user == null)
            {
                throw new ApplicationException($"Unable to load user with ID '{_userManager.GetUserId(User)}'.");
            }

            _context.Messages.Load();
            _context.Users.Load();
            var messages = _context.Messages.Where(m => m.Sender.Id == userID);
            messages = messages.Where(m => m.Receiver.Id == user.Id);
            messages = messages.Where(m => !m.IsRemoved);
            messages = messages.Where(m => m.ID != excludingMessageId);

            var messageList = new List<MessageInfo>();
            var model = new UsermessagesViewModel();

            foreach (var m in messages)
            {
                var mi = new MessageInfo();
                mi.Topic = m.Topic;
                mi.DateTime = m.DateTime;
                mi.SenderID = m.Sender.Id;
                mi.SenderName = m.Sender.UserName;
                mi.MessageId = m.ID;
                if (m.IsRead)
                {
                    mi.isRead = "";
                }
                else
                {
                    mi.isRead = "(Oläst)";
                }
                model.Username = m.Sender.UserName;
                messageList.Add(mi);
            }

            model.Messages = messageList;

            return model;
        }




        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Writepage(WritepageViewModel model)
        {

            try
            {
                var user = await _userManager.GetUserAsync(User);
                if (user == null)
                {
                    throw new ApplicationException($"Unable to load user with ID '{_userManager.GetUserId(User)}'.");
                }

                if (ModelState.IsValid)
                {
                    var message = new Message();
                    message.Sender = user;
                    message.Msg = model.Message;
                    message.Topic = model.Topic;
                    message.DateTime = DateTime.Now;
                    var receiver = await _userManager.FindByIdAsync(model.UserId);
                    message.Receiver = receiver;
                    await _context.Messages.AddAsync(message);
                    await _context.SaveChangesAsync();
                    ViewData["StatusMessage"] = "Meddelande nummer " + _context.Messages.Last().ID + " avsänt till " + receiver.UserName + ", " + _context.Messages.Last().DateTime;
                    ModelState.Clear();
                    return View(constructWritepageViewModel());
                }
                else
                {
                    throw new Exception("Inte valid");
                }

            }

            catch (Exception ex)
            {
                throw new ApplicationException($"Error: '{ex.ToString()}'.");
            }
        }

        private WritepageViewModel constructWritepageViewModel()
        {
            var users = _userManager.Users.ToList();
            List<UserInfo> usernameList = new List<UserInfo>();

            foreach (ApplicationUser au in users)
            {
                var u = new UserInfo();
                u.Username = au.UserName;
                u.UserId = au.Id;
                usernameList.Add(u);
            }
            var model = new WritepageViewModel();
            model.Users = usernameList;

            return model;
        }

        [HttpGet]
        public async Task<IActionResult> Writepage()
        {

            var user = await _userManager.GetUserAsync(User);
            if (user == null)
            {
                throw new ApplicationException($"Unable to load user with ID '{_userManager.GetUserId(User)}'.");
            }

            ViewData["Title"] = "Your write page.";

            return View(constructWritepageViewModel());
        }


        public async Task<IActionResult> Usermessages(string userID)
        {

            /*if(userID == null)
            {
                throw new Exception("bajsjsjsjs");
                return NotFound();
            }*/
            var user = await _userManager.GetUserAsync(User);
            if (user == null)
            {
                throw new ApplicationException($"Unable to load user with ID '{_userManager.GetUserId(User)}'.");
            }

            _context.Messages.Load();
            _context.Users.Load();
            var messages = _context.Messages.Where(m => m.Sender.Id == userID);
            messages = messages.Where(m => m.Receiver.Id == user.Id);
            messages = messages.Where(m => !m.IsRemoved);

            var messageList = new List<MessageInfo>();
            var model = new UsermessagesViewModel();

            foreach (var m in messages)
            {
                var mi = new MessageInfo();
                mi.Topic = m.Topic;
                mi.DateTime = m.DateTime;
                mi.SenderID = m.Sender.Id;
                mi.SenderName = m.Sender.UserName;
                mi.MessageId = m.ID;
                if (m.IsRead)
                {
                    mi.isRead = "";
                }
                else
                {
                    mi.isRead = "(Oläst)";
                }
                model.Username = m.Sender.UserName;
                messageList.Add(mi);
            }

            model.Messages = messageList;

            return View(model);
        }

        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
