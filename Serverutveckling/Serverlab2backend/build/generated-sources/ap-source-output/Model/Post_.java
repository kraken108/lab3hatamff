package Model;

import Model.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-14T13:05:04")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, String> date;
    public static volatile SingularAttribute<Post, Long> id;
    public static volatile SingularAttribute<Post, String> message;
    public static volatile SingularAttribute<Post, User> user;

}