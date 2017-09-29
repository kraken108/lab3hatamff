<%-- 
    Document   : send-order
    Created on : 2017-sep-27, 12:00:07
    Author     : Jakob
--%>

<%@page import="BO.Item"%>
<%@page import="Facade.OrderController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sending order</title>
    </head>
    <body>
        <%
            if (session.getAttribute("username") == null || session.getAttribute("username").equals("")) {
                out.println("Please login");
        %>
        <%
            } else {
                if (session.getAttribute("cartItems") == null) {
                    out.println("empty cart");
                } else {
                    //send order here
                    OrderController oc = new OrderController();
                    out.println(oc.sendOrder((Item[]) session.getAttribute("cartItems"), (String) session.getAttribute("username")));
                    session.removeAttribute("cartItems");
                }
            
        %>
        <form action="main.jsp">
            <input type="submit" value="Back to shop">
        </form>

        <%
            }%>

            <form action="main.jsp">
            <input type="submit" value="Back to shop">
        </form>
    </body>
</html>
