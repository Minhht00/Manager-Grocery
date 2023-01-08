/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lexuanmuoi
 */
public class Staff {

    private int id;
    private String staffName;
    private String phone;
    private String address;
    private int position;
    private String creatAt;
    private String birthDay;
    private String account;
    private String pass;

    public Staff() {
    }
    
    public Staff(String account, String pass, int id) {
        this.account = account;
        this.pass = pass;
        this.id = id;
    }

    public Staff(int id, String staffName, String birthDay, String address, String creatAt, String phone) {
        this.id = id;
        this.staffName = staffName;
        this.phone = phone;
        this.address = address;
        this.creatAt = creatAt;
        this.birthDay = birthDay;
    }

    public Staff(int id, String staffName, String birthDay, String address, String phone) {
        this.id = id;
        this.staffName = staffName;
        this.phone = phone;
        this.address = address;
        this.birthDay = birthDay;
    }

    public Staff(String staffName, String birthDay, String address, String phone) {
        this.staffName = staffName;
        this.phone = phone;
        this.address = address;
        this.birthDay = birthDay;
    }
    
    public Staff(String staffName, String birthDay, String address, String phone, int position) {
        this.staffName = staffName;
        this.phone = phone;
        this.address = address;
        this.birthDay = birthDay;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

}
