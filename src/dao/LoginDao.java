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
import model.Account;

/**
 *
 * @author lexuanmuoi
 */
public class LoginDao {
    public boolean login(String username, String password) throws SQLException{
        
//        String query = "SELECT staff_name FROM staff "
//                + "WHERE username= '" + username + "' and password = '" + password+"'";
//String query = "SELECT staff_name FROM staff WHERE username = ? and password = ? ";

         Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement( "SELECT id,staff_name FROM staff WHERE username = ? and password = ? ");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        
        if(rs.next()){
           Account account = Account.getInstance(rs.getInt("id"),rs.getString("staff_name")); 
            System.out.println("dang nhap thanh cong");
            return true;
        }
        System.out.println("dang nhap khong thanh cong");
        return false;
    }
}
