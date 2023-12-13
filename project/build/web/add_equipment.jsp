<%-- 
    Document   : add_equipment
    Created on : 2020年12月5日, 下午10:31:31
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
                    <h1 style="text-align: center;color: red">Add New Equipment</h1>
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <form method="post" action="HandleTechnician_new">
                            <fieldset>
                                <input type="hidden" name="action"  value="addEquipment">
                                <legend>New Equipment</legend>
                                <label for="e_name">Equipment Name:</label>
                                <input type="text" name="e_name" id="e_name" size="40" required="true">
                                <br/><br/>                                
                                <label for="quantity">Quantity:</label>
                                <input type="number" name="quantity" id="quantity" min="0" max="999" required="true">
                                <br/><br/>                                
                                <div style="display:flex">
                                    <label for="class">Display:</label>
                                    <input type="radio" name="display" value="true">
                                    <label for="A" style="display:flex"> True</label>
                                    <input type="radio" name="display" value="false">
                                    <label for="B" style="display:flex"> False</label>
                                </div>
                                <%
                                    String email = "";
                                    Cookie[] cookies = request.getCookies();
                                    for (int i = 0; i < cookies.length; i++) {
                                        if (cookies[i].getName().equals("loginInfo")) {
                                            email = cookies[i].getValue();
                                            break;
                                        }
                                    }


                                %>
                                <input type="hidden" name="email" value="<%=email%>">
                                <br/>
                                <label for="remark">Remark:</label>
                                <textarea name="remark" id="remark" cols="40" rows="5" placeholder="Remark..."></textarea>
                                <br>
                                <input type="submit" value="Add" style="margin-left: 45%">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>

            <div id="leftcolumn">
                <div class="innertube">
                    <jsp:include page="n_technicianPageMenu.jsp" />
                </div>
            </div>

            <div id="footer">
                <jsp:include page="footer.jsp"  />
            </div>
        </div>
    </body>
</html>
