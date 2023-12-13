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
public class EquipmentReportBean {
    private int equipmentID;
    private String name;
    private int count;

    public EquipmentReportBean() {
    }

    public EquipmentReportBean(int equipmentID, String name, int count) {
        this.equipmentID = equipmentID;
        this.name = name;
        this.count = count;
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
