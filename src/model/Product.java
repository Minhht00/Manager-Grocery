/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author A
 */
public class Product {
     private int id;
    private String productName;
    private int price;
    private int catalogId;
    private String createAt;

    public Product() {
    }

    public Product(int id, String productName,  int catalogId,int price, String createAt) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.catalogId = catalogId;
        this.createAt = createAt;
    }

    public Product(int id, String productName, int price, int catalogId) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.catalogId = catalogId;
    }

    public Product(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }

    public Product(int id, String productName, int price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

   

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    
    
    
}
