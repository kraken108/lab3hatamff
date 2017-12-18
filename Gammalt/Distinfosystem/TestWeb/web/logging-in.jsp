<%-- 
    Document   : logging-in
    Created on : 2017-sep-28, 12:03:04
    Author     : Jakob
--%>

<%@page import="ViewModel.UserInfo"%>
<%@page import="Facade.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logging in</title>
    </head>
    <body>
        Please wait
        <%
            if (session.getAttribute("username") == null || session.getAttribute("username").equals("")) {
                out.println("Please login m8");
            } else {
                Login login = new Login();
                try{
                    UserInfo user = login.getUserInfo((String) session.getAttribute("username"));
                    session.setAttribute("user", user);
                    String redirectURL = "http://localhost:8080/Webbshop/main.jsp";
                    response.sendRedirect(redirectURL);
                }catch(Exception e){
                    out.println(e);
                }
                
            }
        %>
    </body>
</html>
