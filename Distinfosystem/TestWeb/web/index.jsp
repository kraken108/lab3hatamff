<%-- 
    Document   : newjsp
    Created on : 2017-sep-22, 13:17:51
    Author     : Anders
--%>

<%@page import="com.example.TestClass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <body> 

        <%
            String username = request.getParameter("uname");
            String pwd = request.getParameter("pword");
            if(pwd != null && username != null){
                session.setAttribute("username", username);%>
                
                Loggied in as <%= username %>
                <% } else{ %>
            
            
        <form method="post" action="index.jsp">
            Username: <input type="text" name="uname"><br>
            Password: <input type="password" name="pword"><br>
            <input type="submit" value="Login">
        </form>
                <%} %>
        <!--<% TestClass tc = new TestClass();
            tc.setName("ahmeeed");
            out.println(tc.getName());
            String name = request.getParameter("fname");
            if(name!=null){
               out.println(name); 
            }
            
        %> -->



        <!--<jsp:useBean id="bean" class="com.example.TestBean">
            <jsp:setProperty name = "bean" property = "testVar" value = "Ahmed"/>
        </jsp:useBean>
        <p>Testvar is:
        <jsp:getProperty name = "bean" property = "testVar"/>
    </p>
        -->
    </body> 
</html>
