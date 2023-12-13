/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.bean.EquipmentEventBean;
import ict.bean.EquipmentReportBean;
import ict.bean.OverdueReportBean;
import ict.db.EquipmentDB;
import ict.db.EquipmentEventDB;
import ict.db.TechnicianDB_new;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "HandleTechnician_new", urlPatterns = {"/HandleTechnician_new"})
public class HandleTechnician_new extends HttpServlet {

    private TechnicianDB_new db;
    private EquipmentEventDB evDB;
    private EquipmentDB eDB;
    private ArrayList<EquipmentBean> e;
    private ArrayList<EquipmentEventBean> ee;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new TechnicianDB_new(dbUrl, dbUser, dbPassword);
        eDB = new EquipmentDB(dbUrl, dbUser, dbPassword);
        evDB = new EquipmentEventDB(dbUrl, dbUser, dbPassword);
        e = new ArrayList<EquipmentBean>();
        ee = new ArrayList<EquipmentEventBean>();
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
            if (cookies[i].getValue().equals("technician")) {
                user = true;
                break;
            }
        }

        if (user) {

            if ("showEquipment".equalsIgnoreCase(action)) {
                e = db.queryEquipment();
                request.setAttribute("equipment", e);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/n_technicianMain.jsp");
                rd.forward(request, response);
            } else if ("addEquipment".equalsIgnoreCase(action)) {
                String e_id;
                String e_name;
                String quantity;
                String display;
                String email;
                String remark;

                e_id = request.getParameter("e_id");
                e_name = request.getParameter("e_name");
                quantity = request.getParameter("quantity");
                display = request.getParameter("display");
                email = request.getParameter("email");
                remark = request.getParameter("remark");

                boolean isSuccess= db.addNewEquipment(e_id, e_name, quantity, display, email, remark);
     




                if (isSuccess) {
                    response.sendRedirect("add_successful.jsp");
                } else {
                    response.sendRedirect("add_unsuccessful.jsp");
                }
            } else if ("editEquipment".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");

                if (id != null) {
                    EquipmentBean equipment = db.queryEquipmentByID(id);
                    request.setAttribute("e", equipment);
                    RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/edit_equipment.jsp");
                    rd.forward(request, response);
                }
            } else if ("editEquipment_2".equalsIgnoreCase(action)) {

                String e_id;
                String e_name;
                String quantity;
                String display;
                String email;
                String remark;

                e_id = request.getParameter("e_id");
                e_name = request.getParameter("e_name");
                quantity = request.getParameter("quantity");
                display = request.getParameter("display");
                email = request.getParameter("email");
                remark = request.getParameter("remark");

                boolean isSuccess = db.editEquipment_2(e_id, e_name, quantity, display, email, remark);

                System.out.println(isSuccess);
                if (isSuccess) {
                    response.sendRedirect("ed_eq_successful.jsp");
                } else {
                    response.sendRedirect("ed_eq_unsuccessful.jsp");
                }
            } else if ("delEquipment".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");
                if (id != null) {
                    db.deleteEquipment(id);
                    response.sendRedirect("HandleTechnician_new?action=showEquipment");
                }
            } else if ("changeDisplay".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");
                String display = request.getParameter("display");
                if (id != null) {
                    db.changeDisplay(id, display);
                    response.sendRedirect("HandleTechnician_new?action=showEquipment");
                }
            } else if ("checkOut".equalsIgnoreCase(action)) {
                String id = request.getParameter("Event_id");
                if (id != null) {
                    boolean isSuccess = db.checkOut(id);

                    System.out.println(isSuccess);
                    if (isSuccess) {
                        response.sendRedirect("ed_eq_successful.jsp");
                    } else {
                        response.sendRedirect("ed_eq_unsuccessful.jsp");
                    }
                }
            } else if ("search".equalsIgnoreCase(action)) {
                String id = request.getParameter("st_id");

                if (id != null) {
                    ee = db.queryEquipmentEventByStudentID(id);
                    request.setAttribute("equipment_event", ee);

                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/n_technicianHandleEquipment.jsp");
                    rd.forward(request, response);
                }
            } else if ("shoeAllCheckIn".equalsIgnoreCase(action)) {
                ee = db.queryEquipment_event_out();
                request.setAttribute("equipment_event", ee);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/n_technicianHandleEquipment.jsp");
                rd.forward(request, response);
            } else if ("ConfirmOrDecline".equalsIgnoreCase(action)) {
                ee = db.queryEquipment_event_booking();
                request.setAttribute("equipment_event", ee);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/n_technicianConfirmOrDecline.jsp");
                rd.forward(request, response);
            } else if ("checkIn".equalsIgnoreCase(action)) {
                String ev_id;
                String e_id;
                String quantity;

                ev_id = request.getParameter("ev_id");
                e_id = request.getParameter("e_id");
                quantity = request.getParameter("quantity");

                boolean isSuccess = db.checkIn_2(e_id, quantity);

                System.out.println(isSuccess);
                if (isSuccess) {
                    isSuccess = db.checkIn_1(ev_id);
                    if (isSuccess) {
                        response.sendRedirect("ed_eq_successful.jsp");
                    }
                } else {
                    response.sendRedirect("ed_eq_unsuccessful.jsp");
                }
            } else if ("studentReport".equalsIgnoreCase(action)) {
                e = new ArrayList<EquipmentBean>();
                ee = new ArrayList<EquipmentEventBean>();
                int studentId;

                studentId = Integer.parseInt(request.getParameter("st_id"));

                ee = evDB.queryEquipmentEventByStudentID(studentId);

                for (int i = 0; i < ee.size(); i++) {
                    e.add(eDB.queryEquipmentByID(String.valueOf(ee.get(i).getEquipment_id())));
                }

                request.setAttribute("equipment", e);
                request.setAttribute("equipment_event", ee);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/n_technicianStudentReport.jsp");
                rd.forward(request, response);
            } else if ("equipmentReport".equalsIgnoreCase(action)) {
                e = new ArrayList<EquipmentBean>();
                ee = new ArrayList<EquipmentEventBean>();
                ArrayList<EquipmentReportBean> er = new ArrayList<EquipmentReportBean>();

                String bDate = request.getParameter("b_date");
                String rDate = request.getParameter("r_date");

                e = eDB.queryAllEquipment();
                ee = evDB.queryEquipmentEventByDate(bDate, rDate);

                for (int i = 0; i < e.size(); i++) {
                    int count = 0;
                    EquipmentReportBean erb = new EquipmentReportBean();
                    for (int j = 0; j < ee.size(); j++) {
                        if (e.get(i).getEquipmentID() == ee.get(j).getEquipment_id()) {
                            count++;
                        }
                    }
                    erb.setEquipmentID(e.get(i).getEquipmentID());
                    erb.setName(e.get(i).getName());
                    erb.setCount(count);
                    er.add(erb);
                }

                request.setAttribute("equipmentReport", er);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/n_technicianEquipmentReport.jsp");
                rd.forward(request, response);
            } else if ("confirm".equalsIgnoreCase(action)) {
                String ev_id;
                String e_id;
                String quantity;
                String email;

                ev_id = request.getParameter("ev_id");
                e_id = request.getParameter("e_id");
                quantity = request.getParameter("quantity");
                email = request.getParameter("email");

                boolean isSuccess = db.confirm_1(e_id, quantity);

                if (isSuccess) {
                    isSuccess = db.confirm_2(ev_id, email);
                    if (isSuccess) {
                        response.sendRedirect("ed_eq_successful.jsp");
                    }
                } else {
                    response.sendRedirect("ed_eq_unsuccessful.jsp");
                }
            } else if ("decline".equalsIgnoreCase(action)) {
                String ev_id;
                String email;

                ev_id = request.getParameter("ev_id");
                email = request.getParameter("email");

                boolean isSuccess = db.decline(ev_id, email);

                if (isSuccess) {
                    response.sendRedirect("ed_eq_successful.jsp");
                } else {
                    response.sendRedirect("ed_eq_unsuccessful.jsp");
                }
            } else if ("overdueReport".equalsIgnoreCase(action)) {
                OverdueReportBean orb = new OverdueReportBean();
                ArrayList<OverdueReportBean> or = new ArrayList<OverdueReportBean>();

                LocalDate localDate = LocalDate.now();//For reference
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String toDate = localDate.format(formatter);

                or = evDB.queryAllOverdue(toDate);

                request.setAttribute("overdueReport", or);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/n_technicianOverdueReport.jsp");
                rd.forward(request, response);
            }

        } else {
            request.setAttribute("message", "Please Login as Technician");
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
