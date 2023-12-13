<%-- 
    Document   : studentViewWaitingList
    Created on : 2020年11月29日, 下午06:03:14
    Author     : sam
--%>

<%@page import="ict.bean.EquipmentBean"%>
<%@page import="java.util.ArrayList"%>
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
                        <h1>Waiting for booking</h1>
                        <form method="get" action="HandleStudentEquipment">
                            <table id='customers'>
                                <tr>
                                    <th>Equipment Id</th>  <th> Name</th><th>You want to get how many?</th><th>Cencel?</th>
                                </tr>
                                <%
                                    if (session.getAttribute("bookingItems") != null) {
                                        out.println("<input type=\"hidden\" name=\"action\"  value= \"createBooking\" />");
                                        ArrayList<EquipmentBean> items = (ArrayList<EquipmentBean>) session.getAttribute("bookingItems");
                                        // loop through the customer array to display each customer record
                                        for (int i = 0; i < items.size(); i++) {
                                            EquipmentBean e = items.get(i);
                                            out.println("<tr>");
                                            out.println("<td>" + e.getEquipmentID() + "</td>");
                                            out.println("<td>" + e.getName() + "</td>");
                                            out.println("<td><input type = 'number' min = 0 name ='item" + i + "' max = " + e.getCount() + " value=1></td>");
                                            out.println("<td><button><a href=\"HandleStudentEquipment?action=cencel&id=" + e.getEquipmentID() + "\">Cencel this booking</a></button></td>");
                                            out.println("</tr>");

                                        }
                                    }
                                    out.println("</table>");
                                    if (session.getAttribute("bookingItems") != null) {
                                        ArrayList<EquipmentBean> items = (ArrayList<EquipmentBean>) session.getAttribute("bookingItems");
                                        if (items.size() > 0) {
                                            out.println("<h2>Please complete this form to borrow all the items: </h2>");
                                            out.println("Borrow Date: <input type=\"date\" name='bDate'><br><br>");
                                            out.println("Expected return date: <input type=\"date\" name='rDate'><br><br>");
                                            out.println("The Reason for borrow: <textarea  name=\"reason\" rows=\"4\" cols=\"50\">Comment......</textarea>");
                                            out.println("<input type=\"Submit\" value=\"create\" style='margin-left: 45%'>");
                                        }
                                    }
                                %>
                        </form>
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
