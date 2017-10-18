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
    public class ReadpageController : Controller
    {
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly SignInManager<ApplicationUser> _signInManager;
        private readonly ApplicationDbContext _context;

        public ReadpageController(UserManager<ApplicationUser> userManager,
         SignInManager<ApplicationUser> signInManager, ApplicationDbContext context)
        {
            _context = context;
            _userManager = userManager;
            _signInManager = signInManager;
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
            messages = messages.Where(M => M.Receiver.Id == user.Id);
            messages = messages.Where(m => !m.IsRemoved);
            messages = messages.OrderBy(s => s.Sender);



            List<UserInfo> usersList = new List<UserInfo>();


            string previousMessageUser = "";

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
    }
}
