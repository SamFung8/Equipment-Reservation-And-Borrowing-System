<%-- 
    Document   : n_technicianMain
    Created on : 2020年12月5日, 下午09:40:23
    Author     : liwai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.EquipmentBean"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
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

        <style>
            #customers {
                font-family: Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
                text-align: center;
            }

            #customers td, #customers th {
                border: 1px solid #ddd;
                padding: 8px;
            }

            #customers tr:nth-child(even){background-color: #f2f2f2;}

            #customers tr:hover {background-color: #ddd;}

            #customers th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #4CAF50;
                color: white;
            }
        </style>

    </head>
    <body>

        <%
            if (request.getParameter("message") != null) {
        %>
        <script type="text/javascript">
            var msg = "<%=request.getParameter("message")%>!";
            alert(msg);
            document.location.href = "HandleStudentEquipment?action=showEquipmentList";
        </script>
        <%
            }
        %>

        <div id="maincontainer">
            <div id="topsection"><div class="innertube">
                    <jsp:include page="heading.jsp" />
                    <h1 style="text-align: center;color: red">Main Page</h1>
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <%
                            ArrayList<EquipmentBean> equipments = (ArrayList<EquipmentBean>) request.getAttribute("equipment");
                            out.println("<h1>Equipment List</h1>");
                            out.println("<button class=\"btn_add\"><a href=\"add_equipment.jsp\">Add New Equipment</a></button>");
                            out.println("<hr>");
                            out.println("<table id='customers'>");
                            out.println("<tr>");
                            out.println("<th>ID</th>  <th> Name</th><th>Quantity</th><th>Remark</th ><th>Display</th><th>Change Display</th><th>Edit</th><th>Delete</th>");
                            out.println("</tr>");
                            String disOrEnable = "";
                            for (int i = 0; i < equipments.size(); i++) {
                                EquipmentBean e = equipments.get(i);
                                out.println("<tr>");
                                out.println("<td>" + e.getEquipmentID() + "</td>");
                                out.println("<td>" + e.getName() + "</td>");
                                out.println("<td>" + e.getCount() + "</td>");
                                out.println("<td>" + e.getRemark() + "</td>");
                                if ("true".equals(e.getDisplay())) {
                                    disOrEnable = "Enable";
                                } else {
                                    disOrEnable = "Disable";
                                }
                                out.println("<td>" + disOrEnable + "</td>");
                                if ("true".equals(e.getDisplay())) {
                                    out.println("<td><button><a href=\"HandleTechnician_new?action=changeDisplay&id=" + e.getEquipmentID() + "&display=" + e.getDisplay() + "\">Disable</a></button></td>");
                                } else {
                                    out.println("<td><button><a href=\"HandleTechnician_new?action=changeDisplay&id=" + e.getEquipmentID() + "&display=" + e.getDisplay() + "\">Enable</a></button></td>");
                                }
                                out.println("<td><button><a href=\"HandleTechnician_new?action=editEquipment&id=" + e.getEquipmentID() + "\">Edit</a></button></td>");
                                out.println("<td><button><a href=\"HandleTechnician_new?action=delEquipment&id=" + e.getEquipmentID() + "\">Delete</a></button></td>");
                                out.println("</tr>");

                            }
                            out.println("</table>");
                        %>
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
