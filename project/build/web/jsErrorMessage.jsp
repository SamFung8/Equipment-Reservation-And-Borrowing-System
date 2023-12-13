<%-- 
    Document   : jsErrorMessage
    Created on : 2020年11月28日, 下午03:41:53
    Author     : sam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <%
            String message = (String) request.getAttribute("message");
            String url = (String) request.getAttribute("url");
        %>

        <a href="<%= url%>"><h1 style="text-align: center;"><%= message%></h1></a>


    </body>
</html>
