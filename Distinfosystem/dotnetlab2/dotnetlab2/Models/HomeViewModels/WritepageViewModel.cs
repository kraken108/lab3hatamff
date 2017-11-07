using Microsoft.AspNetCore.Mvc.Rendering;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{

    /// <summary>
    /// used to display users that can receive messages, constructs a message with a topic.  
    /// </summary>
    public class WritepageViewModel
    {
        public string Message { get; set; }
        public string Topic { get; set; }
        public string UserId { get; set; }
        public List<UserInfo> Users { get; set; }
    }
}
