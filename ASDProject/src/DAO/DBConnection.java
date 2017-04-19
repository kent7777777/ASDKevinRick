/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeerick
 */
public class DBConnection {
    public static Connection getCon() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/KevinRickASD", "root", "mumsql");
        return cn;

    }
    
}
