using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    public class GetMsgViewModel
    {
        public string Topic { get; set; }
        public string Msg { get; set; }
        public DateTime dateTime { get; set; }
    }
}
