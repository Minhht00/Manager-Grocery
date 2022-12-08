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
import model.Category;

/**
 *
 * @author A
 */
public class CategoryDao implements DaoInterface{
    @Override
    public List<Object> findAll() throws SQLException {
        List<Object> categoryList = new ArrayList<>();

//        String query = "SELECT * FROM products where status !=0";
        String query = "SELECT * FROM categories";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            Category category = new Category(rs.getInt("id"), rs.getString("category_name"),rs.getString("create_at"));
            categoryList.add(category);
        }
        return categoryList;
    }

    @Override
    public boolean insert(Object ob) throws SQLException {
        Category category = (Category) ob;
        String query = "insert into categories(category_name) values(?)";
        Connection connection = ConnectHelper.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, category.getCategoryName());
        return pstmt.execute();
    }

    @Override
    public boolean update(Object ob) throws SQLException {
        Category category = (Category) ob;
//        String query = "UPDATE products SET name=?,catalog_id=?, price=? "
//                + "WHERE id='" + fd.getId() + "'";
        String query = "UPDATE categories SET category_name=?"
                + "WHERE id='" + category.getId() + "'";
        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, category.getCategoryName());

        return pstmt.execute();
    }

    @Override
    public boolean delete(Object ob) throws SQLException {
        Category category = (Category) ob;
        String query = "delete from categories "
                + "WHERE id='" + category.getId() + "'";
        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.execute();
    }

    public List<Category> search(String id) throws SQLException {

        List<Category> categoryList = new ArrayList<>();

//        String query = "SELECT * FROM products where product_name LIKE '%"+name+"%'";
        String query = "SELECT * FROM categories "
                + "WHERE id='" + id + "' or category LIKE'" + id + "%'";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            Category category = new Category(rs.getInt("id"), rs.getString("category_name"),rs.getString("create_at"));
            categoryList.add(category);
        }
        return categoryList;

    }
    
}
