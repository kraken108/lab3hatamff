using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace dotnetlab2.Models
{
    public class Login
    {
        public int ID { get; set; }
        public DateTime DateTime { get; set; }
        public ApplicationUser ApplicationUser { get; set; }
    }
}
