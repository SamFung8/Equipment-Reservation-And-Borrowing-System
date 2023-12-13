<%-- 
    Document   : cs_unsuccessful
    Created on : 2020年11月28日, 下午10:28:51
    Author     : liwai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function load(){
                alert("Student Account Create Unuccessful!\nPlase Enter Again!");
                window.location.href = 'create_student_ac.jsp';
            }
        </script>
    </head>
    <body onload="load()">

    </body>
</html>
