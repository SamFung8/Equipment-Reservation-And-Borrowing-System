<%-- 
    Document   : edit_student_ac
    Created on : 2020年11月30日, 下午08:47:50
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
                    <h1 style="text-align: center;color: red">Edit Student Account</h1>
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <jsp:useBean id="s" scope="request" class="ict.bean.StudentBean"/>
                        <form method="post" action="HandleTechnician">
                            <fieldset>
                                <input type="hidden" name="action"  value= "editStudentAccount" />
                                <legend>Student Profile</legend>
                                <label for="name">Student ID:</label>
                                <input type="text" name="st_id" id="st_id" size="40" value=<%=s.getId()%> readonly>
                                <br/><br/>
                                <label for="name">Name:</label>
                                <input type="text" name="name" id="name" size="40" value=<%=s.getName()%>>
                                <br/><br/>
                                <label for="mail">E-mail:</label>
                                <input type="email" name="mail" id="mail" size="40" value=<%=s.getMail()%>>
                                <br/><br/>
                                <input type="submit" value="Edit" style="margin-left: 45%">
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
