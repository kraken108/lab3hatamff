<%-- 
    Document   : view-orders
    Created on : 2017-sep-29, 12:01:07
    Author     : Jakob
--%>

<%@page import="BO.User"%>
<%@page import="BO.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Facade.OrderController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order page</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                out.println("You are not logged in.");
            } else if (!user.isStock()) {
                out.println("Insufficient rights.");
            } else {

        %>
        <h3>Current orders:</h3>

        <%            OrderController oc = new OrderController();
            try {
                ArrayList<Order> orders = oc.getAllOrders();
                if (orders.isEmpty()) {
                    out.println("No orders at the moment");
                } else {
                    for (Order o : orders) {
                        out.println(o.toString());

        %>
        <br><br>
        <%                    }
                }
            } catch (Exception e) {
                out.println(e);
            }

        %>
        <form action="main.jsp">
            <input type="submit" value="Back to shop">
        </form>
        <%                    }


        %>

    </body>
</html>
