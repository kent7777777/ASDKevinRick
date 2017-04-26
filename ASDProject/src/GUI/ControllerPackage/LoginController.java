/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DAOFacade;
import DAO.DBConnection;
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
        DAO.DAOFacade DF = new DAOFacade();

        PasswordAuthentificationChainBuilder pa = new PasswordAuthentificationChainBuilder();
        try (Connection cn = DBConnection.getInstance().getCon()) {
            String token = DF.UserGetToken(cn, username);
            if (token == null) {
                return false;
            } else {
                String result = pa.getHandler().handleRequest(password, token);
                if (result.equals("right")) {
                    user = (User) DF.UserfindUniqueWithCartAndOwned(cn, username);
                    GUIController.getController().setUser(user);
                } else {
                    return false;
                }
            }

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ASDProject.class.getName()).log(Level.SEVERE, null, ex);
        }

        GUIController.getController().setUsername();
        GUIController.getController().setPermission();
        GUIController.getController().getAccount().setInfo();
        return true;
    }
}
