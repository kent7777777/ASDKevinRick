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
import Strategy.Transaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coolk
 */
public class CheckinController {
    
    public User getUser(String username){
        UserDAOExtension id = new UserDAOExtension();
        User user = null;
        
        try(Connection cn = DBConnection.getCon()){
            user = (User) id.findUniqueWithCartAndOwned(cn, username);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public void CheckIn(Item item){
        User user = item.getOwner();
        Transaction strategy = new Transaction(user);
        strategy.getStrategy().returnItem(user, item);
        
        UserDAOExtension id = new UserDAOExtension();
        
        try(Connection cn = DBConnection.getCon()){
            GUIController.getController().setUser((User) id.findUniqueWithCartAndOwned(cn, user.getUsername()));
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
