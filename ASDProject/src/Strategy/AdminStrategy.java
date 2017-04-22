/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import DAO.DBConnection;
import Framework.DAO.ItemDAO;
import Framework.DAO.UserDAO;
import Framework.Item;
import Framework.User;
import java.sql.Connection;
import java.sql.SQLException;

import java.time.LocalDate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeerick
 */
public class AdminStrategy implements TransactionStrategy {

    @Override
    public boolean rentBook(User user, Item item) {
        UserDAO ud = new UserDAO();
        ItemDAO itd = new ItemDAO();
        try (Connection cn = DBConnection.getCon()) {
            Item temp = (Item) itd.findUnique(cn, Integer.toString(item.getId()));
            if (temp.getOwner().getUsername() != null) {
                return false;
            } else {
                temp.setOwner((User) ud.findUnique(cn, temp.getOwner().getUsername()));
                LocalDate now = LocalDate.now();
                temp.setDateOut(now);
                LocalDate due = now.plusDays(7);
                temp.setDateDue(due);
                itd.updateData(cn, temp);
                return true;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdminStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public double returnBook(User user, Item item) {
        ItemDAO itd = new ItemDAO();
        try (Connection cn = DBConnection.getCon()) {
            
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdminStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}


