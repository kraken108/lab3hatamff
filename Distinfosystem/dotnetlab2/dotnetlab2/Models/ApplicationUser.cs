using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;

namespace dotnetlab2.Models
{
    // Add profile data for application users by adding properties to the ApplicationUser class
   

    public class ApplicationUser : IdentityUser
    {
        // public virtual ICollection<Message> Messages { get; set; }
        // Tror inte vi behöver messages här, vi kan söka i messages efter namn bara
        // Så när vi ska hämta alla meddelanden för en användare så kör vi en get messages where username == etc
        // Man kan göra det rätt enkelt i en controllerklass
       
    public ApplicationUser()
        {
          //  Messages = new List<Message>();
        }


    }
}
