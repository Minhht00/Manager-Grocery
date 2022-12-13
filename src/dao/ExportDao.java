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
import model.Export;
import model.ExportDetail;

/**
 *
 * @author A
 */
public class ExportDao implements DaoInterface {

    @Override
    public List<Object> findAll() throws SQLException {
        List<Object> exportList = new ArrayList<>();

//        String query = "SELECT * FROM products where status !=0";
        String query = "SELECT * FROM export";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            Export export = new Export(rs.getInt("id"), rs.getInt("price_export"),
                    rs.getString("create_at"));
            exportList.add(export);
        }
        return exportList;
    }

    @Override
    public boolean insert(Object ob) throws SQLException {
        Export export = new Export();
        export = (Export) ob;
        String query = "insert into export(price_export, staff_id) values(?,?)";
        Connection connection = ConnectHelper.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, export.getPrice_export());
        pstmt.setInt(2, export.getStaffId());

        return pstmt.execute();
    }

    @Override
    public boolean update(Object ob) throws SQLException {
//      Product product = new Product();
//        product = (Product) ob;
// String query = "UPDATE products SET product_name=?, price=?, amount=? "
//                + "WHERE id='" + product.getId() + "'";
//        Connection conn = ConnectHelper.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(query);
//        pstmt.setString(1, product.getProductName());
//        pstmt.setInt(2, product.getPrice());
//        pstmt.setInt(3, product.getAmount());
//       
//        return pstmt.execute();
        return true;
    }

    @Override
    public boolean delete(Object ob) throws SQLException {
        Export export = new Export();
        export = (Export) ob;
        String query = "delete from export "
                + "WHERE id='" + export.getId() + "'";
        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.execute();
    }
    
    public List<ExportDetail> getById(int id) throws SQLException {
       
        List<ExportDetail> exportDtList = new ArrayList<>();   
  
//        String query = "SELECT * FROM products where product_name LIKE '%"+name+"%'";
        String query = "SELECT * FROM export_detail "
                + "WHERE export_id='" + id + "'" ;

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
//            Product product = new Product(rs.getInt("id"), rs.getString("product_name"),
//                    rs.getInt("catalog_id"), rs.getInt("price")
//                    , rs.getInt("status"));
             ExportDetail exportDt = new ExportDetail(rs.getInt("id"), rs.getInt("export_id"),
                    rs.getInt("product_id"), rs.getInt("quantity"),rs.getString("create_at"));
            exportDtList.add(exportDt);
        }
        return exportDtList;

    }
}
