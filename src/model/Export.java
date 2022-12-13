/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author A
 */
public class Export {
    private int id;
    private int price_export;
    private String create_at;
    private int staffId;

    public Export() {
    }
    
    public Export(int price_export) {
        this.price_export = price_export;
    }

    public Export(int price_export, int staffId) {
        this.price_export = price_export;
        this.staffId = staffId;
    }

    public Export(int id, int price_export, String create_at) {
        this.id = id;
        this.price_export = price_export;
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice_export() {
        return price_export;
    }

    public void setPrice_export(int price_export) {
        this.price_export = price_export;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
    
    
    
}
