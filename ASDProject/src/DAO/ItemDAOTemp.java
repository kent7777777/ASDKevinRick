/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Framework.Item;
import Framework.Product;
import Framework.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yeerick
 */
public class ItemDAOTemp {
    
    public List<Item> findAll(Connection cn) throws SQLException {
        String query = "SELECT * FROM item";
        ResultSet rs;
        PreparedStatement st = cn.prepareStatement(query);
        rs = st.executeQuery();
        
        List<Item> items = new ArrayList<>();
        
        while (rs.next()) {            
            ProductDAOTEMP pd = new ProductDAOTEMP();
            UserDAOTemp ud = new UserDAOTemp();
            
            Product product = pd.findByProductIdentifier(cn, rs.getString("productidentifier"));
            String username = rs.getString("username");
            User us = null;
            if(username != null){
                us = ud.findByUsername(cn, username);
            }
            
            Item item = new Item(rs.getInt("id"), product, us);
            LocalDate dateout = new Date(rs.getDate("dateout").getTime()).toLocalDate(); 
            item.setDateOut(dateout);
            LocalDate datedue = new Date(rs.getDate("datedue").getTime()).toLocalDate();
            item.setDateDue(datedue);   
            items.add(item);
        }
        return items;
    }
    
    public Item findById(Connection cn, int id) throws SQLException {
        String query = "SELECT * FROM item WHERE id = ?";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, id);
        rs = st.executeQuery();
        
        while (rs.next()) {            
            ProductDAOTEMP pd = new ProductDAOTEMP();
            UserDAOTemp ud = new UserDAOTemp();
            
            Product product = pd.findByProductIdentifier(cn, rs.getString("productidentifier"));
            String username = rs.getString("username");
            User us = null;
            if(username != null){
                us = ud.findByUsername(cn, username);
            }
            
            Item item = new Item(rs.getInt("id"), product, us);
            return item;
        }
        return null;
    }
    
    public void addItem(Connection cn, int id, String productIdentifier, String username) throws SQLException {
        String query = "INSERT INTO item (id, productidentifier, username) VALUES (?, ?, ?)";
        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, id);
        st.setString(2, productIdentifier);
        st.setString(3, username);
        st.execute();
    }
    
    public void deleteItem(Connection cn, int id) throws SQLException {
        String query = "DELETE FROM item WHERE id = ?";
        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, id);
        st.execute();
    }
    
    
}
