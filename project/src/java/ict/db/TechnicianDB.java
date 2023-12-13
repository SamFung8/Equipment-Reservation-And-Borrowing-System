/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.StudentBean;
import ict.bean.TechnicianBean;
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
public class TechnicianDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public TechnicianDB(String url, String username, String password) {
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

    public boolean loginTechnicianAccount(String email, String password) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM TECHNICIAN WHERE email=? and password=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);
            pStmnt.setString(2, password);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
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

    public boolean registerTechnicianAccount(String mail, String name, String password, String gender, int phoneNumber) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO `technician` (`email`, `name`, `password`, `gender`, `phoneNumber`) VALUES (?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, mail);
            pStmnt.setString(2, name);
            pStmnt.setString(3, password);
            pStmnt.setString(4, gender);
            pStmnt.setString(5, String.valueOf(phoneNumber));
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

    public boolean registerStudentAccount(int st_id, String name, String mail, String password, String year, String year_class) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO STUDENT VALUE (?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, String.valueOf(st_id));
            pStmnt.setString(2, name);
            pStmnt.setString(3, mail);
            pStmnt.setString(4, password);
            pStmnt.setString(5, year);
            pStmnt.setString(6, year_class);
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

    public ArrayList<TechnicianBean> queryTechnician() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<TechnicianBean> al = new ArrayList<TechnicianBean>();
        TechnicianBean t = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM technician";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                t = new TechnicianBean();
                t.setEmail(rs.getString(1));
                t.setName(rs.getString(2));
                t.setPassword(rs.getString(3));
                t.setGender(rs.getString(4));
                t.setPhoneNumber(rs.getInt(5));
                al.add(t);
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
        return al;
    }

    public ArrayList<StudentBean> queryStudent() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<StudentBean> al = new ArrayList<StudentBean>();
        StudentBean s = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM STUDENT";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                s = new StudentBean();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setMail(rs.getString(3));
                s.setPassword(rs.getString(4));
                s.setYear(rs.getString(5));
                s.setYear_class(rs.getString(6));
                al.add(s);
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
        return al;
    }

    public boolean deleteTechnicianAccount(String email) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM `technician` WHERE `technician`.`email` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);
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
    
    public boolean deleteStudentAccount(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM `student` WHERE `student`.`student_id` = ?";
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
    
    public boolean editStudentAccount(int st_id, String name, String mail) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE `student` SET `student_id` = ?, `name` = ?, `mail` = ? WHERE `student`.`student_id` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, String.valueOf(st_id));
            pStmnt.setString(2, name);
            pStmnt.setString(3, mail);
            pStmnt.setString(4, String.valueOf(st_id));
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
    
    public boolean editStudentAccount(String email, String name, int phoneNumber) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE `technician` SET `email` = ?, `name` = ?, `phoneNumber` = ? WHERE `technician`.`email` = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);
            pStmnt.setString(2, name);
            pStmnt.setString(3, String.valueOf(phoneNumber));
            pStmnt.setString(4, email);
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
    
    public StudentBean queryCustyID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        StudentBean s = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM STUDENT WHERE student_id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                s = new StudentBean();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setMail(rs.getString(3));
                s.setPassword(rs.getString(4));
                s.setYear(rs.getString(5));
                s.setYear_class(rs.getString(6));
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
        return s;
    }
    
    public TechnicianBean queryCustyEmail(String email) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        TechnicianBean t = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM technician WHERE email=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                t = new TechnicianBean();
                t.setEmail(rs.getString(1));
                t.setName(rs.getString(2));
                t.setPassword(rs.getString(3));
                t.setGender(rs.getString(4));
                t.setPhoneNumber(rs.getInt(5));
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
        return t;
    }
}
