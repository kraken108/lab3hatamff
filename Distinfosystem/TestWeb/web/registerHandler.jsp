<%-- 
    Document   : registerHandler
    Created on : 27-Sep-2017, 11:19:58
    Author     : Michael
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="Facade.Register"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>            
<<<<<<< HEAD
        <%
            
        String userName = request.getParameter("userName");
        String passWordOne = request.getParameter("passWordOne");
        String passWordTwo = request.getParameter("passWordTwo");
        
        Register register = new Register();       
=======
        <%            
        String userName = request.getParameter("userName");
        String passWordOne = request.getParameter("passWordOne");
        String passWordTwo = request.getParameter("passWordTwo");
        Register register = new Register();
>>>>>>> 328ead9d00637673994f566ed0da52432fd78290
        
        if(passWordOne.equals(passWordTwo) && register.checkPassWord(passWordOne) && register.checkUserName(userName)){

            register.setPassWord(passWordOne);
            register.setUserName(userName);
                
            try{
                register.insertUser(userName,passWordOne);
            }
            catch(SQLException Ex){
                session.setAttribute("error", "Username already exists");
                String redirectURL = "http://localhost:8080/Webbshop/error.jsp";   
                response.sendRedirect(redirectURL);  
               }
            }           
        else{
            String redirectURL = "http://localhost:8080/Webbshop/registrationFailed.jsp";   
            response.sendRedirect(redirectURL);            
        } 
        
        %>

        <table border="1">
            <tbody>
                <tr>
                    <td>Username: </td>
                    <td><%=userName %></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><%=passWordOne %></td>
                </tr>
            </tbody>
        </table>            
       
        
        <form name="backToMainPage" action="index.jsp" method="POST">
            
             <input type="submit" value="Back to main page" name="backToMainPage"/>
<<<<<<< HEAD
        </form>

                
=======
        </form>                
>>>>>>> 328ead9d00637673994f566ed0da52432fd78290
    </body>
</html>

