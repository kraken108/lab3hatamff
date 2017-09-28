<%-- 
    Document   : register
    Created on : 27-Sep-2017, 11:21:29
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
        <h1>Register</h1>
        <form reg ="Register" action="registerHandler.jsp" method="POST">
            <table border="0">
                
                <tbody>
                    <tr>
                        <td>Username : </td>
                        <td><input type="text" name="userName" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="text" name="passWord" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Password again: </td>
                        <td><input type="text" name="passWord" value="" size="50" /></td>
                    </tr>
                </tbody>
            </table>  
            <input type="submit" value="Submit" name="submitReg"/>                        
        </form>
    </body>
</html>
