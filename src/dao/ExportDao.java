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
            Export export = new Export(rs.getInt("id"), rs.getInt("export_price"),
                    rs.getString("create_at"));
            exportList.add(export);
        }
        return exportList;
    }

    @Override
    public boolean insert(Object ob) throws SQLException {
        Export export = new Export();
        export = (Export) ob;
        String query = "insert into export(price_export) values(?)";
        Connection connection = ConnectHelper.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, export.getPrice_export());
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
}
