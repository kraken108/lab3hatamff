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
    public class UsermessagesController : Controller
    {
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly SignInManager<ApplicationUser> _signInManager;
        private readonly ApplicationDbContext _context;

        public UsermessagesController(UserManager<ApplicationUser> userManager,
         SignInManager<ApplicationUser> signInManager, ApplicationDbContext context)
        {
            _context = context;
            _userManager = userManager;
            _signInManager = signInManager;
        }


        public async Task<IActionResult> Usermessages(string userID, string StatusMessage)
        {
            if (StatusMessage == null)
            {

            }
            else
            {
                ViewData["StatusMessage"] = StatusMessage;
            }

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
                messageList.Add(mi);
            }

            model.Messages = messageList;

            return View(model);
        }
    }
}
