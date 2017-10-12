<%-- 
    Document   : shopping-cart
    Created on : 2017-sep-26, 12:52:37
    Author     : Jakob
--%>

<%@page import="BO.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping cart</title>
    </head>
    <body>
        <h2>Shopping cart</h2>

        <%
            if (session.getAttribute("username") == null || session.getAttribute("username").equals("")) {
                out.println("Error: please login.");
            } else {
                int total = 0;
                Item[] cartItems;
                if (session.getAttribute("cartItems") == null) {
                    out.println("No items in cart");
                } else {
        %><h3>Items in cart:</h3><%
            cartItems = (Item[]) session.getAttribute("cartItems");

            for (int i = 0; i < cartItems.length; i++) {
                if (cartItems[i] == null) {
                } else {
                    total += cartItems[i].getPrice();
                    out.println(cartItems[i].toString());%><br><%
                            }
                        }
        %><br><br><% out.println("Total: " + total);


        %> 
        <form action="send-order.jsp">
            <input type="submit" value="Send order">
        </form><%

        }
        }
        %>
        <form action="main.jsp">
            <input type="submit" value="Back to shop">
        </form><%
        %>
    </body>
</html>
