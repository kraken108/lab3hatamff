<%-- 
    Document   : test
    Created on : 2017-sep-22, 16:46:27
    Author     : Jakob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            if(session.getAttribute("username") == null || session.getAttribute("username").equals("")){
                out.println("Please login m8");
            }else{
                out.println(session.getAttribute("username"));
            }
        %>
    </body>
</html>
