/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asdproject;

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
 * @author yeerick
 */
public class ASDProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserDAOExtension id = new UserDAOExtension();
        UserFactory factory = new LibraryUserFactory();
        User us = factory.createUser("Admin", "bob", "12345678", "coolbob7777777@gmail.com");
        try(Connection cn = DBConnection.getInstance().getCon()){
            id.AddData(cn, us);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ASDProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
