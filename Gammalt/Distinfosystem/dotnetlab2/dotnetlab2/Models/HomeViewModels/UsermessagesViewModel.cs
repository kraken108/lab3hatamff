using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    public class UsermessagesViewModel
    {
        public string Username { get; set; }
        public List<MessageInfo> Messages { get; set; }
    }
}
