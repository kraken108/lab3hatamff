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
    /// <summary>
    /// UsermessagesController controls the requests and responses from the Usermessages view.
    /// </summary>
    [Authorize]
    public class UsermessagesController : Controller
    {
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly SignInManager<ApplicationUser> _signInManager;
        private readonly ApplicationDbContext _context;

        /// <summary>
        /// Constructor that creates database contexts for communication with the database.
        /// </summary>
        /// <param name="userManager"></param>
        /// <param name="signInManager"></param>
        /// <param name="context"></param>
        public UsermessagesController(UserManager<ApplicationUser> userManager,
         SignInManager<ApplicationUser> signInManager, ApplicationDbContext context)
        {
            _context = context;
            _userManager = userManager;
            _signInManager = signInManager;
        }

        /// <summary>
        /// Takes a userID and a StatusMessage as parameters and returns a view model with a list of messages from the user with the userID.
        /// </summary>
        /// <param name="userID">The userID of the sender of the messages</param>
        /// <param name="StatusMessage">Statusmessage to display on top of page</param>
        /// <returns></returns>
        public async Task<IActionResult> Usermessages(string userID, string StatusMessage)
        {
            if (StatusMessage == null)
            {

            }
            else
            {
                ViewData["StatusMessage"] = StatusMessage;
            }
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

            var u = await _userManager.FindByIdAsync(userID);
            model.Username = u.UserName;

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
                //model.Username = m.Sender.UserName;
                messageList.Add(mi);
            }

            model.Messages = messageList;

            return View(model);
        }
    }
}
