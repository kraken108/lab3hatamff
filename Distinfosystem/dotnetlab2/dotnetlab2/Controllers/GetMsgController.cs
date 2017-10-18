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
    public class GetMsgController : Controller
    {
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly SignInManager<ApplicationUser> _signInManager;
        private readonly ApplicationDbContext _context;

        public GetMsgController(UserManager<ApplicationUser> userManager,
         SignInManager<ApplicationUser> signInManager, ApplicationDbContext context)
        {
            _context = context;
            _userManager = userManager;
            _signInManager = signInManager;
        }

        [HttpPost]
        public async Task<IActionResult> GetMsg(GetMsgViewModel model, string SenderId)
        {
            _context.Messages.Load();
            _userManager.Users.Load();

            try
            {
                var message = (from m in _context.Messages where m.ID == model.MessageId select m).FirstOrDefault();
                message.IsRemoved = true;
                int num = _context.SaveChanges();
            }
            catch (Exception e)
            {
                return RedirectToAction(nameof(UsermessagesController.Usermessages), "Usermessages", new { userID = SenderId, StatusMessage = "Failed to remove message" });
            }

            return RedirectToAction(nameof(UsermessagesController.Usermessages), "Usermessages", new { userID = SenderId, StatusMessage = "Successfully removed message!" });
        }



        [HttpGet]
        public async Task<IActionResult> GetMsg(string modelUsername, int? messageID)
        {
            _userManager.Users.Load();

            if (messageID == null)
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

                if (m.ID == messageID)
                {
                    var otherMessages = getOtherMessages(m.ID, m.Sender.Id);
                    var model = new GetMsgViewModel
                    {
                        UserMessagesViewModel = await otherMessages,
                        Topic = m.Topic,
                        Msg = m.Msg,
                        DateTime = m.DateTime,
                        MessageId = m.ID,
                        SenderId = m.Sender.Id
                    };
                    return View(model);
                }
            }

            throw new Exception("Finns inget meddelande med det ID:t ?????");

        }

        private async Task<UsermessagesViewModel> getOtherMessages(int excludingMessageId, string userID)
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

    }
}
