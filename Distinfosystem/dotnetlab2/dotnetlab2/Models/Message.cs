using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;


namespace dotnetlab2.Models
{
    public class Message
    {   
        public ApplicationUser Sender { get; set; }
        public ApplicationUser Receiver { get; set; }
        public Boolean Read { get; set; }
        public Boolean Removed { get; set; }
        public string Msg { get; set; }

        //navigation property
        public virtual ApplicationUser User { get; set; }

        public Message(string Message)
        {
            Read = false;
            Removed = false;
        }   


    }

}