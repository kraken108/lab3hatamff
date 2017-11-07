using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    /// <summary>
    /// Used to save messages in lists etc using strings etc.
    /// </summary>
    public class MessageInfo
    {
        public int MessageId { get; set; }
        public string SenderID { get; set; }
        public string Topic { get; set; }
        public string SenderName { get; set; }
        public DateTime DateTime { get; set; }
        public string isRead { get; set; }
    }
}
