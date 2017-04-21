/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DBConnection;
import DAO.UserDAOExtension;
import Framework.Factories.UserFactory;
import Framework.IData;
import Framework.PasswordAuthentication.PasswordAuthentificationChainBuilder;
import Framework.User;
import LibraryProducts.LibraryFatories.LibraryUserFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
        User[] users = null;
        UserDAOExtension ud = new UserDAOExtension();
        try(Connection cn = DBConnection.getCon()){
            List<IData> idatas = ud.findALL(cn);
            users = new User[idatas.size()];
            for(int i = 0; i < idatas.size(); i++){
                users[i] = (User)idatas.get(i);
            }
            
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }
    
    public boolean addUser(User user) throws SQLException{
        PasswordAuthentificationChainBuilder pa = new PasswordAuthentificationChainBuilder();
        String validation = pa.getHandler().handleRequest(user.getPassword(), null);
        if(validation.equals("invalid")){
            return false;
        }
        
        UserDAOExtension id = new UserDAOExtension();
        try(Connection cn = DBConnection.getCon()){
            id.AddData(cn, user);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public boolean deleteUser(User user){
        UserDAOExtension id = new UserDAOExtension();
        try(Connection cn = DBConnection.getCon()){
            id.deleteData(cn, user.getUsername());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true; 
    }
}
