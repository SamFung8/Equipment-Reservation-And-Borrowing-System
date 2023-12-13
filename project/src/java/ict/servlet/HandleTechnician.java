/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.StudentBean;
import ict.bean.TechnicianBean;
import ict.db.TechnicianDB;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author liwai
 */
@WebServlet(name = "HandleTechnician", urlPatterns = {"/HandleTechnician"})
public class HandleTechnician extends HttpServlet {

    private TechnicianDB db;
    private ArrayList<TechnicianBean> TechnicianAc;
    private ArrayList<StudentBean> StudentAc;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new TechnicianDB(dbUrl, dbUser, dbPassword);
        TechnicianAc = new ArrayList<TechnicianBean>();
        StudentAc = new ArrayList<StudentBean>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action = request.getParameter("action");

        boolean user = false;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getValue().equals("seniorTechnician")) {
                user = true;
                break;
            }
        }

        if (user) {
            if ("createTechnicianAC".equalsIgnoreCase(action)) {
                String mail;
                String name;
                String password;
                String gender;
                int phoneNumber;

                mail = request.getParameter("email");
                name = request.getParameter("name");
                password = request.getParameter("password");
                gender = request.getParameter("gender");
                phoneNumber = parseInt(request.getParameter("phoneNumber"));

                boolean isSuccess = false;
                try {
                    isSuccess = db.registerTechnicianAccount(mail, name, password, gender, phoneNumber);
                } catch (Exception e) {
                    request.setAttribute("message", "Plase choose the Gender.");
                    request.setAttribute("url", "add_equipment.jsp");

                    passErrorMessage(request, response);
                }

                if (isSuccess) {
                    response.sendRedirect("ct_successful.jsp");
                } else {
                    response.sendRedirect("ct_unsuccessful.jsp");
                }
            } else if ("createStudentAC".equalsIgnoreCase(action)) {
                int st_id;
                String name;
                String mail;
                String password;
                String year;
                String year_class;

                st_id = parseInt(request.getParameter("st_id"));
                name = request.getParameter("name");
                mail = request.getParameter("mail");
                password = request.getParameter("password");
                year = request.getParameter("year");
                year_class = request.getParameter("class");

                boolean isSuccess = false;
                try {
                    isSuccess = db.registerStudentAccount(st_id, name, mail, password, year, year_class);
                } catch (Exception e) {
                    request.setAttribute("message", "Plase choose the Study Year and Class.");
                    request.setAttribute("url", "add_equipment.jsp");

                    passErrorMessage(request, response);
                }

                if (isSuccess) {
                    response.sendRedirect("cs_successful.jsp");
                } else {
                    response.sendRedirect("cs_unsuccessful.jsp");
                }
            } else if ("showTech".equalsIgnoreCase(action)) {
                TechnicianAc = db.queryTechnician();
                request.setAttribute("Tech_Ac", TechnicianAc);

                StudentAc = db.queryStudent();
                request.setAttribute("Stud_Ac", StudentAc);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/listAllUsers.jsp");
                rd.forward(request, response);
            } else if ("delTech".equalsIgnoreCase(action)) {

                String email = request.getParameter("email");
                if (email != null) {
                    db.deleteTechnicianAccount(email);
                    response.sendRedirect("HandleTechnician?action=showTech");
                }
            } else if ("delStud".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");
                if (id != null) {
                    db.deleteStudentAccount(id);
                    response.sendRedirect("HandleTechnician?action=showTech");
                }
            } else if ("editTech".equalsIgnoreCase(action)) {
                String email = request.getParameter("email");

                if (email != null) {
                    TechnicianBean technician = db.queryCustyEmail(email);
                    request.setAttribute("t", technician);
                    RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/edit_technician_ac.jsp");
                    rd.forward(request, response);
                }
            } else if ("editStud".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");

                if (id != null) {
                    StudentBean student = db.queryCustyID(id);
                    request.setAttribute("s", student);
                    RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/edit_student_ac.jsp");
                    rd.forward(request, response);
                }
            } else if ("editStudentAccount".equalsIgnoreCase(action)) {
                int st_id;
                String name;
                String mail;

                st_id = parseInt(request.getParameter("st_id"));
                name = request.getParameter("name");
                mail = request.getParameter("mail");

                boolean isSuccess = db.editStudentAccount(st_id, name, mail);

                if (isSuccess) {
                    response.sendRedirect("ed_successful.jsp");
                } else {
                    response.sendRedirect("ed_unsuccessful.jsp");
                }
            } else if ("editTechnicianAccount".equalsIgnoreCase(action)) {

                String mail;
                String name;;
                int phoneNumber;

                mail = request.getParameter("email");
                name = request.getParameter("name");
                phoneNumber = parseInt(request.getParameter("phoneNumber"));

                boolean isSuccess = db.editStudentAccount(mail, name, phoneNumber);

                System.out.println(isSuccess);
                if (isSuccess) {
                    response.sendRedirect("ed_successful.jsp");
                } else {
                    response.sendRedirect("ed_unsuccessful.jsp");
                }
            }

        } else {
            request.setAttribute("message", "Please Login as Senior Technician");
            request.setAttribute("url", "index.jsp?loginType=Technician");

            passErrorMessage(request, response);
        }
    }

    private void passErrorMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/jsErrorMessage.jsp");
        rd.forward(request, response);
    }

}
