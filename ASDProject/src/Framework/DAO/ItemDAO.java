/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.DAO;

import DAO.DBConnection;
import Framework.IData;
import Framework.Item;
import Framework.Product;
import Framework.User;
import LibraryProducts.Book;
import LibraryProducts.EBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeerick
 */
public class ItemDAO implements IDAO {

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
            if (username != null) {
                us = ud.findByUsername(cn, username);
            }

            Item item = new Item(rs.getInt("id"), product, us);
            items.add(item);
            if (rs.getDate("dateout") != null) {
                LocalDate dateout = new Date(rs.getDate("dateout").getTime()).toLocalDate();
                item.setDateOut(dateout);
            }
            if (rs.getDate("dateout") != null) {
                LocalDate datedue = new Date(rs.getDate("datedue").getTime()).toLocalDate();
                item.setDateDue(datedue);
            }
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
            if (username != null) {
                us = ud.findByUsername(cn, username);
            }

            Item item = new Item(rs.getInt("id"), product, us);
            if (rs.getDate("dateout") != null) {
                LocalDate dateout = new Date(rs.getDate("dateout").getTime()).toLocalDate();
                item.setDateOut(dateout);
            }
            if (rs.getDate("dateout") != null) {
                LocalDate datedue = new Date(rs.getDate("datedue").getTime()).toLocalDate();
                item.setDateDue(datedue);
            }
            return item;
        }
        return null;
    }

    @Override
    public void AddData(Connection cn, IData data) throws SQLException {
        String query = "INSERT INTO item (id, productidentifier, username) VALUES (?, ?, ?)";
        PreparedStatement st = cn.prepareStatement(query);
        Item item = (Item) data;
        st.setInt(1, item.getId());
        st.setString(2, item.getProduct().getProductIdentifier());
        if (item.getOwner() != null) {
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
        Item item = (Item) data;
        if (item.getOwner() == null) {
            st.setString(1, null);
        } else {
            st.setString(1, item.getOwner().getUsername());
        }
        if (item.getDateOut() == null) {
            st.setDate(2, null);
        } else {
            Date dateout = Date.valueOf(item.getDateOut());
            st.setDate(2, dateout);
        }
        if (item.getDateDue() == null) {
            st.setDate(3, null);
        } else {
            Date datedue = Date.valueOf(item.getDateDue());
            st.setDate(3, datedue);
        }
        st.setInt(4, item.getId());
        st.execute();
    }

    public static void main(String[] args) {
        LocalDate sd = LocalDate.now();
        System.out.println(sd.isBefore(LocalDate.now().plusDays(-1)));
        LocalDate sa = LocalDate.now().plusDays(30);
        long intervalDays = ChronoUnit.DAYS.between(sd, sa);
        Period intervalPeriod = Period.between(sd, sa);
        System.out.println(intervalDays);
//        try (Connection cn = DBConnection.getCon()) {
//            
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
