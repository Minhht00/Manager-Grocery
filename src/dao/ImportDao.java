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

/**
 *
 * @author A
 */
public class ImportDao implements DaoInterface {

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
        Import import2 = new Import();
        import2 = (Import) ob;
        String query = "insert into import(price_import, staff_id) values(?,?)";
        Connection connection = ConnectHelper.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, import2.getPrice_import());
        pstmt.setInt(2, import2.getStaffId());
        return pstmt.execute();
    }

    @Override
    public boolean update(Object ob) throws SQLException {

        return true;
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

    public List<ImportDetail> getById(int id) throws SQLException {

        List<ImportDetail> importDtList = new ArrayList<>();

        String query = "SELECT * FROM import_detail "
                + "WHERE import_id='" + id + "'";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            ImportDetail importDt = new ImportDetail(rs.getInt("id"), rs.getInt("import_id"),
                    rs.getInt("product_id"), rs.getInt("quantity"), rs.getInt("quantity_import"),
                    rs.getInt("price_import"), rs.getString("msg"), rs.getString("exp"), rs.getInt("status"),
                    rs.getString("create_at"));
            importDtList.add(importDt);
        }
        return importDtList;

    }
}
