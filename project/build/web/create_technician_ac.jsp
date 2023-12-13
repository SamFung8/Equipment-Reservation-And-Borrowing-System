<%-- 
    Document   : create_technician_ac
    Created on : 2020年11月28日, 下午10:26:19
    Author     : liwai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>profile</title>
        <link rel="stylesheet" type="text/css" href="css\mystyles.css">

        <style type="text/css">
            fieldset {
                padding: 1em;
                font:80%/1 sans-serif;
            }
            label {
                float:left;
                width:20%;
                margin-right:0.5em;
                padding-top:0.2em;
                text-align:right;
                font-weight:bold;
            } 
        </style>

    </head>
    <body>
        <div id="maincontainer">
            <div id="topsection"><div class="innertube">
                    <jsp:include page="heading.jsp" />
                    <h1 style="text-align: center;color: red">Create Technician Account</h1>
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <form method="post" action="HandleTechnician">
                            <fieldset>
                                <input type="hidden" name="action"  value= "createTechnicianAC" />
                                <legend>Technician Profile</legend>
                                <label for="email">Email</label>
                                <input type="email" name="email" id="email" size="40" required="true">
                                <br/><br/>
                                <label for="name">Name:</label>
                                <input type="text" name="name" id="name" size="40" required="true">
                                <br/><br/>
                                <label for="password">Password:</label>
                                <input type="password" name="password" id="password" size="40" required="true">
                                <br/><br/>
                                <div style="display:flex">
                                    <label for="class">Gender:</label>
                                    <input type="radio" name="gender" value="M">
                                    <label for="A" style="display:flex"> M</label>
                                    <input type="radio" name="gender" value="F">
                                    <label for="B" style="display:flex"> F</label>
                                </div>
                                <br/>
                                <label for="phoneNumber">Phone Number:</label>
                                <input type="number" name="phoneNumber" id="phoneNumber" min="10000000" max="99999999" required="true">
                                <br>
                                <input type="submit" value="Create" style="margin-left: 45%">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>

            <div id="leftcolumn">
                <div class="innertube">
                    <jsp:include page="technicianPageMenu.jsp" />
                </div>
            </div>

            <div id="footer">
                <jsp:include page="footer.jsp"  />
            </div>
        </div>
    </body>
</html>
