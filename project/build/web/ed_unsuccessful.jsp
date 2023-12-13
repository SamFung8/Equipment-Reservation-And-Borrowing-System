<%-- 
    Document   : ed_unsuccessful
    Created on : 2020年12月1日, 上午01:12:25
    Author     : liwai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function load(){
                alert("Edit Account Unsuccessful");
                window.location.href = 'HandleTechnician?action=showTech';
            }
        </script>
    </head>
    <body onload="load()">

    </body>
</html>
