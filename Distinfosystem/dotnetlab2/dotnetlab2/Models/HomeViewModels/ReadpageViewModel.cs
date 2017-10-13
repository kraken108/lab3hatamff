using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models.HomeViewModels
{
    public class ReadpageViewModel
    {
        public string UserID { get; set; }
        public List<UserInfo> Users { get; set; }
        public int totalNumberMessages { get; set; }
        public int totalNumberRead { get; set; }
        public int totalNumberRemoved { get; set; }
    }
}
