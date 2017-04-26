/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DAOFacade;
import DAO.DBConnection;
import Framework.Item;
import Framework.User;
import GUI.GUIController;
import Strategy.Transaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coolk
 */
public class CartController {

    public boolean removeItem(Item item) {
        DAO.DAOFacade DF = new DAOFacade();

        try (Connection cn = DBConnection.getCon()) {
            DF.deleteCart(cn, GUIController.getController().getUser().getUsername(), item.getId());
            GUIController.getController().setUser(
                    (User) DF.UserfindUniqueWithCartAndOwned(
                            cn, GUIController.getController().getUser().getUsername()));
            GUIController.getController().getCart().updateCart(
                    GUIController.getController().getUser().getCart());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public String checkOut() {
        User user = GUIController.getController().getUser();
        List<Item> items = user.getCart().getCart();
        Transaction strategy = new Transaction(user);
        List<Integer> itemIds = new ArrayList<>();

        for (Item i : items) {
            strategy.getStrategy().rentItem(user, i);
            itemIds.add(i.getId());
        }

        DAO.DAOFacade DF = new DAOFacade();
        LocalDate dueDate = null;

        try (Connection cn = DBConnection.getCon()) {
            for (int i : itemIds) {
                DF.deleteCart(cn, user.getUsername(), i);
            }
            user = (User) DF.UserfindUniqueWithCartAndOwned(cn, user.getUsername());
            GUIController.getController().setUser(user);
            Item item = (Item) DF.ItemfindUnique(cn, String.valueOf(items.get(0).getId()));
            dueDate = item.getDateDue();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dueDate.toString();
    }
}
