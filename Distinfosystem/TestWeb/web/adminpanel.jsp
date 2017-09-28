<%-- 
    Document   : adminpanel
    Created on : 2017-sep-28, 12:15:39
    Author     : Jakob
--%>

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
            User user = (User)session.getAttribute("user");
            if(user == null){
                out.println("You are not logged in.");
            }else if(!user.isAdministrator()){
                out.println("Insufficient rights.");
            }else{
                out.println("Welcome to the administration panel!");
            }
            
            
        %>
        
    </body>
</html>
