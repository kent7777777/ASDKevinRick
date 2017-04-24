/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Framework.DAO.ItemDAO;
import Framework.DAO.UserDAO;
import Framework.Factories.UserFactory;
import Framework.IData;
import Framework.Item;
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
public class UserDAOExtension extends UserDAO {
    
    public IData findUniqueWithCartAndOwned(Connection cn, String identifier) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ?";
        String query2 = "SELECT * FROM shoppingcart WHERE username = ?";
        String query3 = "SELECT * FROM item WHERE username = ?";
        ResultSet rs;
        ResultSet rs2;
        ResultSet rs3;
        ItemDAO id = new ItemDAO();

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, identifier);
        rs = st.executeQuery();
        
        PreparedStatement st2 = cn.prepareStatement(query2);
        st2.setString(1, identifier);
        rs2 = st2.executeQuery();
        
        PreparedStatement st3 = cn.prepareStatement(query3);
        st3.setString(1, identifier);
        rs3 = st3.executeQuery();
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
            while(rs2.next()){
                us.getCart().addItem((Item)id.findUnique(cn, Integer.toString(rs2.getInt("itemid"))));
            }
            List<Item> items = new ArrayList<>();
            while(rs3.next()){
                Item item = (Item)id.findUnique(cn, rs3.getString("productidentifier"));
                items.add(item);
            }
            
            us.setOwnedItems(items);
            
            return us;
        }
        return null;
    }
    
    public void addCart(Connection cn, String username, int itemid) throws SQLException{
        String query = "INSERT INTO shoppingcart (username, itemid) VALUES (?, ?)";
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        st.setInt(2, itemid);
        st.execute();
    }
    
    public void deleteCart(Connection cn, String username, int itemid) throws SQLException{
        String query = "DELETE FROM shoppingcart WHERE (username = ? AND itemid = ?)";
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        st.setInt(2, itemid);
        st.execute();
    }
}
