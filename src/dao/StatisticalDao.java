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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jdk.dynalink.beans.StaticClass;
import model.Export;
import model.Product;

/**
 *
 * @author lexuanmuoi
 */
public class StatisticalDao {

    public List<Export> getStat(String dateForm, String dateTo) throws SQLException {

        List<Export> exportList = new ArrayList<>();

//        String query = "SELECT * FROM products where product_name LIKE '%"+name+"%'";
        String query = "SELECT Date(e.create_at) as date,SUM(e.price_export) as amount FROM export e WHERE e.create_at "
                + "BETWEEN '" + dateForm + "' AND '" + dateTo + "' GROUP BY Date(create_at)";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            Export export = new Export(rs.getString("date"), rs.getInt("amount"));
            exportList.add(export);
        }
        return exportList;

    }
    public List<Product> getStatProduct(String dateForm, String dateTo) throws SQLException {

        List<Product> statProductList = new ArrayList<>();

//        String query = "SELECT * FROM products where product_name LIKE '%"+name+"%'";
        String query = "SELECT p.id as id, p.product_name as product_name, Sum(quantity) as quantity from products p, export_detail ed "
                + "WHERE p.id = ed.product_id AND ed.create_at "
                + "BETWEEN '"+dateForm+"' AND '"+dateTo+"' GROUP BY product_id ORDER BY quantity DESC";

        Connection conn = ConnectHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        while (rs.next()) {
            Product product = new Product(rs.getInt("id"), rs.getString("product_name"), rs.getInt("quantity"));
            statProductList.add(product);
        }
        return statProductList;

    }

    public static void main(String[] args) throws ParseException, SQLException {
//            final String OLD_FORMAT = "dd-MM-yyyy";
//            final String NEW_FORMAT = "yyyy-MM-dd";
//
//            // August 12, 2010
//            String oldDateString1 = "12-11-2022";
//            String newDateString1;
//
//            String oldDateString2 = "31-12-2022";
//            String newDateString2;
//            
//            SimpleDateFormat sdf1 = new SimpleDateFormat(OLD_FORMAT);
//            sdf1.applyPattern(NEW_FORMAT);
//            
//            Date d1 = sdf1.parse(oldDateString1);
//            newDateString1 = sdf1.format(d1);
//            System.out.println(newDateString1);
//            
//             SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT);
//            sdf2.applyPattern(NEW_FORMAT);
//            Date d2 = sdf2.parse(oldDateString2);
//            newDateString2 = sdf2.format(d2);
//            
//            
//            System.out.println(newDateString2);
//            StatisticalDao s = new StatisticalDao();
//            List<Export> exportList = s.getStat(newDateString1, newDateString2);
//           
//            System.out.println(exportList);
        final String OLD_FORMAT = "dd-MM-yyyy";
        final String NEW_FORMAT = "yyyy-MM-dd";

        // August 12, 2010
        String oldDateString1 = "11-11-2022";
        String newDateString1;

        SimpleDateFormat sdf1 = new SimpleDateFormat(OLD_FORMAT);
        Date d1 = sdf1.parse(oldDateString1);
        sdf1.applyPattern(NEW_FORMAT);
        newDateString1 = sdf1.format(d1);
       
        String oldDateString2 = "31-12-2022";
        String newDateString2;

        SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT);
        Date d2 = sdf2.parse(oldDateString2);
        sdf2.applyPattern(NEW_FORMAT);
        newDateString2 = sdf2.format(d2);
        

        StatisticalDao s = new StatisticalDao();
        List<Export> exportList = s.getStat(newDateString1, newDateString2);

        for (Export e : exportList) {
            System.out.println(e.getCreate_at()+ "-"+ e.getPrice_export());
        }
    }
}
