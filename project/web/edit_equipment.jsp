<%-- 
    Document   : edit_equipment
    Created on : 2020年12月5日, 下午10:23:46
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
                        <jsp:useBean id="e" scope="request" class="ict.bean.EquipmentBean"/>
                        <form method="get" action="HandleTechnician_new">
                            <fieldset>
                                <input type="hidden" name="action"  value="editEquipment_2">
                                <legend>New Equipment</legend>
                                <label for="e_id">Equipment ID: </label>
                                <input type="number" name="e_id" id="e_id" value=<%=e.getEquipmentID()%> readonly>
                                <br/><br/>                                
                                <label for="e_name">Equipment Name:</label>
                                <input type="text" name="e_name" id="e_name" size="40" value=<%=e.getName()%>>
                                <br/><br/>                                
                                <label for="quantity">Quantity:</label>
                                <input type="number" name="quantity" id="quantity" min="0" max="999" value=<%=e.getCount()%>>
                                <br/>                          
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
                                <textarea name="remark" id="remark" cols="40" rows="5"><%=e.getRemark()%></textarea>
                                <br>
                                <input type="submit" value="Edit" style="margin-left: 45%">
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

