﻿using System;
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

            var model = new IndexViewModel
            {
                Username = user.UserName
            };

            return View(model);
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

            var messages = _context.Messages.ToList();
            List<MessageInfo> messageList = new List<MessageInfo>();

            foreach(Message m in messages)
            {
                var mi = new MessageInfo();
                mi.Message = m.Msg;
                mi.Topic = m.Topic;
                mi.SenderName = m.Sender.UserName;
                if(m.Receiver == null)
                {
                    throw new Exception("Sender: " + mi.SenderName + " " + mi.ReceiverName + " Varför är den null?");
                }
                mi.ReceiverName = m.Receiver.UserName;
                messageList.Add(mi);
            }

            var model = new ReadpageViewModel();
            model.Messages = messageList;

            return View(model);
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
                    var receiver = await _userManager.FindByIdAsync(model.UserId);
                    message.Receiver = receiver;
                    await _context.Messages.AddAsync(message);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Writepage));
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

        [HttpGet]
        public async Task<IActionResult> Writepage()
        {

            var user = await _userManager.GetUserAsync(User);
            if (user == null)
            {
                throw new ApplicationException($"Unable to load user with ID '{_userManager.GetUserId(User)}'.");
            }


            var users = _userManager.Users.ToList();
            List<UserInfo> usernameList = new List<UserInfo>();

            foreach(ApplicationUser au in users)
            {
                var u = new UserInfo();
                u.Username = au.UserName;
                u.UserId = au.Id;
                usernameList.Add(u);
            }
            var model = new WritepageViewModel();
            model.Users = usernameList;

            ViewData["Title"] = "Your write page.";

            return View(model);
        }

        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
