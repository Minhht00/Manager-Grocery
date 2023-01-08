/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import connect.ConnectHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lexuanmuoi
 */
//public class Account {
//    private Account(){}
//    
//    private static class AccountHolder{
//        private static final Account INSTANCE = new Account();
//    }
//    public static Account getInstance(){
//        return AccountHolder.INSTANCE;
//    }
//}
public final class Account {
    private static Account instance;
    public String staffName;
    public int staffId;
    public int position;
//    public String password;


//    private Account(String username, String password) {
        private Account(int staffId, String staffName, int position) {

        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.staffName = staffName;
        this.staffId = staffId;
        this.position = position;
//        this.password = password;
    }

    public static Account getInstance(int staffId,String staffName,int position) {
        if (instance == null) {
            instance = new Account(staffId, staffName, position);
        }
        return instance;
    }
}