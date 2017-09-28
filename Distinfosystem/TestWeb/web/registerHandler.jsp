<%-- 
    Document   : registerHandler
    Created on : 27-Sep-2017, 11:19:58
    Author     : Michael
--%>

<%@page import="Facade.Register"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>            
        <%
        
        String userName = request.getParameter("userName");
        String passWordOne = request.getParameter("passWordOne");
        String passWordTwo = request.getParameter("passWordTwo");
        
        Register register = new Register();
        
        if(register.checkStrings(userName))
            register.setUserName(userName);

        if((register.checkStrings(passWordOne)) &&  (register.checkStrings(passWordTwo))
                && (register.comparePasswords(passWordOne, passWordTwo)))
            register.setPassWord(passWordOne);
        
        %>
        <table border="1">
            <tbody>
                <tr>
                    <td>Username: </td>
                    <td><%= userName  %></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><%= passWordOne %></td>
                </tr>
            </tbody>
        </table>
      
            <form reg ="backToMainPage" action="index.jsp" method="POST">
                
                        <input type="submit" value="Back" name="submitReg"/>                        

            </form>    
                
    </body>
</html>
