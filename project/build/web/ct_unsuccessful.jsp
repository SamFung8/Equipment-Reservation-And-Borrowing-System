<%-- 
    Document   : ct_unsuccessful
    Created on : 2020年11月29日, 下午09:23:41
    Author     : liwai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function load(){
                alert("Technician Account Create Unuccessful!\nPlase Enter Again!");
                window.location.href = 'create_technician_ac.jsp';
            }
        </script>
    </head>
    <body onload="load()">

    </body>
</html>
