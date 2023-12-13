<%-- 
    Document   : StudentViewEquipment
    Created on : 2020年11月29日, 下午12:03:41
    Author     : sam
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
                            out.println("<table id='customers'>");
                            out.println("<tr>");
                            out.println("<th>Equipment Id</th>  <th> Name</th><th>Count</th><th>Remark</th ><th>Booking</th>");
                            out.println("</tr>");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < equipments.size(); i++) {
                                EquipmentBean e = equipments.get(i);
                                out.println("<tr>");

                                out.println("<td>" + e.getEquipmentID() + "</td>");
                                out.println("<td>" + e.getName() + "</td>");
                                out.println("<td>" + e.getCount() + "</td>");
                                out.println("<td>" + e.getRemark() + "</td>");
                                out.println("<td><button><a href=\"HandleStudentEquipment?action=addBooking&id=" + e.getEquipmentID() + "\">Add a new booking</a></button></td>");
                                out.println("</tr>");

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
