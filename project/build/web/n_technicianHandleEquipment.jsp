<%-- 
    Document   : n_technicianHandleEquipment
    Created on : 2020年12月12日, 上午02:43:32
    Author     : liwai
--%>

<%@page import="ict.bean.EquipmentEventBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
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
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <form method="get" action="HandleTechnician_new">
                            <fieldset>
                                <input type="hidden" name="action" value= "checkOut" />
                                <legend>Check Out</legend>
                                <label for="name">Event ID:</label>
                                <input type="number" name="Event_id" id="Event_id" required="true">
                                <br>
                                <br>
                                <input type="submit" value="Check Out" style="margin-left: 45%">
                            </fieldset>
                        </form>
                        <br>
                        <form method="post" action="HandleTechnician_new">
                            <fieldset>
                                <input type="hidden" name="action" value= "search" />
                                <legend>Check In</legend>
                                <label for="name">Student ID:</label>
                                <input type="number" name="st_id" id="st_id" required="true">
                                <input type="submit" value="Search">
                                <br>
                                <br>
                                <%
                                    ArrayList<EquipmentEventBean> equipments = (ArrayList<EquipmentEventBean>) request.getAttribute("equipment_event");
                                    out.println("<table id='customers'>");
                                    out.println("<tr>");
                                    out.println("<th>Event ID</th><th>Student ID</th><th>Equipment ID</th><th>Quantity</th><th>Status</th><th>Confirm Check In</th>");
                                    out.println("</tr>");
                                    String Out = "";
                                    for (int i = 0; i < equipments.size(); i++) {
                                        EquipmentEventBean ee = equipments.get(i);
                                        out.println("<tr>");
                                        out.println("<td>" + ee.getEvent_id()+ "</td>");
                                        out.println("<td>" + ee.getStudent_id() + "</td>");
                                        out.println("<td>" + ee.getEquipment_id() + "</td>");
                                        out.println("<td>" + ee.getCount() + "</td>");
                                        if ("out".equals(ee.getStatus())) {
                                            Out = "Check Out";
                                        }
                                        out.println("<td>" + Out + "</td>");
                                        out.println("<td><button><a href=\"HandleTechnician_new?action=checkIn&ev_id=" + ee.getEvent_id() + "&e_id=" + ee.getEquipment_id() + "&quantity=" + ee.getCount() + "\">Check In</a></button></td>");
                                        out.println("</tr>");

                                    }
                                    out.println("</table>");
                                %>

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

