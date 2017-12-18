<%-- 
    Document   : error
    Created on : 2017-sep-29, 14:25:52
    Author     : Jakob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>An error occured :< : 
        <%
            out.println(session.getAttribute("error"));
        %></h1>
        
        <form action="logout.jsp">
            <input type="submit" value="Continue">
        </form>
    </body>
</html>
