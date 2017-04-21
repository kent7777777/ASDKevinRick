/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DBConnection;
import DAO.UserDAOExtension;
import Framework.Factories.UserFactory;
import Framework.User;
import LibraryProducts.LibraryFatories.LibraryUserFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coolk
 */
public class UsersController {
    UserFactory factory;
    
    public UsersController(){
        factory = new LibraryUserFactory();
        
    }
    
    public User[] getUsers(){
        return new User[10]; //TODO implement getUser
    }
    
    public boolean addUser(User user) throws SQLException{
        UserDAOExtension id = new UserDAOExtension();
        try(Connection cn = DBConnection.getCon()){
            id.AddData(cn, user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true; //TODO implement addUser
    }
    
    public boolean deleteUser(User user){
        return true; //TODO implement deleteUser
    }
}
