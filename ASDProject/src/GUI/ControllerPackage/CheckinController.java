/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DAOFacade;
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
        DAO.DAOFacade DF = new DAOFacade();
        
        User user = null;
        
        try(Connection cn = DBConnection.getCon()){
            user = (User) DF.UserfindUniqueWithCartAndOwned(cn, username);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public double CheckIn(Item item, String username){
        User user = null;
        DAO.DAOFacade DF = new DAOFacade();
        
        try(Connection cn = DBConnection.getCon()){
            user = (User) DF.UserfindUniqueWithCartAndOwned(cn, username);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(item.getProduct().getProductName());
        System.out.println(item.getId());
        Transaction strategy = new Transaction(user);
        double fine = strategy.getStrategy().returnItem(user, item);
        
        try(Connection cn = DBConnection.getCon()){
            GUIController.getController().setUser((User) DF.UserfindUniqueWithCartAndOwned(cn, user.getUsername()));
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fine;
    }
}
