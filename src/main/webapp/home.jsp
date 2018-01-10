<%@ page import="com.homework.userAccount.UserAccount" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
        <title>Home Page</title>
        <style>
            <%@include file='style/loginPage.css' %>
        </style>
    </head>
<body id="body">
    <div align="center">
        <%UserAccount user = (UserAccount) session.getAttribute("User"); %>
        <h3>Hi <%=user.getName() %></h3>
            <strong>Your Email</strong>: <%=user.getEmail() %><br>
            <strong>Your Country</strong>: <%=user.getCountry() %><br>

            <form action="Logout" method="post">
                <input type="submit" value="Logout" >
            </form>
    </div>
    </body>
</html>