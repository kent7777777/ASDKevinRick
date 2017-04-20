/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;


import Framework.User;
import GUI.GUIController;

/**
 *
 * @author coolk
 */
public class LoginController {
    
    public boolean login(String username, String password){
        User user = null;
        GUIController.getController().setUser(user);
        return true; //TODO implement login
    }
    
}
