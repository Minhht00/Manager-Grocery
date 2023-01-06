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
import model.Import;
import model.ImportDetail;
import model.Product;

/**
 *
 * @author A
 */
public class ImportDetailDao implements DaoInterface {

    @Override
    public List<Object> findAll() throws SQLException {
        List<Object> importList = new ArrayList<>();

        String query = "SELECT * FROM import";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            Import importL = new Import(rs.getInt("id"), rs.getInt("price_import"),
                    rs.getString("create_at"), rs.getInt("staff_id"));
            importList.add(importL);
        }
        return importList;
    }

    @Override
    public boolean insert(Object ob) throws SQLException {
//        Export export = new Export();
//        export = (Export) ob;
//        String query = "insert into export(price_export, staff_id) values(?,?)";
//        Connection connection = ConnectHelper.getConnection();
//        PreparedStatement pstmt = connection.prepareStatement(query);
//        pstmt.setInt(1, export.getPrice_export());
//        pstmt.setInt(2, export.getStaffId());

        return true;
    }

    @Override
    public boolean update(Object ob) throws SQLException {
        ImportDetail importDt = (ImportDetail) ob;

        String query = "UPDATE import_detail SET msg=?, exp=?, status=? "
                + "WHERE id='" + importDt.getId() + "'";
        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,importDt.getMsg());
        pstmt.setString(2, importDt.getExp());
        pstmt.setInt(3, importDt.getStatus());

        return pstmt.execute();
    }

    @Override
    public boolean delete(Object ob) throws SQLException {
//        Export export = new Export();
//        export = (Export) ob;
//        String query = "delete from export "
//                + "WHERE id='" + export.getId() + "'";
//        Connection conn = ConnectHelper.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(query);
//        return pstmt.execute();
        return true;
    }
}
