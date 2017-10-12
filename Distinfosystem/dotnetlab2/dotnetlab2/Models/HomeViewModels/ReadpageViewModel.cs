using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    public class ReadpageViewModel
    {
        public int MessageId { get; set; }
        public List<MessageInfo> Messages { get; set; }
    }
}
