/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DBConnection;
import DAO.UserDAOExtension;
import Framework.Item;
import Framework.User;
import GUI.GUIController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coolk
 */
public class CartController {
    
    public boolean removeItem(Item item){
        UserDAOExtension id = new UserDAOExtension();
        
        try(Connection cn = DBConnection.getCon()){
            id.deleteCart(cn, GUIController.getController().getUser().getUsername(), item.getId());
            GUIController.getController().setUser(
                    (User) id.findUniqueWithCartAndOwned(
                            cn, GUIController.getController().getUser().getUsername()));
            GUIController.getController().getCart().updateCart(
                    GUIController.getController().getUser().getCart());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
}
