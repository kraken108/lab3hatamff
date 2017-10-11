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

namespace dotnetlab2.Controllers
{

    [Authorize]
    public class HomeController : Controller
    {
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly SignInManager<ApplicationUser> _signInManager;

        public HomeController(UserManager<ApplicationUser> userManager,
          SignInManager<ApplicationUser> signInManager)
        {
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

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Writepage(WritepageViewModel model)
        {


            var user = await _userManager.GetUserAsync(User);
            if (user == null)
            {
                throw new ApplicationException($"Unable to load user with ID '{_userManager.GetUserId(User)}'.");
            }

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
        public IActionResult Readpage()
        {
            ViewData["Message"] = "Your read page.";

            return View();
        }

        public IActionResult Writepage()
        {
            ViewData["Message"] = "Your write page.";

            return View();
        }

        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
