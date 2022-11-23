/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lexuanmuoi
 */
public interface DaoInterface {   
    public boolean insert(Object ob) throws SQLException;
    public List<Object>findAll()throws SQLException;
    public boolean update(Object ob) throws SQLException;
    public boolean delete(Object ob) throws SQLException;

}
