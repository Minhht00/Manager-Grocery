/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.ConnectHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author A
 */
public class ProductDao implements DaoInterface{
     @Override
    public List<Object> findAll() throws SQLException {
        List<Object> productList = new ArrayList<>();   
  
//        String query = "SELECT * FROM products where status !=0";
        String query = "SELECT * FROM products";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            Product product = new Product(rs.getInt("id"), rs.getString("product_name"),
                    rs.getInt("catalog_id"), rs.getInt("price"), rs.getInt("amount")
                    ,rs.getString("create_at"));
            productList.add(product);
        }
        return productList;
    }
    @Override
    public boolean insert(Object ob) throws SQLException {
        Product product = new Product();
        product = (Product) ob;
        String query = "insert into products(product_name,price,amount,catalog_id) values(?,?,?,?)";
        Connection connection = ConnectHelper.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, product.getProductName());
        pstmt.setInt(2, product.getPrice());
        pstmt.setInt(3, product.getAmount());
        pstmt.setInt(4, product.getCatalogId());
        return pstmt.execute();
    }

    @Override
    public boolean update(Object ob) throws SQLException {
      Product product = new Product();
        product = (Product) ob;
//        String query = "UPDATE products SET name=?,catalog_id=?, price=? "
//                + "WHERE id='" + fd.getId() + "'";
 String query = "UPDATE products SET product_name=?, price=?, amount=? "
                + "WHERE id='" + product.getId() + "'";
        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, product.getProductName());
        pstmt.setInt(2, product.getPrice());
        pstmt.setInt(3, product.getAmount());
       
        return pstmt.execute();
    }

    @Override
    public boolean delete(Object ob) throws SQLException {
        Product product = new Product();
        product = (Product) ob;
        String query = "delete from products "
                + "WHERE id='" + product.getId() + "'";
        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.execute();
    }

   public List<Product> search(String id) throws SQLException {
       
        List<Product> productList = new ArrayList<>();   
  
//        String query = "SELECT * FROM products where product_name LIKE '%"+name+"%'";
        String query = "SELECT * FROM products "
                + "WHERE id='" + id + "' or product_name LIKE'" + id +"%'" ;

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
//            Product product = new Product(rs.getInt("id"), rs.getString("product_name"),
//                    rs.getInt("catalog_id"), rs.getInt("price")
//                    , rs.getInt("status"));
             Product product = new Product(rs.getInt("id"), rs.getString("product_name"),
                    rs.getInt("catalog_id"), rs.getInt("price"), rs.getInt("amount")
                    ,rs.getString("create_at"));
            productList.add(product);
        }
        return productList;

    }
   public List<Product> searchName(String name) throws SQLException {
       
        List<Product> productList = new ArrayList<>();   
  
        String query = "SELECT * FROM products where product_name LIKE '%"+name+"%'";
      

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
//            Product product = new Product(rs.getInt("id"), rs.getString("product_name"),
//                    rs.getInt("catalog_id"), rs.getInt("price")
//                    , rs.getInt("status"));
             Product product = new Product(rs.getInt("id"), rs.getString("product_name"),
                    rs.getInt("catalog_id"), rs.getInt("price"), rs.getInt("amount")
                    ,rs.getString("create_at"));
            productList.add(product);
        }
        return productList;

    }
}
