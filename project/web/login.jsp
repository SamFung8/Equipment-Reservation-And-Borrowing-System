<%-- 
    Document   : main
    Created on : 2020?10?24?, ??08:34:54
    Author     : sam
--%>

<%
    String type = "student";

    if (request.getParameter("loginType") != null) {
        type = "Technician";
    }
%>

<form method="post" action="HandleLogin">
    <fieldset>
        <%
            if (type.equals("student")) {
        %>
        <input type="hidden" name="action"  value= "loginStudent" />        
        <legend>Student Login</legend>
        <label for="id">ID: </label>
        <input type="text" name="id"/>
        <%
        } else {
        %>
        <div style="display:flex">
            <label for="class">Technician Type:</label>
            <input type="radio" name="technicianType" value="technician">
            <label for="technician" style="display:flex"> Technician</label>
            <input type="radio" name="technicianType" value="seniorTechnician">
            <label for="seniorTechnician" style="display:flex"> Senior Technician</label>
        </div>
        <br>
        <input type="hidden" name="action"  value= "loginTechnician" />        
        <legend>Technician Login</legend>
        <label for="mail">Email: </label>
        <input type="email" name="mail"/>
        <%
            }
        %>      
        <br/><br/>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" size="40"/>
        <input type="submit" value="Login" style="margin-left: 45%">
    </fieldset>
</form>