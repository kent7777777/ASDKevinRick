/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author yeerick
 */
public class DBConnection {
    
    private static DBConnection instance = null;
    private final String URL = "jdbc:mysql://localhost:3306/KevinRickASD";
    private final String NAME = "root";
    private final String PASS = "mumsql";            
    
    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        } 
        return instance;
    }

    public Connection getCon() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cn = DriverManager.getConnection(URL, NAME, PASS);
        return cn;

    }

}
