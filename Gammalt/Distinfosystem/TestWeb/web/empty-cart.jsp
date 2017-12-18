<%-- 
    Document   : empty-cart
    Created on : 2017-sep-29, 11:52:06
    Author     : Jakob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Emptying cart</title>
    </head>
    <body>
        <%
            session.removeAttribute("cartItems");
            String redirectURL = "http://localhost:8080/Webbshop/main.jsp";
            response.sendRedirect(redirectURL);
        %>
    </body>
</html>
