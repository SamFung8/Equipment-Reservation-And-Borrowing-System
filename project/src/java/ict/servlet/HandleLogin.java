/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.StudentBean;
import ict.db.SeniorTechnicianDB;
import ict.db.StudentDB;
import ict.db.TechnicianDB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sam
 */
@WebServlet(name = "HandleLogin", urlPatterns = {"/HandleLogin"})
public class HandleLogin extends HttpServlet {

    private StudentDB sDB;
    private TechnicianDB tDB;
    private SeniorTechnicianDB stDB;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        sDB = new StudentDB(dbUrl, dbUser, dbPassword);
        tDB = new TechnicianDB(dbUrl, dbUser, dbPassword);
        stDB = new SeniorTechnicianDB(dbUrl, dbUser, dbPassword);
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

        if ("logout".equalsIgnoreCase(action)) {
            Cookie loginInfo = new Cookie("loginInfo", "");
            loginInfo.setMaxAge(0);
            response.addCookie(loginInfo);
            Cookie loginType = new Cookie("loginType", "");
            loginType.setMaxAge(0);
            response.addCookie(loginType);
            response.sendRedirect("index.jsp");
        } else if ("loginTechnician".equalsIgnoreCase(action)) {
            try {
                String email, password, type;

                email = request.getParameter("mail");
                password = request.getParameter("password");
                type = request.getParameter("technicianType");

                boolean isSuccess = false;
                if (type.equals("technician")) {
                    isSuccess = tDB.loginTechnicianAccount(email, password);
                    if (isSuccess) {
                        Cookie loginInfo = new Cookie("loginInfo", email);
                        loginInfo.setMaxAge(60 * 60 * 24);
                        response.addCookie(loginInfo);
                        Cookie loginType = new Cookie("loginType", "technician");
                        loginType.setMaxAge(60 * 60 * 24);
                        response.addCookie(loginType);
                        response.sendRedirect("HandleTechnician_new?action=showEquipment");
                    }
                } else {
                    isSuccess = stDB.loginSeniorTechnicianAccount(email, password);
                    if (isSuccess) {
                        Cookie loginInfo = new Cookie("loginInfo", email);
                        loginInfo.setMaxAge(60 * 60 * 24);
                        response.addCookie(loginInfo);
                        Cookie loginType = new Cookie("loginType", "seniorTechnician");
                        loginType.setMaxAge(60 * 60 * 24);
                        response.addCookie(loginType);
                        response.sendRedirect("HandleTechnician?action=showTech");
                    }
                }

                if (!isSuccess) {
                    request.setAttribute("message", "Sorry, can not login Technician account!\nPlase register again!");
                    request.setAttribute("url", "index.jsp?loginType=Technician.jsp");
                    passErrorMessage(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("message", "Sorry, can not login Technician account!\nPlase register again!");
                request.setAttribute("url", "index.jsp?loginType=Technician.jsp");
                passErrorMessage(request, response);
            }
        } else if ("loginStudent".equalsIgnoreCase(action)) {
            String id = "";
            String password;
            boolean isSuccess = false;

            id = request.getParameter("id");
            password = request.getParameter("password");

            try {
                isSuccess = sDB.loginStudentAccount(id, password);
            } catch (Exception e) {
                request.setAttribute("message", "Sorry, cannot login student account!\nPlase login again!");
                request.setAttribute("url", "index.jsp");

                passErrorMessage(request, response);
            }

            if (isSuccess) {
                Cookie loginInfo = new Cookie("loginInfo", id);
                loginInfo.setMaxAge(60 * 60 * 24);
                response.addCookie(loginInfo);
                Cookie loginType = new Cookie("loginType", "student");
                loginType.setMaxAge(60 * 60 * 24);
                response.addCookie(loginType);
                response.sendRedirect("HandleStudentEquipment?action=showEquipmentList");
            } else {
                request.setAttribute("message", "Sorry, cannot login student account!\nPlase login again!");
                request.setAttribute("url", "index.jsp");

                passErrorMessage(request, response);
            }
        }
    }

    private void passErrorMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/jsErrorMessage.jsp");
        rd.forward(request, response);
    }
}
