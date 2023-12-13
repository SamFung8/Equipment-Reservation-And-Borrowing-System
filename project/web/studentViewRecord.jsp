<%-- 
    Document   : studentViewRecord
    Created on : 2020年11月30日, 下午09:37:32
    Author     : sam
--%>

<%@page import="ict.bean.EquipmentEventBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.EquipmentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div id="maincontainer">
            <div id="topsection"><div class="innertube">
                    <jsp:include page="heading.jsp" />
                    <h1 style="text-align: center;color: red">Main Page</h1>
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <h1>View all record</h1>
                        <table id='customers'>
                            <tr>
                                <th>Event Id</th> <th>Equipment Name</th><th>Borrow Date</th><th>Return Date</th><th>Count</th><th>Reason</th><th>Status</th>
                            </tr>
                            <%
                                if (request.getAttribute("equipment_event") != null && request.getAttribute("equipment") != null) {
                                    ArrayList<EquipmentEventBean> eventItems = (ArrayList<EquipmentEventBean>) request.getAttribute("equipment_event");
                                    ArrayList<EquipmentBean> items = (ArrayList<EquipmentBean>) request.getAttribute("equipment");
                                    // loop through the customer array to display each customer record
                                    for (int i = 0; i < items.size(); i++) {
                                        EquipmentBean e = items.get(i);
                                        EquipmentEventBean ev = eventItems.get(i);
                                        out.println("<tr>");
                                        out.println("<td>" + ev.getEvent_id() + "</td>");
                                        out.println("<td>" + e.getName() + "</td>");
                                        out.println("<td>" + ev.getBorrow_date() + "</td>");
                                        out.println("<td>" + ev.getReturn_date() + "</td>");
                                        out.println("<td>" + ev.getCount() + "</td>");
                                        out.println("<td>" + ev.getReson() + "</td>");
                                        out.println("<td>" + ev.getStatus() + "</td>");
                                        out.println("</tr>");

                                    }
                                }
                                out.println("</table>");
                            %>
                    </div>
                </div>
            </div>

            <div id="leftcolumn">
                <div class="innertube">
                    <jsp:include page="studentPageMenu.jsp" />
                </div>
            </div>

            <div id="footer">
                <jsp:include page="footer.jsp"  />
            </div>
        </div>
    </body>
</html>
