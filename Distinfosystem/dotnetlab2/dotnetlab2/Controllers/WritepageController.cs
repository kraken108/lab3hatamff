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
    /// WritepageController controls the requests and responses from the Writepage view.
    /// Used to create and send messages to users.
    /// </summary>
    [Authorize]
    public class WritepageController : Controller
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
        public WritepageController(UserManager<ApplicationUser> userManager,
         SignInManager<ApplicationUser> signInManager, ApplicationDbContext context)
        {
            _context = context;
            _userManager = userManager;
            _signInManager = signInManager;
        }

        /// <summary>
        /// Writepage post method, is called when a message is sent. Takes a view model with data such as topic, message and receiver of message.
        /// Creates a message and saves it to the database.
        /// </summary>
        /// <param name="model">The model containing data to create a message</param>
        /// <returns>Status message if it succeeded or not.</returns>
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

        /// <summary>
        /// Constructs a view model containing a list of available users that the message can be sent to.
        /// </summary>
        /// <returns>A list of available users.</returns>
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

        /// <summary>
        /// Returns the writepage view model containing available users to send a message to.
        /// </summary>
        /// <returns></returns>
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
    }
}
