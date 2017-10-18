using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    /// <summary>
    /// Displays information about the user for the index page.
    /// </summary>
    public class IndexViewModel
    {
        public string Username { get; set; }
        public DateTime LastLogin { get; set; }
        public int LoginsThisMonth { get; set; }
        public int NumberUnreadMessages { get; set; }
    }
}
