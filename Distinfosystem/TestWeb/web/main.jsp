<%-- 
    Document   : main
    Created on : 2017-sep-22, 16:46:27
    Author     : Jakob
--%>

<%@page import="Facade.ItemController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BO.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Main page!</h1>

        <%

            if (session.getAttribute("username") == null || session.getAttribute("username").equals("")) {
                out.println("Please login m8");
            } else {
                out.println("Logged in as: " + session.getAttribute("username"));

        %><h3> Available items: </h3><br>
        <%                // Collect current items and display them to user, plus buttons to add to cart
            ItemController ic = new ItemController();
            ArrayList<Item> items = ic.getItems();

            Item[] cartItems;
            if (session.getAttribute("cartItems") == null) {
                cartItems = new Item[100];
            } else {
                cartItems = (Item[]) session.getAttribute("cartItems");
            }

            String addToCart = null;
            if(request.getParameter("addToCart") == null){
            }else{
                addToCart = request.getParameter("addToCart");
            }

            if (addToCart == null) {
                out.println("nulli");
            } else {
                out.println(addToCart + addToCart + addToCart);
              //  int index = Integer.parseInt(addToCart);
              //  cartItems[index] = items.get(index);
            }

          /*  for (int i = 0; i < cartItems.length; i++) {
                if (cartItems[i] == null) {
                } else {
                    out.println(cartItems[i].getName() + "   " + cartItems[i].getPrice());%><br><%
                }
            }*/

                        if (items == null) {
                            out.println("NULL LUL");
                        } else {
                            for (int i = 0; i < items.size(); i++) {
                                out.println(items.get(i).getName() + "\t" + items.get(i).getPrice());%>
        <form>
            <input type="hidden" name="addToCart" value="<%out.println(i);%>">
            <input type="submit" value="Add to cart">
        </form>
        <br><%
                    }

                }

            }
        %>
    </body>
</html>
