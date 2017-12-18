package Model;

import Model.Message;
import Model.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-14T13:05:04")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Message> receivedMessages;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile ListAttribute<User, Message> sentMessages;
    public static volatile ListAttribute<User, Post> posts;
    public static volatile SingularAttribute<User, String> username;

}