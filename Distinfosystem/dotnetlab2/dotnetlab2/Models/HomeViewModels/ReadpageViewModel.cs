using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    /// <summary>
    /// used to display users that has sent messages to the user and also information about all messages.
    /// </summary>
    public class ReadpageViewModel
    {
        public string UserID { get; set; }
        public List<UserInfo> Users { get; set; }
        public int totalNumberMessages { get; set; }
        public int totalNumberRead { get; set; }
        public int totalNumberRemoved { get; set; }
    }
}
