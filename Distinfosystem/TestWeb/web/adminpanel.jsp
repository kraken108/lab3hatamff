<%-- 
    Document   : adminpanel
    Created on : 2017-sep-28, 12:15:39
    Author     : Jakob
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Facade.UserController"%>
<%@page import="BO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administration panel</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                out.println("You are not logged in.");
            } else if (!user.isAdministrator()) {
                out.println("Insufficient rights.");
            } else {
                out.println("Welcome to the administration panel!");
                
                
        %>
        <h4>Users:</h4>

        <%
                UserController uc = new UserController();
                try {
                    ArrayList<User> users = uc.getUsers();
                    if (users.isEmpty()) {
                        out.println("No users found :<");
                    }else{
                        for(User u : users){
                            out.println(u.getName());
                            
                            
        %>
        <form action="edituser.jsp">
            <input type="hidden" name="userToEdit" value="<%out.print(u.getName());%>">
            <input type="submit" value="Edit">
        </form>
        <br>
        <%
                        }
                    }
                } catch (Exception e) {
                    out.println(e);
                }

            }
        %>
        <form action="main.jsp">
            <input type="submit" value="Back to shop">
        </form>

    </body>
</html>
