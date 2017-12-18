<%-- 
    Document   : add-rights
    Created on : 2017-sep-28, 13:47:31
    Author     : Jakob
--%>

<%@page import="ViewModel.UserInfo"%>
<%@page import="Facade.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add rights</title>
    </head>
    <body>
        <%

            UserInfo user = (UserInfo) session.getAttribute("user");
            if (user == null) {

        %>
        You are not logged in.
        <%       } else if (!user.isAdministrator()) {
        %>
        Insufficient rights.
        <%
        } else {
            UserInfo userToEdit = (UserInfo) session.getAttribute("userToEdit");
            if (userToEdit != null && request.getParameter("rightToAdd") != null) {
                UserController uc = new UserController();
                try {
                    uc.addRights(userToEdit.getName(), request.getParameter("rightToAdd"));
        %>
        Success!
        <%
            } catch (Exception e) {
                out.println(e.toString());
            }
        } else {
        %>
        An error occurred! (Error: 5)
        <%
                }

            }
            session.removeAttribute("userToEdit");
        %>
        <form action="main.jsp">
            <input type="submit" value="Back to shop">
        </form>
    </body>
</html>
