<%-- 
    Document   : register
    Created on : 26-Sep-2017, 11:49:41
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <form name="theRegistry" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td>Username:</td>
                            <td><input type="text" size="25" /></td>
                        </tr>
                        
                        <tr>           
                            <td>Password:</td>
                            <td><input type="text" size="25" /></td>
                       </tr>                       
                    </tbody>              
                </table>          
            </form>             
        </h1>
        
        <%
            String userName;
            String password;

            
        %>
            
        
        <form name="submit" method="post" action="index.jsp">
            
                        <input type="submit" value="Submit information">
        </form>
        
        
    </body>
</html>
