/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DBConnection;
import DAO.UserDAOExtension;
import Framework.Factories.UserFactory;
import Framework.PasswordAuthentication.PasswordAuthentificationChainBuilder;
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
public class RegisterController {
    UserFactory factory;
    
    public RegisterController(){
        factory = new LibraryUserFactory();
    }
    
    public boolean createMember(String username, String password, String email){
        PasswordAuthentificationChainBuilder pa = new PasswordAuthentificationChainBuilder();
        String validation = pa.getHandler().handleRequest(password, null);
        if(validation.equals("invalid")){
            return false;
        }
        
        User user = factory.createUser("Member", username, password, email);
        UserDAOExtension id = new UserDAOExtension();
        
        try(Connection cn = DBConnection.getCon()){
            id.AddData(cn, user);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
}