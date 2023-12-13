/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.EquipmentEventBean;
import ict.bean.OverdueReportBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author sam
 */
public class EquipmentEventDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public EquipmentEventDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return DriverManager.getConnection(url, username, password);
    }

    public boolean addNewEvent(EquipmentEventBean evb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO Equipment_event VALUE (?, ?, ?, ?, ?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, evb.getEvent_id());
            pStmnt.setInt(2, evb.getStudent_id());
            pStmnt.setInt(3, evb.getEquipment_id());
            pStmnt.setString(4, evb.getEmail());
            pStmnt.setString(5, evb.getBorrow_date());
            pStmnt.setString(6, evb.getReturn_date());
            pStmnt.setInt(7, evb.getCount());
            pStmnt.setString(8, evb.getReson());
            pStmnt.setString(9, evb.getStatus());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public ArrayList<EquipmentEventBean> queryEquipmentEventByStudentID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        EquipmentEventBean evb = null;
        ArrayList<EquipmentEventBean> ev = new ArrayList<EquipmentEventBean>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM EQUIPMENT_event WHERE student_ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                evb = new EquipmentEventBean();
                evb.setEvent_id(rs.getInt("event_id"));
                evb.setStudent_id(rs.getInt("student_id"));
                evb.setEquipment_id(rs.getInt("equipment_id"));
                evb.setEmail(rs.getString("email"));
                evb.setBorrow_date(rs.getString("borrow_date"));
                evb.setReturn_date(rs.getString("return_date"));
                evb.setCount(rs.getInt("count"));
                evb.setReson(rs.getString("reason"));
                evb.setStatus(rs.getString("status"));
                ev.add(evb);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ev;
    }

    public ArrayList<EquipmentEventBean> queryEquipmentEventByDate(String bDate, String rDate) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        EquipmentEventBean evb = null;
        ArrayList<EquipmentEventBean> ev = new ArrayList<EquipmentEventBean>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM EQUIPMENT_event WHERE borrow_date>=? and return_date<=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, bDate);
            pStmnt.setString(2, rDate);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                evb = new EquipmentEventBean();
                evb.setEvent_id(rs.getInt("event_id"));
                evb.setStudent_id(rs.getInt("student_id"));
                evb.setEquipment_id(rs.getInt("equipment_id"));
                evb.setEmail(rs.getString("email"));
                evb.setBorrow_date(rs.getString("borrow_date"));
                evb.setReturn_date(rs.getString("return_date"));
                evb.setCount(rs.getInt("count"));
                evb.setReson(rs.getString("reason"));
                evb.setStatus(rs.getString("status"));
                ev.add(evb);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ev;
    }

    public ArrayList<OverdueReportBean> queryAllOverdue(String toDate) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        OverdueReportBean orb = null;
        ArrayList<OverdueReportBean> or = new ArrayList<OverdueReportBean>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT equipment_event.student_id, student.name, equipment_event.equipment_id, "
                    + "equipment.name, borrow_date, return_date, equipment_event.count from equipment, student, "
                    + "equipment_event where equipment.equipment_id=equipment_event.equipment_id "
                    + "and student.student_id=equipment_event.student_id and status='out' and return_date<?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, toDate);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                orb = new OverdueReportBean();
                orb.setStudentID(rs.getInt("student_id"));
                orb.setEquipmentID(rs.getInt("equipment_id"));
                orb.setBorrowDate(rs.getString("borrow_date"));
                orb.setReturnDate(rs.getString("return_date"));
                orb.setCount(rs.getInt("equipment_event.count"));
                orb.setsName(rs.getString("student.name"));
                orb.seteName(rs.getString("equipment.name"));
                or.add(orb);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return or;
    }

}
