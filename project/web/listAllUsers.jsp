<%-- 
    Document   : listAllUsers
    Created on : 2020年11月30日, 下午05:59:36
    Author     : liwai
--%>

<%@page import="ict.bean.StudentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.TechnicianBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css\mystyles.css"/>
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
                    <h1 style="text-align: center;color: red">All User Account</h1>
                </div>
            </div>

            <div id="contentwrapper">
                <div id="contentcolumn">
                    <div class="innertube">


                        <%
                            ArrayList<TechnicianBean> technicians = (ArrayList<TechnicianBean>) request.getAttribute("Tech_Ac");
                            out.println("<h1>Technician List</h1>");
                            out.println("<table id='customers'>");
                            out.println("<tr>");
                            out.println("<th>Email</th><th> Name</th><th>Gender</th><th>PhoneNumber</th ><th>Delete</th><th>Edit</th>");
                            out.println("</tr>");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < technicians.size(); i++) {
                                TechnicianBean t = technicians.get(i);
                                out.println("<tr>");

                                out.println("<td>" + t.getEmail() + "</td>");
                                out.println("<td>" + t.getName() + "</td>");
                                out.println("<td>" + t.getGender() + "</td>");
                                out.println("<td>" + t.getPhoneNumber() + "</td>");
                                out.println("<td><button><a href=\"HandleTechnician?action=delTech&email=" + t.getEmail() + "\">Delete</a></button></td>");
                                out.println("<td><button><a href=\"HandleTechnician?action=editTech&email=" + t.getEmail() + "\">Edit</a></button></td>");
                                out.println("</tr>");

                            }
                            out.println("</table>");
                        %>
                        
                        <%
                            ArrayList<StudentBean> students = (ArrayList<StudentBean>) request.getAttribute("Stud_Ac");
                            out.println("<h1>Student List</h1>");
                            out.println("<table id='customers'>");
                            out.println("<tr>");
                            out.println("<th>Student ID</th><th>Name</th><th>Email</th><th>Study Year</th><th>Class</th><th>Delete</th><th>Edit</th>");
                            out.println("</tr>");
                            // loop through the customer array to display each customer record
                            for (int i = 0; i < students.size(); i++) {
                                StudentBean s = students.get(i);
                                out.println("<tr>");

                                out.println("<td>" + s.getId() + "</td>");
                                out.println("<td>" + s.getName() + "</td>");
                                out.println("<td>" + s.getMail() + "</td>");
                                out.println("<td>" + s.getYear() + "</td>");
                                out.println("<td>" + s.getYear_class() + "</td>");
                                out.println("<td><button><a href=\"HandleTechnician?action=delStud&id=" + s.getId() + "\">Delete</a></button></td>");
                                out.println("<td><button><a href=\"HandleTechnician?action=editStud&id=" + s.getId() + "\">Edit</a></button></td>");
                                out.println("</tr>");

                            }
                            out.println("</table>");
                        %>

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
