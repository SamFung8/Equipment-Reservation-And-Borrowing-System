<%-- 
    Document   : template
    Created on : 2020年10月24日, 下午08:36:48
    Author     : sam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css\mystyles.css"/>
    </head>
    <body>
        <div id="maincontainer">
            <div id="topsection"><div class="innertube">
                    <jsp:include page="heading.jsp" />
                    <h1 style="text-align: center;color: red">Login</h1>
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <jsp:include page="login.jsp" />
                    </div>
                </div>
            </div>
                    
            <div id="leftcolumn">
                <div class="innertube">
                    <jsp:include page="loginMenu.jsp" />
                </div>
            </div>
                
            <div id="footer">
                <jsp:include page="footer.jsp"  />
            </div>
        </div>
    </body>
</html>
