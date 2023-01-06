/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author A
 */
public class Import {
    private int id;
    private int priceImport;
    private String create_at;
    private int staffId;

    public Import() {
    }

    public Import(int priceImport) {
        this.priceImport = priceImport;
    }

    public Import(int priceImport, int staffId) {
        this.priceImport = priceImport;
        this.staffId = staffId;
    }

    public Import(int id, int priceImport, String create_at, int staffId) {
        this.id = id;
        this.priceImport = priceImport;
        this.create_at = create_at;
        this.staffId = staffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(int priceImport) {
        this.priceImport = priceImport;
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
