using Microsoft.AspNetCore.Mvc.Rendering;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    public class WritepageViewModel
    {
        public string Message { get; set; }
        public string Topic { get; set; }
        public int UserId { get; set; }
        public List<UserInfo> Users { get; set; }
    }
}
