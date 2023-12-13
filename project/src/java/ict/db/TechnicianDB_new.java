/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.EquipmentBean;
import ict.bean.EquipmentEventBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author liwai
 */
public class TechnicianDB_new {

    private String url = "";
    private String username = "";
    private String password = "";

    public TechnicianDB_new(String url, String username, String password) {
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

    public boolean addNewEquipment(String e_id, String e_name, String quantity, String display, String email, String remark) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO `equipment` (`equipment_id`, `count`, `name`, `display`, `email`, `remark`) VALUES (?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, e_id);
            pStmnt.setString(2, quantity);
            pStmnt.setString(3, e_name);
            pStmnt.setString(4, display);
            pStmnt.setString(5, email);
            pStmnt.setString(6, remark);
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
                System.out.println(ex);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
        return isSuccess;
    }

    public ArrayList queryEquipment() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        EquipmentBean eb = null;
        ArrayList<EquipmentBean> e = new ArrayList<EquipmentBean>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Equipment";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                eb = new EquipmentBean();
                eb.setEquipmentID(rs.getInt("equipment_id"));
                eb.setName(rs.getString("name"));
                eb.setCount(rs.getInt("count"));
                eb.setDisplay(rs.getString("display"));
                eb.setEmail(rs.getString("email"));
                eb.setRemark(rs.getString("remark"));
                e.add(eb);
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
        return e;
    }

    public EquipmentBean queryEquipmentByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        EquipmentBean eb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM EQUIPMENT WHERE EQUIPMENT_ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                eb = new EquipmentBean();
                eb.setEquipmentID(rs.getInt("equipment_id"));
                eb.setName(rs.getString("name"));
                eb.setCount(rs.getInt("count"));
                eb.setDisplay(rs.getString("display"));
                eb.setEmail(rs.getString("email"));
                eb.setRemark(rs.getString("remark"));
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
        return eb;
    }

    public boolean deleteEquipment(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM `equipment` WHERE `equipment`.`equipment_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

    public boolean editEquipment_2(String e_id, String e_name, String quantity, String display, String email, String remark) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE `equipment` SET `count` = ?, `name` = ?,`email` = ?, `remark` = ? WHERE `equipment`.`equipment_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, quantity);
            pStmnt.setString(2, e_name);
            pStmnt.setString(3, email);
            pStmnt.setString(4, remark);
            pStmnt.setString(5, e_id);
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
                System.out.println(ex);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
        return isSuccess;
    }

    public boolean changeDisplay(String equipment_id, String display) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        String n_display = "";
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE `equipment` SET `display` = ? WHERE `equipment`.`equipment_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            if (display.equals("true")) {
                n_display = "false";
            } else {
                n_display = "true";
            }
            pStmnt.setString(1, n_display);
            pStmnt.setString(2, equipment_id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

    public boolean checkOut(String event_id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE `equipment_event` SET `status` = 'out' WHERE `equipment_event`.`event_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, event_id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

    public ArrayList<EquipmentEventBean> queryEquipment_event_booking() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        EquipmentEventBean evb = null;
        ArrayList<EquipmentEventBean> ev = new ArrayList<EquipmentEventBean>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM `equipment_event` WHERE `status` = 'booking'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
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

    public ArrayList<EquipmentEventBean> queryEquipment_event_out() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        EquipmentEventBean evb = null;
        ArrayList<EquipmentEventBean> ev = new ArrayList<EquipmentEventBean>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM `equipment_event` WHERE `status` = 'out'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
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

    public ArrayList<EquipmentEventBean> queryEquipmentEventByStudentID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        EquipmentEventBean evb = null;
        ArrayList<EquipmentEventBean> ev = new ArrayList<EquipmentEventBean>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM EQUIPMENT_event WHERE student_ID=? and `status` = 'out'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, Integer.parseInt(id));
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

    public boolean checkIn_1(String event_id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE `equipment_event` SET `status` = 'in' WHERE `equipment_event`.`event_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, event_id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

    public boolean checkIn_2(String equipment_id, String quantity) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        int e_quantity = Integer.parseInt(quantity);

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT COUNT FROM `equipment` WHERE `equipment_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, Integer.parseInt(equipment_id));
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                e_quantity += rs.getInt("count");
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

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE `equipment` SET `count` = ? WHERE `equipment`.`equipment_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, String.valueOf(e_quantity));
            pStmnt.setString(2, equipment_id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

    public boolean confirm_1(String equipment_id, String quantity) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        int e_quantity = 0;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT COUNT FROM `equipment` WHERE `equipment_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, Integer.parseInt(equipment_id));
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                e_quantity = rs.getInt("count") - Integer.parseInt(quantity);
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

        if (e_quantity < 0) {
            return isSuccess;
        }

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE `equipment` SET `count` = ? WHERE `equipment`.`equipment_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, String.valueOf(e_quantity));
            pStmnt.setString(2, equipment_id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

    public boolean confirm_2(String event_id, String email) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();

            String preQueryStatement = "UPDATE `equipment_event` SET `email` = ? WHERE `equipment_event`.`event_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);
            pStmnt.setString(2, event_id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

        try {
            cnnct = getConnection();

            String preQueryStatement = "UPDATE `equipment_event` SET `status` = 'confirm' WHERE `equipment_event`.`event_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, event_id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

    public boolean decline(String event_id, String email) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();

            String preQueryStatement = "UPDATE `equipment_event` SET `email` = ? WHERE `equipment_event`.`event_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);
            pStmnt.setString(2, event_id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

        try {
            cnnct = getConnection();

            String preQueryStatement = "UPDATE `equipment_event` SET `status` = 'decline' WHERE `equipment_event`.`event_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, event_id);
            if (pStmnt.executeUpdate() > 0) {
                pStmnt.execute();
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

}
