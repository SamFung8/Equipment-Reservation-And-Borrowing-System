<%-- 
    Document   : ed_eq_successful
    Created on : 2020年12月12日, 上午01:52:06
    Author     : liwai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function load(){
                alert("Successful");
                window.location.href = 'HandleTechnician_new?action=showEquipment';
            }
        </script>
    </head>
    <body onload="load()">

    </body>
</html>
