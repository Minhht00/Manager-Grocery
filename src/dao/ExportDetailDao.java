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
import model.ExportDetail;

/**
 *
 * @author A
 */
public class ExportDetailDao implements DaoInterface {

    @Override
    public List<Object> findAll() throws SQLException {
        List<Object> exportDtList = new ArrayList<>();

//        String query = "SELECT * FROM products where status !=0";
        String query = "SELECT * FROM export_detail";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            ExportDetail exportDt = new ExportDetail(rs.getInt("id"), rs.getInt("export_id"),
                    rs.getInt("product_id"), rs.getInt("quantity"),
                     rs.getString("create_at"));
            exportDtList.add(exportDt);
        }
        return exportDtList;
    }

    @Override
    public boolean insert(Object ob) throws SQLException {
        ExportDetail exportDt = new ExportDetail();
        exportDt = (ExportDetail) ob;
        String query = "insert into export_detail(export_id,product_id,quantity) values(?,?,?)";
        Connection connection = ConnectHelper.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, exportDt.getExportId());
        pstmt.setInt(2, exportDt.getProductId());
        pstmt.setInt(3, exportDt.getQuantity());
        return pstmt.execute();
    }

    @Override
    public boolean update(Object ob) throws SQLException {
//      Product product = new Product();
//        product = (Product) ob;
////        String query = "UPDATE products SET name=?,catalog_id=?, price=? "
////                + "WHERE id='" + fd.getId() + "'";
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
        ExportDetail exportDt = new ExportDetail();
        exportDt = (ExportDetail) ob;
        String query = "delete from export_detail "
                + "WHERE id='" + exportDt.getId() + "'";
        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.execute();
    }
}
