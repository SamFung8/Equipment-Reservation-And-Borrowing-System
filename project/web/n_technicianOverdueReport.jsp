<%-- 
    Document   : n_technicianOverdueReport
    Created on : 2020年12月13日, 上午09:21:45
    Author     : sam
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="ict.bean.OverdueReportBean"%>
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
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">
                        <%
                            LocalDate localDate = LocalDate.now();//For reference
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            String toDate = localDate.format(formatter);

                            out.println("<div style='text-align: center;'>");
                            out.println("<h1>Until today, no delivered equipment on return date record!</h1>");
                            out.println("<h1>ToDate: " + toDate + "</h1>");
                            out.println("</div>");

                            out.println("<br>");
                            out.println("<table id='customers'>");
                            out.println("<tr>");
                            out.println("<th>Equipment Id</th> <th>Equipment Name</th><th>Student Id</th><th>Student name</th><th>Count</th><th>Borrow Date</th><th>Return Date</th>");
                            out.println("</tr>");

                            ArrayList<OverdueReportBean> items = (ArrayList<OverdueReportBean>) request.getAttribute("overdueReport");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < items.size(); i++) {
                                OverdueReportBean or = items.get(i);
                                out.println("<tr>");
                                out.println("<td>" + or.getEquipmentID() + "</td>");
                                out.println("<td>" + or.geteName() + "</td>");
                                out.println("<td>" + or.getStudentID() + "</td>");
                                out.println("<td>" + or.getsName() + "</td>");
                                out.println("<td>" + or.getCount() + "</td>");
                                out.println("<td>" + or.getBorrowDate() + "</td>");
                                out.println("<td>" + or.getReturnDate() + "</td>");
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
