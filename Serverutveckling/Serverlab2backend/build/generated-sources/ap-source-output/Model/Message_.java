package Model;

import Model.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-14T13:05:04")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, String> date;
    public static volatile SingularAttribute<Message, User> receiver;
    public static volatile SingularAttribute<Message, User> sender;
    public static volatile SingularAttribute<Message, String> topic;
    public static volatile SingularAttribute<Message, Long> id;
    public static volatile SingularAttribute<Message, String> message;

}