using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    /// <summary>
    /// View model for the GetMsg view which displays a chosen message to the users and also displays other messages from the same sender.
    /// </summary>
    public class GetMsgViewModel
    {
        public string Topic { get; set; }
        public string Msg { get; set; }
        public DateTime DateTime { get; set; }
        public int MessageId { get; set; }
        public UsermessagesViewModel UserMessagesViewModel { get; set; }
        public string SenderId { get; set; }
    }
}
