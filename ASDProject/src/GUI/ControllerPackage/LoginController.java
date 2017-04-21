/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DBConnection;
import DAO.UserDAOExtension;
import Framework.PasswordAuthentication.PasswordAuthentificationChainBuilder;
import Framework.User;
import GUI.GUIController;
import asdproject.ASDProject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coolk
 */
public class LoginController {

    public boolean login(String username, String password) {
        User user = null;
        UserDAOExtension ud = new UserDAOExtension();
        PasswordAuthentificationChainBuilder pa = new PasswordAuthentificationChainBuilder();
        try (Connection cn = DBConnection.getCon()) {
            String token = ud.getToken(cn, username);
            if (token == null) {
                return false;
            } else {
                System.out.println(password);
                System.out.println(token);
                String result = pa.getHandler().handleRequest(password, token);
                if (result.equals("right")) {
                    user = (User) ud.findUniqueWithCartAndOwned(cn, username);
                    GUIController.getController().setUser(user);
                } else {
                    return false;
                }
            }

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ASDProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GUIController.getController().setUsername();
        return true; //TODO implement login
    }

}
