/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author A
 */
public class ImportDetail {
    private int id;
    private int importId;
    private int productId;
    private int quantity;
    private int quantityImport;
    private int priceImport;
    private String msg;
    private String exp;
    private int status;
    private String create_at;

    public ImportDetail() {
    }
    
    public ImportDetail(int id, int quantityImport, String msg, String exp, int status) {
        this.id = id;
        this.quantityImport = quantityImport;  
        this.msg = msg;
        this.exp = exp;
        this.status = status;
    }
    
    public ImportDetail(int importId, int productId, int quantity, int priceImport, int status) {
        this.importId = importId;  
        this.productId = productId;
        this.quantity = quantity;
        this.priceImport = priceImport;
        this.status = status;
    }

    public ImportDetail(int id, int importId, int productId, int quantity, int quantityImport, int priceImport, String msg, String exp, int status, String create_at) {
        this.id = id;
        this.importId = importId;
        this.productId = productId;
        this.quantity = quantity;
        this.quantityImport = quantityImport;
        this.priceImport = priceImport;
        this.msg = msg;
        this.exp = exp;
        this.status = status;
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImportId() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId = importId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityImport() {
        return quantityImport;
    }

    public void setQuantityImport(int quantityImport) {
        this.quantityImport = quantityImport;
    }

    public int getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(int priceImport) {
        this.priceImport = priceImport;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
    
    
}
