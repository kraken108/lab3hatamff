<%-- 
    Document   : newjsp
    Created on : 2017-sep-22, 13:17:51
    Author     : Anders
--%>

<%@page import="Facade.Login"%>
<%@page import="BO.TestClass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <body> 
        <h1> LOGIN TEST </h1>
        <%
            String username = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            
            if (pwd != null && username != null && !pwd.equals("") && !username.equals("")) {
                Login login = new Login();
                String result = login.tryLogin(username, pwd);
                if (result.equals("SUCCESSFUL")) {
                    //session.setAttribute("username", username);
                    out.println("Logged in as: " + username);
                    
                    session.setAttribute("username", username);
                    String redirectURL = "http://localhost:8080/Webbshop/main.jsp";
                    response.sendRedirect(redirectURL);

                    //redirect to main page
                    
                } else if (result.equals("UNSUCCESSFUL")) {
                    //failed to login
                    out.println("Username or password was incorrect.");
                } else{
                    //error
                    out.println(result);
 
                }
            } else { %>

        <form method="post">
            Username: <input type="text" name="uname"><br>
            Password: <input type="password" name="pwd"><br>
            <input type="submit" value="Login">
        </form>
        <%}%>



    </body> 
</html>
