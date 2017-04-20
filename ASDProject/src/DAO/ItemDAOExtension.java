/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Framework.DAO.ItemDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeerick
 */
public class ItemDAOExtension extends ItemDAO {

    public List<Item> findByProductIdentifier(Connection cn, String productidentifier) throws SQLException {
        String query = "SELECT * FROM item WHERE productidentifier = ?";
        ResultSet rs;
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, productidentifier);
        rs = st.executeQuery();
        List<Item> items = new ArrayList<>();
        while (rs.next()) {
            ProductDAOExtension pd = new ProductDAOExtension();
            UserDAOExtension ud = new UserDAOExtension();

            Product product = (Product)pd.findUnique(cn, rs.getString("productidentifier"));
            String username = rs.getString("username");
            User us = null;
            if (username != null) {
                us = (User)ud.findUnique(cn, username);
            }

            Item item = new Item(rs.getInt("id"), product, us);
            item.setDateDue(rs.getDate("dateout"));
            item.setDateOut(rs.getDate("datedue"));
            items.add(item);
        }
        return items;
    }

}
