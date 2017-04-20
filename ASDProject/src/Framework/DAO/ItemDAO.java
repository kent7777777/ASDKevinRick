/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.DAO;

import Framework.IData;
import Framework.Item;
import Framework.Product;
import Framework.User;
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
public class ItemDAO implements IDAO{

    @Override
    public List<IData> findALL(Connection cn) throws SQLException {
        String query = "SELECT * FROM item";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        rs = st.executeQuery();
        
        List<IData> items = new ArrayList<>();
        
        while (rs.next()) {            
            DAO.ProductDAOTEMP pd = new DAO.ProductDAOTEMP();
            DAO.UserDAOTemp ud = new DAO.UserDAOTemp();            
            Product product = pd.findByProductIdentifier(cn, rs.getString("productidentifier"));
            String username = rs.getString("username");
            User us = null;
            if(username != null){
                us = ud.findByUsername(cn, username);
            }
            
            Item item = new Item(rs.getInt("id"), product, us);
            items.add(item);
            item.setDateDue(rs.getDate("dateout"));
            item.setDateOut(rs.getDate("datedue"));
        }
        return items;
    }

    @Override
    public IData findUnique(Connection cn, String identifier) throws SQLException {
        String query = "SELECT * FROM item WHERE id = ?";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, Integer.parseInt(identifier));
        rs = st.executeQuery();
        
        while (rs.next()) {            
            DAO.ProductDAOTEMP pd = new DAO.ProductDAOTEMP();
            DAO.UserDAOTemp ud = new DAO.UserDAOTemp();
            
            Product product = pd.findByProductIdentifier(cn, rs.getString("productidentifier"));
            String username = rs.getString("username");
            User us = null;
            if(username != null){
                us = ud.findByUsername(cn, username);
            }
            
            Item item = new Item(rs.getInt("id"), product, us);
            item.setDateDue(rs.getDate("dateout"));
            item.setDateOut(rs.getDate("datedue"));
            return item;
        }
        return null;
    }

    @Override
    public void AddData(Connection cn, IData data) throws SQLException {
        String query = "INSERT INTO item (id, productidentifier, username) VALUES (?, ?, ?)";
        PreparedStatement st = cn.prepareStatement(query);
        Item item = (Item)data;
        st.setInt(1, item.getId());
        st.setString(2, item.getProduct().getProductIdentifier());
        if(item.getOwner() != null){
            st.setString(3, item.getOwner().getUsername());
        } else {
            st.setString(3, null);
        }
        st.execute();
    }

    @Override
    public void deleteData(Connection cn, String identifier) throws SQLException {
        String query = "DELETE FROM item WHERE id = ?";
        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, Integer.parseInt(identifier));
        st.execute();
    }

    @Override
    public void updateData(Connection cn, IData data) throws SQLException {
        String query = "UPDATE item SET username = ?, dateout = ?, datedue = ? WHERE id = ?";
        PreparedStatement st = cn.prepareStatement(query);
        Item item = (Item)data;
        st.setString(1, item.getOwner().getUsername());
        st.setDate(2, item.getDateOut());
        st.setDate(3, item.getDateDue());
        st.setInt(4, item.getId());
        st.execute();        
    }
}
