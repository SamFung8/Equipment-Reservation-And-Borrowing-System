/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.bean.EquipmentEventBean;
import ict.db.EquipmentDB;
import ict.db.EquipmentEventDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sam
 */
@WebServlet(name = "HandleStudentEquipment", urlPatterns = {"/HandleStudentEquipment"})
public class HandleStudentEquipment extends HttpServlet {

    private EquipmentEventDB evDB;
    private EquipmentDB eDB;
    private ArrayList<EquipmentBean> bookingItems;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        eDB = new EquipmentDB(dbUrl, dbUser, dbPassword);
        evDB = new EquipmentEventDB(dbUrl, dbUser, dbPassword);
        bookingItems = new ArrayList<EquipmentBean>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        boolean user = false;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getValue( ).equals("student")) {
                user = true;
                break;
            }
        }

        if (user) {
            if ("showEquipmentList".equalsIgnoreCase(action)) {
                ArrayList<EquipmentBean> e = new ArrayList<EquipmentBean>();

                e = eDB.queryEquipment();
                request.setAttribute("equipment", e);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/studentViewEquipment.jsp");
                rd.forward(request, response);
            } else if ("addBooking".equalsIgnoreCase(action)) {
                boolean repeat = false;
                String id = request.getParameter("id");

                for (int i = 0; i < bookingItems.size(); i++) {
                    if (String.valueOf(bookingItems.get(i).getEquipmentID()).equals(id)) {
                        repeat = true;
                        response.sendRedirect("HandleStudentEquipment?action=showEquipmentList&message=Sorry,you+have+already+add+this+item");
                    }
                }

                if (!repeat) {
                    EquipmentBean eb;
                    eb = eDB.queryEquipmentByID(id);
                    bookingItems.add(eb);
                    session.setAttribute("bookingItems", bookingItems);
                    response.sendRedirect("HandleStudentEquipment?action=showEquipmentList&message=Added");
                }
            } else if ("cencel".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");

                for (int i = 0; i < bookingItems.size(); i++) {
                    if (String.valueOf(bookingItems.get(i).getEquipmentID()).equals(id)) {
                        bookingItems.remove(i);
                        break;
                    }
                }
                session.setAttribute("bookingItems", bookingItems);
                response.sendRedirect("studentViewWaitingList.jsp");
            } else if ("createBooking".equalsIgnoreCase(action)) {
                EquipmentEventBean evb;
                String brrowDate, returnDate, reason;
                int count, equipmentId, studentId;

                studentId = 0;
                String cookieName = "loginInfo";
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        Cookie cookie = cookies[i];
                        if (cookieName.equals(cookie.getName())) {
                            studentId = Integer.parseInt(cookie.getValue());
                            break;
                        }
                    }

                    brrowDate = request.getParameter("bDate");
                    returnDate = request.getParameter("rDate");
                    reason = request.getParameter("reason");

                    for (int i = 0; i < bookingItems.size(); i++) {
                        equipmentId = bookingItems.get(i).getEquipmentID();
                        count = Integer.parseInt(request.getParameter("item" + i));
                        evb = new EquipmentEventBean(0, studentId, equipmentId, null, brrowDate, returnDate, reason, "booking", count);
                        evDB.addNewEvent(evb);
                        eDB.updateCountById(evb.getEquipment_id());
                    }
                    session.removeAttribute("bookingItems");
                    bookingItems = new ArrayList<EquipmentBean>();
                    response.sendRedirect("HandleStudentEquipment?action=showEquipmentList&message=Created");
                }
            } else if ("viewRecord".equalsIgnoreCase(action)) {
                ArrayList<EquipmentBean> e = new ArrayList<EquipmentBean>();
                ArrayList<EquipmentEventBean> ev = new ArrayList<EquipmentEventBean>();
                int studentId;

                studentId = 0;
                String cookieName = "loginInfo";
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        Cookie cookie = cookies[i];
                        if (cookieName.equals(cookie.getName())) {
                            studentId = Integer.parseInt(cookie.getValue());
                            break;
                        }
                    }

                    ev = evDB.queryEquipmentEventByStudentID(studentId);

                    for (int i = 0; i < ev.size(); i++) {
                        e.add(eDB.queryEquipmentByID(String.valueOf(ev.get(i).getEquipment_id())));
                    }
                }
                request.setAttribute("equipment", e);
                request.setAttribute("equipment_event", ev);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/studentViewRecord.jsp");
                rd.forward(request, response);

            }
        } else {
            request.setAttribute("message", "Please Login as student");
            request.setAttribute("url", "index.jsp");

            passErrorMessage(request, response);
        }

    }

    private void passErrorMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/jsErrorMessage.jsp");
        rd.forward(request, response);
    }
}
