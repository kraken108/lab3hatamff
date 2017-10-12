using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;


namespace dotnetlab2.Models
{
    public class Message
    {   
        public int ID { get; set; }
        public virtual ApplicationUser Sender { get; set; }
        public virtual ApplicationUser Receiver { get; set; }
        public Boolean IsRead { get; set; }
        public Boolean IsRemoved { get; set; }
        public string Msg { get; set; }
        public string Topic { get; set; }
        //navigation property
        public virtual ApplicationUser User { get; set; }

        public Message()//(string Message)
        {
            IsRead = false;
            IsRemoved = false;
        }   


    }
}