<%-- 
    Document   : logout
    Created on : 2017-sep-26, 13:06:35
    Author     : Jakob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logging out</title>
    </head>
    <body>
        <%
            session.removeAttribute("username");
            session.removeAttribute("cartItems");
            String redirectURL = "http://localhost:8080/Webbshop/";
            response.sendRedirect(redirectURL);
        %>
    </body>
</html>
