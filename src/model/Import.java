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
    private int price_import;
    private String create_at;
    private int staffId;

    public Import() {
    }

    public Import(int price_import) {
        this.price_import = price_import;
    }

    public Import(int price_import, int staffId) {
        this.price_import = price_import;
        this.staffId = staffId;
    }

    public Import(int id, int price_import, String create_at, int staffId) {
        this.id = id;
        this.price_import = price_import;
        this.create_at = create_at;
        this.staffId = staffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice_import() {
        return price_import;
    }

    public void setPrice_import(int price_import) {
        this.price_import = price_import;
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

    @Override
    public String toString() {
        return "Import{" + "id=" + id + ", price_import=" + price_import + ", create_at=" + create_at + ", staffId=" + staffId + '}';
    }
    
    
}
