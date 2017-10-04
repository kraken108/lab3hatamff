<%-- 
    Document   : main
    Created on : 2017-sep-22, 16:46:27
    Author     : Jakob
--%>

<%@page import="ViewModel.ItemInfo"%>
<%@page import="ViewModel.UserInfo"%>
<%@page import="java.lang.String"%>
<%@page import="Facade.ItemController"%>
<%@page import="java.util.ArrayList"%>
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
            //check if logged in
            if (session.getAttribute("username") == null || session.getAttribute("username").equals("")) {
                out.println("Please login");
            } else {
                //if logged in then get the user info
                UserInfo user = (UserInfo) session.getAttribute("user");
                if (user == null) {
                    session.setAttribute("error", "No login found");
                    String redirectURL = "http://localhost:8080/Webbshop/error.jsp";
                    response.sendRedirect(redirectURL);
                }
                //if user is admin then display admin panel button
                if (user.isAdministrator()) {;

        %>
        <form action="adminpanel.jsp">
            <input type="submit" value="Administration Panel">
        </form>
        <%            }
            //if user is staff then display show orders button
            if (user.isStock()) {
        %>
        <form action="view-orders.jsp">
            <input type="submit" value="View orders">
        </form>
        <%
            }
        %>
        <br>
        <%
            out.println("Logged in as: " + session.getAttribute("username"));
        %>

        <form action="logout.jsp">
            <input type="submit" value="Logout">
        </form>
        <br><br><br>
        <%
            
        %>
        <form action="shopping-cart.jsp">
            <input type="submit" value="Shopping cart">
        </form>
        <% 
            //get a list of all current items from the database
            ItemController ic = new ItemController();
            ArrayList<ItemInfo> items = null;
            try {
                items = ic.getItems();
            } catch (Exception e) {
                session.setAttribute("error", "Failed to retrieve items");
                String redirectURL = "http://localhost:8080/Webbshop/error.jsp";
                response.sendRedirect(redirectURL);
            }

            //get the cartItems, if it doesnt exists (wont on first try) then create a new array
            ItemInfo[] cartItems;
            if (session.getAttribute("cartItems") == null) {
                cartItems = new ItemInfo[100];
            } else {
                cartItems = (ItemInfo[]) session.getAttribute("cartItems");
            }

            //get the add to cart parameter if it exists
            String addToCart = null;
            if (request.getParameter("addToCart") == null) {
            } else {
                addToCart = request.getParameter("addToCart");
            }

            if (addToCart == null) {
                //no items to add to cart
            } else { //add item to add to the cart

                for (int i = 0; i < cartItems.length; i++) {
                    if (cartItems[i] == null) {
                        String[] stringsToAdd = addToCart.split("\t");

                        cartItems[i] = new ItemInfo(stringsToAdd[0], Float.parseFloat(stringsToAdd[1]), stringsToAdd[2], stringsToAdd[3]);
                        session.setAttribute("cartItems", cartItems);
                        break;
                    }
                }
            }
            
            //Calculate nr of items in cart and print it out
            int x = 0;
            for (int i = 0; i < cartItems.length; i++) {
                if (cartItems[i] == null) {

                } else {
                    x++;
                }
            }
            out.println("Items in cart: " + x);

        %>
        <h3> Available items: </h3>
        <%            if (items == null) {
                //no items
            } else { //print out the item name etc
                for (int i = 0; i < items.size(); i++) {
                    out.println(items.get(i).toString());
        %>
        <form>
            <input type="hidden" name="addToCart" value="
                   <% //Button for adding the item to cart. Send item info in a string as parameter
                       out.print(items.get(i).getName()
                               + "\t" + items.get(i).getPrice() + "\t" + items.get(i).getInStock() + "\t" + items.get(i).getId());
                   %>">
            <input type="submit" value="Add to cart">
        </form>
        <br>
        <%
                    }

                }

            }
        %>
    </body>
</html>
