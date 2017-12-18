using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    /// <summary>
    /// Used to display information about a user using strings etc.
    /// </summary>
    public class UserInfo
    {
        public string Username { get; set; }
        public string UserId { get; set; }
        public int NumberUnreadMessages { get; set; }
    }
}
