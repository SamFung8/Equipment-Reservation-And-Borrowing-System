/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;
import java.io.Serializable;
/**
 *
 * @author sam
 */
public class StudentBean {
    private int id;
    private String name;
    private String mail;
    private String password;
    private String year;
    private String year_class;

    public StudentBean() {
    }

    public StudentBean(int id, String name, String mail, String password, String year, String year_class) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.year = year;
        this.year_class = year_class;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear_class() {
        return year_class;
    }

    public void setYear_class(String year_class) {
        this.year_class = year_class;
    }
    
}
