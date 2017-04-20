/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.DAO;

import Framework.Factories.UserFactory;
import Framework.IData;
import Framework.PasswordAuthentication.PasswordAuthentificationChainBuilder;
import Framework.Permission;
import Framework.Product;
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
public class UserDAO implements IDAO {

    @Override
    public List<IData> findALL(Connection cn) throws SQLException {
        String query = "SELECT * FROM user";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        rs = st.executeQuery();
        
        List<IData> users = new ArrayList<>();
        
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

    @Override
    public IData findUnique(Connection cn, String identifier) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ?";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, identifier);
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

    @Override
    public void AddData(Connection cn, IData data) throws SQLException {
        String query = "INSERT INTO user (username, password, email, permission) VALUES (?, ?, ?, ?)";
        PreparedStatement st = cn.prepareStatement(query);
        User us = (User)data;
        st.setString(1, us.getUsername());
        st.setString(2, us.getPassword());
        st.setString(3, us.getEmail());
        if(us.getPermission() == Permission.HIGH){
            st.setString(4, "HIGH");
        } else if(us.getPermission() == Permission.MEDIUM){
            st.setString(4, "MEDIUM");
        } else {
            st.setString(4, "LOW");
        }
        st.execute();
    }

    @Override
    public void deleteData(Connection cn, String identifier) throws SQLException {
        String query = "DELETE FROM user WHERE username = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setString(1, identifier);
        ps.execute();
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

    @Override
    public void updateData(Connection cn, IData data) throws SQLException {
        String query = "UPDATE user SET password = ?, email = ?, permission = ? WHERE username = ?";
        PreparedStatement st = cn.prepareStatement(query);
        User user = (User)data;
        PasswordAuthentificationChainBuilder pa = new PasswordAuthentificationChainBuilder();
        String token = pa.getHandler().handleRequest(user.getPassword(), null);
        st.setString(1, token);
        st.setString(2, user.getEmail());
        st.setString(3, user.getPermission().toString());
        st.setString(4, user.getUsername());
        st.execute();  
    }
}
