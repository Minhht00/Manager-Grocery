/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
//    public String password;

//    private Account(String username, String password) {
        private Account(int staffId, String staffName) {

        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.staffName = staffName;
        this.staffId = staffId;
//        this.password = password;
    }

    public static Account getInstance(int staffId,String staffName) {
        if (instance == null) {
            instance = new Account(staffId, staffName);
        }
        return instance;
    }
}