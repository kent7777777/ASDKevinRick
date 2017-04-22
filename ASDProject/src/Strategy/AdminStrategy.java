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
import java.time.temporal.ChronoUnit;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeerick
 */
public class AdminStrategy implements TransactionStrategy {

    @Override
    public boolean rentItem(User user, Item item) {
        UserDAO ud = new UserDAO();
        ItemDAO itd = new ItemDAO();
        try (Connection cn = DBConnection.getCon()) {
            Item temp = (Item) itd.findUnique(cn, Integer.toString(item.getId()));
            if (temp.getOwner() != null) {
                return false;
            } else {
                temp.setOwner((User) ud.findUnique(cn, user.getUsername()));
                LocalDate now = LocalDate.now();
                temp.setDateOut(now);
                LocalDate due = now.plusDays(28);
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
    public double returnItem(User user, Item item) {
       ItemDAO itd = new ItemDAO();
        if (item.getOwner() != null && item.getOwner().getUsername().equals(user.getUsername())) {
            try (Connection cn = DBConnection.getCon()) {
                if (item.getDateDue().isAfter(LocalDate.now().plusDays(1))) {
                    item.setOwner(null);
                    item.setDateDue(null);
                    item.setDateOut(null);
                    itd.updateData(cn, item);
                    return 0;
                } else {
                    int intervalDays = (int) ChronoUnit.DAYS.between(item.getDateDue(), LocalDate.now());
                    item.setOwner(null);
                    item.setDateDue(null);
                    item.setDateOut(null);
                    itd.updateData(cn, item);
                    return intervalDays * 0.5;
                }
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(MemberStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }
}


