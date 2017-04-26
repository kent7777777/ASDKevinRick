/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import DAO.DAOFacade;
import DAO.DBConnection;
import Framework.Admin;
import Framework.DAO.ItemDAO;
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
public class MemberStrategy implements TransactionStrategy {

    @Override
    public boolean rentItem(User user, Item item) {
        DAO.DAOFacade DF = new DAOFacade();
        try (Connection cn = DBConnection.getCon()) {
            Item temp = (Item) DF.ItemfindUnique(cn, Integer.toString(item.getId()));
            if (temp.getOwner() != null) {
                return false;
            } else {
                temp.setOwner((User) DF.UserfindUnique(cn, user.getUsername()));
                LocalDate now = LocalDate.now();
                temp.setDateOut(now);
                LocalDate due = now.plusDays(7);
                temp.setDateDue(due);
                DF.ItemupdateData(cn, temp);
                return true;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdminStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public double returnItem(User user, Item item) {
        DAOFacade DF = new DAOFacade();
        if (item.getOwner() != null && item.getOwner().getUsername().equals(user.getUsername())) {
            try (Connection cn = DBConnection.getCon()) {
                if (item.getDateDue().isAfter(LocalDate.now().plusDays(1))) {
                    item.setOwner(null);
                    item.setDateDue(null);
                    item.setDateOut(null);
                    DF.ItemupdateData(cn, item);
                    return 0;
                } else {
                    int intervalDays = (int) ChronoUnit.DAYS.between(item.getDateDue(), LocalDate.now());
                    item.setOwner(null);
                    item.setDateDue(null);
                    item.setDateOut(null);
                    DF.ItemupdateData(cn, item);
                    return intervalDays * 2;
                }
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(MemberStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        User us = new Admin("pigrick", null, "lai_yeerick@hotmail.com");
        Item item;
        Transaction t1 = new Transaction(us);
        try (Connection cn = DBConnection.getCon()) {
            ItemDAO ids = new ItemDAO();
            item = (Item) ids.findUnique(cn, "1");

            //System.out.println(t1.getStrategy().rentItem(us, item));
            System.out.println(t1.getStrategy().returnItem(us, item));

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
