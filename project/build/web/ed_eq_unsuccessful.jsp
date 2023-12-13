<%-- 
    Document   : ed_eq_unsuccessful
    Created on : 2020年12月12日, 上午01:51:56
    Author     : liwai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function load(){
                alert("Unsuccessful");
                window.location.href = 'HandleTechnician_new?action=showEquipment';
            }
        </script>
    </head>
    <body onload="load()">

    </body>
</html>
