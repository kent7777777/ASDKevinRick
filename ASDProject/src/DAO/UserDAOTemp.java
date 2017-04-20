/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Framework.Factories.UserFactory;
import Framework.Permission;
import Framework.User;
import LibraryProducts.LibraryFatories.LibraryUserFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yeerick
 */
public class UserDAOTemp {

    public User findByUsername(Connection cn, String username) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ?";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        rs = st.executeQuery();
        while (rs.next()) {
            UserFactory uf = new LibraryUserFactory();
            String permission = rs.getString("permission");
            User us;
            if(permission.equals("HIGH")){
                us = uf.createUser("Admin", rs.getString("username"), null, rs.getString("email"));
            } else if (permission.equals("MEDIUM")){
                us = uf.createUser("Staff", rs.getString("username"), null, rs.getString("email"));
            } else {
                us = uf.createUser("Member", rs.getString("username"), null, rs.getString("email"));
            }
            return us;
        }
        return null;
    }
    
    public List<User> findAll(Connection cn) throws SQLException {
        String query = "SELECT * FROM user";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        rs = st.executeQuery();
        
        List<User> users = new ArrayList<>();
        
        while (rs.next()) {
            UserFactory uf = new LibraryUserFactory();
            String permission = rs.getString("permission");
            User us;
            if(permission.equals("HIGH")){
                us = uf.createUser("Admin", rs.getString("username"), null, rs.getString("email"));
            } else if (permission.equals("MEDIUM")){
                us = uf.createUser("Staff", rs.getString("username"), null, rs.getString("email"));
            } else {
                us = uf.createUser("Member", rs.getString("username"), null, rs.getString("email"));
            }            
            users.add(us);
        }
        return users;
    }
      
    public String getToken(Connection cn, String username) throws SQLException {
        String sql = "SELECT password FROM user WHERE username = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        }
        return null;
    }
    
    public void addUser(Connection cn, Permission permission, String username, String token, String email) throws SQLException {
        String query = "INSERT INTO user (username, password, email, permission) VALUES (?, ?, ?, ?)";
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, token);
        st.setString(3, email);
        if(permission == Permission.HIGH){
            st.setString(4, "HIGH");
        } else if(permission == Permission.MEDIUM){
            st.setString(4, "MEDIUM");
        } else {
            st.setString(4, "LOW");
        }
        st.execute();
    }
    
    public void deleteUser(Connection cn, String username) throws SQLException {
        String query = "DELETE FROM user WHERE username = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setString(1, username);
        ps.execute();
    }
    
    
}
