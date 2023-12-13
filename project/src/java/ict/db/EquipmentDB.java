/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.EquipmentBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sam
 */
public class EquipmentDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public EquipmentDB(String url, String username, String password) {
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

    public ArrayList queryEquipment() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        EquipmentBean eb = null;
        ArrayList<EquipmentBean> e = new ArrayList<EquipmentBean>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Equipment WHERE display='true' and count>0";
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

    public boolean updateCountById(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE equipment SET count = (count-1) WHERE equipment_id=?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
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

    public ArrayList queryAllEquipment() {
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

}
