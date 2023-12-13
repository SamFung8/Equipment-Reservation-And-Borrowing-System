<%-- 
    Document   : n_technicianConfirmOrDecline
    Created on : 2020年12月13日, 上午02:30:40
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
                        <%
                            ArrayList<EquipmentEventBean> equipments = (ArrayList<EquipmentEventBean>) request.getAttribute("equipment_event");
                            out.println("<table id='customers'>");
                            out.println("<tr>");
                            out.println("<th>Event ID</th><th>Student ID</th><th>Equipment ID</th><th>Quantity</th><th>Start Day</th><th>Return Day</th><th>Confirm</th><th>Decline</th>");
                            out.println("</tr>");
                            String Out = "";
                            for (int i = 0; i < equipments.size(); i++) {
                                EquipmentEventBean ee = equipments.get(i);
                                out.println("<tr>");
                                out.println("<td>" + ee.getEvent_id() + "</td>");
                                out.println("<td>" + ee.getStudent_id() + "</td>");
                                out.println("<td>" + ee.getEquipment_id() + "</td>");
                                out.println("<td>" + ee.getCount() + "</td>");
                                out.println("<td>" + ee.getBorrow_date() + "</td>");
                                out.println("<td>" + ee.getReturn_date() + "</td>");

                                String userEmail = "";
                                Cookie[] cookies = request.getCookies();
                                for (int c = 0; c < cookies.length; c++) {
                                    if (cookies[c].getName().equals("loginInfo")) {
                                        userEmail = cookies[c].getValue();
                                    }
                                }

                                out.println("<td><button><a href=\"HandleTechnician_new?action=confirm&ev_id=" + ee.getEvent_id() + "&e_id=" + ee.getEquipment_id() + "&quantity=" + ee.getCount() + "&email=" + userEmail + "\">Confirm</a></button></td>");
                                out.println("<td><button><a href=\"HandleTechnician_new?action=decline&ev_id=" + ee.getEvent_id() + "&email=" + userEmail + "\">Decline</a></button></td>");
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
