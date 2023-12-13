<%-- 
    Document   : n_technicianEquipmentReport
    Created on : 2020年12月12日, 下午05:28:55
    Author     : sam
--%>

<%@page import="ict.bean.EquipmentReportBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/tlds/equipmentReport.tld" prefix="ict" %>
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
                        <form method="post" action="HandleTechnician_new">
                            <fieldset>
                                <input type="hidden" name="action" value= "equipmentReport" />
                                <h1 style="text-align: center;">Show the utilzation rate of the equipment</h1>
                                <legend>Calculated by month/year</legend>
                                <div style="margin-left:20%">
                                    <label>Date: Form </label>
                                    <input type="date" name="b_date" required="true"/><br><br>
                                    <label> To </label>
                                    <input type="date" name="r_date" required="true"/><br><br>
                                </div>
                                <input type="submit" value="Search" style="margin-left:40%;">
                                <br>
                                <br>
                            </fieldset>
                        </form>
                        <br><br>
                        <table id='customers'>
                            <tr>
                                <th>Equipment Id</th> <th>Equipment Name</th><th>Usage rate</th>
                            </tr>
                            <%
                                if (request.getAttribute("equipmentReport") != null) {
                                    ArrayList<EquipmentReportBean> items = (ArrayList<EquipmentReportBean>) request.getAttribute("equipmentReport");
                            %>       
                            <ict:showEquipmentReport er="<%=items%>" />
                            <%
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

