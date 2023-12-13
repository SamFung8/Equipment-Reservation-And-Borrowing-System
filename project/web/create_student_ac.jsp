<%-- 
    Document   : create_student_ac
    Created on : 2020年11月28日, 下午10:25:52
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
                    <h1 style="text-align: center;color: red">Create Student Account</h1>
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <form method="post" action="HandleTechnician">
                            <fieldset>
                                <input type="hidden" name="action"  value= "createStudentAC" />
                                <legend>Student Profile</legend>
                                <label for="name">Student ID:</label>
                                <input type="number" name="st_id" id="st_id" min="190001" max="199999" required="true">
                                <br/><br/>
                                <label for="name">Name:</label>
                                <input type="text" name="name" id="name" size="40" required="true">
                                <br/><br/>
                                <label for="mail">E-mail:</label>
                                <input type="email" name="mail" id="mail" size="40" required="true">
                                <br/><br/>
                                <label for="password">Password:</label>
                                <input type="password" name="password" id="password" size="40" required="true">
                                <br/><br/>
                                <div style="display:flex">
                                    <label for="class">Study Year:</label>
                                    <input type="radio" name="year" value="1">
                                    <label for="A" style="display:flex"> 1</label>
                                    <input type="radio" name="year" value="2">
                                    <label for="B" style="display:flex"> 2</label>
                                    <input type="radio" name="year" value="2+">
                                    <label for="C" style="display:flex"> 2+</label>
                                </div>
                                <br>
                                <div style="display:flex">
                                    <label for="class">Class:</label>
                                    <input type="radio" name="class" value="A">
                                    <label for="A" style="display:flex"> A</label>
                                    <input type="radio" name="class" value="B">
                                    <label for="B" style="display:flex"> B</label>
                                    <input type="radio" name="class" value="C">
                                    <label for="C" style="display:flex"> C</label>
                                </div>
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

