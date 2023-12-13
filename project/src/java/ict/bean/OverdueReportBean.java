/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author sam
 */
public class OverdueReportBean {
    private int studentID, equipmentID, count;
    private String sName, eName, borrowDate, returnDate;

    public OverdueReportBean() {
    }

    public OverdueReportBean(int studentID, int equipmentID, int count, String sName, String eName, String borrowDate, String returnDate) {
        this.studentID = studentID;
        this.equipmentID = equipmentID;
        this.count = count;
        this.sName = sName;
        this.eName = eName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    
}
