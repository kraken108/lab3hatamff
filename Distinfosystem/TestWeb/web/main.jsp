<%-- 
    Document   : main
    Created on : 2017-sep-22, 16:46:27
    Author     : Jakob
--%>

<%@page import="java.lang.String"%>
<%@page import="Facade.ItemController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BO.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Webbshop</title>
    </head>
    <body>
        <h1>Welcome to the webbshop!
        </h1>
        
        <%

            if (session.getAttribute("username") == null || session.getAttribute("username").equals("")) {
                out.println("Please login m8");
            } else {
        out.println("Logged in as: " + session.getAttribute("username"));%>
        <form action="logout.jsp">
            <input type="submit" value="Logout">
        </form>
        <br><br><br><%

        %><form action="shopping-cart.jsp">
            <input type="submit" value="Shopping cart">
        </form><%                // Collect current items and display them to user, plus buttons to add to cart
            ItemController ic = new ItemController();
            ArrayList<Item> items = ic.getItems();

            Item[] cartItems;
            if (session.getAttribute("cartItems") == null) {
                cartItems = new Item[100];
            } else {
                cartItems = (Item[]) session.getAttribute("cartItems");
            }

            String addToCart = null;
            if (request.getParameter("addToCart") == null) {
            } else {
                addToCart = request.getParameter("addToCart");
            }

            if (addToCart == null) {
                //no items in cart
            } else {
                for (int i = 0; i < cartItems.length; i++) {
                    if (cartItems[i] == null) {
                        String[] stringsToAdd = addToCart.split(" ");
                        out.println(stringsToAdd.length);
                        for(String s : stringsToAdd){
                            out.println(s);
                        }
                        cartItems[i] = new Item(stringsToAdd[0], Float.parseFloat(stringsToAdd[1]),stringsToAdd[2],stringsToAdd[3]);
                        session.setAttribute("cartItems", cartItems);
                        break;
                    }
                }
            }
            int x = 0;
            for (int i = 0; i < cartItems.length; i++) {
                if (cartItems[i] == null) {

                } else {
                    x++;
                }
            }
            out.println("Items in cart: " + x);

        %><h3> Available items: </h3>
        <%            if (items == null) {
                //no items
            } else {
                for (int i = 0; i < items.size(); i++) {
                    out.println(items.get(i).toString());%>
        <form>
            <input type="hidden" name="addToCart" value="<%out.println(items.get(i).getName() 
                    + " " + items.get(i).getPrice() + " "+items.get(i).getInStock() +" " + items.get(i).getId());%>">
            <input type="submit" value="Add to cart">
        </form>
        <br><%
                    }

                }

            }
        %>
    </body>
</html>
