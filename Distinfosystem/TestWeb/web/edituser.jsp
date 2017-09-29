<%-- 
    Document   : edituser
    Created on : 2017-sep-28, 13:11:16
    Author     : Jakob
--%>

<%@page import="Facade.UserController"%>
<%@page import="BO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit user</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                out.println("You are not logged in.");
            } else if (!user.isAdministrator()) {
                out.println("Insufficient rights.");
            } else {
                out.println("Edit user: ");
                String userToEdit = request.getParameter("userToEdit");
                
                UserController uc = new UserController();
                User userToEditInfo = null;
                User userEdit = null;
                if (userToEdit == null) {
                     userToEditInfo = (User)session.getAttribute("userToEdit");
                } else {
                    try{
                        userToEditInfo = uc.getSingleUser(userToEdit);
                    }catch(Exception e){
                        session.setAttribute("error", "Error retrieving user to edit");
                        String redirectURL = "http://localhost:8080/Webbshop/error.jsp";
                        response.sendRedirect(redirectURL);
                    }
                    session.setAttribute("userToEdit", userToEditInfo);
                }
                
                session.setAttribute("userToEdit", userToEditInfo);
                out.println(userToEditInfo.getName());
                
        %>
        <br>
        <%
            out.println("User rights: " + userToEditInfo.getRightsString());
        %>
        <br><br>
        
        <form method="post" action="change-password.jsp">
            <input type="text" name="newPassword">
            <input type="submit" value="Change Password">
        </form>
        <br>
        <form method="post" action="add-rights.jsp">
            <select name="rightToAdd">
                
                <%
                if(!userToEditInfo.isAdministrator()){
                %>
                <option value="ADMINISTRATOR">Administrator</option>
                <%
                    }
                if(!userToEditInfo.isCustomer()){
                %>
                <option value="CUSTOMER">Customer</option>
                <%
                    }
                if(!userToEditInfo.isStock()){
                %>
                <option value="STOCK">Stock</option>
                <%
                }    
                %>
            </select>
            <input type="submit" value="Add rights">
        </form>
        <br>
        <form action="remove-rights.jsp">
            <select name="rightToRemove">
                <%
                if(userToEditInfo.isAdministrator()){
                %>
                <option value="ADMINISTRATOR">Administrator</option>
                <%
                    }
                if(userToEditInfo.isCustomer()){
                %>
                <option value="CUSTOMER">Customer</option>
                <%
                    }
                if(userToEditInfo.isStock()){
                %>
                <option value="STOCK">Stock</option>
                <%
                }    
                %>
            </select>
            <input type="submit" value="Remove rights">
        </form>
        <br>
        <form action="delete-user.jsp">
            <input type="submit" value="Delete User">
        </form>
        <%            }

        %>
        <form action="main.jsp">
            <input type="submit" value="Back to shop">
        </form>
    </body>
</html>
