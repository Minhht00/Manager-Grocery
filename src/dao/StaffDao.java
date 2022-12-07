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
import model.Staff;

/**
 *
 * @author A
 */
public class StaffDao implements DaoInterface {

    @Override
    public List<Object> findAll() throws SQLException {
        List<Object> staffList = new ArrayList<>();

//        String query = "SELECT * FROM products where status !=0";
        String query = "SELECT * FROM staff";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            Staff staff = new Staff(rs.getInt("id"), rs.getString("staff_name"),
                    rs.getString("birthday"), rs.getString("address"), rs.getString("create_at"),
                    rs.getString("phone"));
            staffList.add(staff);
        }
        return staffList;
    }

    @Override
    public boolean insert(Object ob) throws SQLException {
        Staff staff = new Staff();
        staff = (Staff) ob;
        String query = "insert into staff(staff_name,birthday,address,phone) values(?,?,?,?)";
        Connection connection = ConnectHelper.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, staff.getStaffName());
        pstmt.setString(2, staff.getBirthDay());
        pstmt.setString(3, staff.getAddress());
        pstmt.setString(4, staff.getPhone());
        return pstmt.execute();
    }
//

    @Override
    public boolean update(Object ob) throws SQLException {
        Staff staff = (Staff) ob;
//        String query = "UPDATE products SET name=?,catalog_id=?, price=? "
//                + "WHERE id='" + fd.getId() + "'";
        String query = "UPDATE staff SET staff_name=?, birthday=?, address=?, phone=? "
                + "WHERE id='" + staff.getId() + "'";
        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, staff.getStaffName());
        pstmt.setString(2, staff.getBirthDay());
        pstmt.setString(3, staff.getAddress());
        pstmt.setString(4, staff.getPhone());

        return pstmt.execute();
    }


    @Override
    public boolean delete(Object ob) throws SQLException {
        Staff staff = new Staff();
        staff = (Staff) ob;
        String query = "delete from staff "
                + "WHERE id='" + staff.getId() + "'";
        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.execute();
    }
//
//    public List<Staff> search(String id) throws SQLException {
//
//        List<Staff> staffList = new ArrayList<>();
//
////        String query = "SELECT * FROM products where product_name LIKE '%"+name+"%'";
//        String query = "SELECT * FROM products "
//                + "WHERE id='" + id + "' or product_name LIKE'" + id + "%'";
//
//        Connection conn = ConnectHelper.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(query);
//        ResultSet rs = pstmt.executeQuery(query);
//        while (rs.next()) {
////            Staff staff = new Staff(rs.getInt("id"), rs.getString("product_name"),
////                    rs.getInt("catalog_id"), rs.getInt("price")
////                    , rs.getInt("status"));
//            Staff staff = new Staff(rs.getInt("id"), rs.getString("product_name"),
//                    rs.getInt("catalog_id"), rs.getInt("price"), rs.getInt("amount"),
//                     rs.getString("create_at"));
//            staffList.add(staff);
//        }
//        return staffList;
//
//    }
//
//    public List<Staff> getQuantityById(int id) throws SQLException {
//
//        List<Staff> staffList = new ArrayList<>();
//
////        String query = "SELECT * FROM products where product_name LIKE '%"+name+"%'";
//        String query = "SELECT amount FROM products "
//                + "WHERE id='" + id + "'";
//
//        Connection conn = ConnectHelper.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(query);
//        ResultSet rs = pstmt.executeQuery(query);
//        while (rs.next()) {
////            Staff staff = new Staff(rs.getInt("id"), rs.getString("product_name"),
////                    rs.getInt("catalog_id"), rs.getInt("price")
////                    , rs.getInt("status"));
//            Staff staff = new Staff(rs.getInt("amount"));
//            staffList.add(staff);
//        }
//        return staffList;

//    }
}
