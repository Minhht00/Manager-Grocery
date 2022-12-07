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
    private String creatAt;
    private String birthDay;

    public Staff() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
