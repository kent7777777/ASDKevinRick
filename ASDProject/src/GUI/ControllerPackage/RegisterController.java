/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import Framework.Factories.UserFactory;
import Framework.User;
import LibraryProducts.LibraryFatories.LibraryUserFactory;

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
        User user = factory.createUser("Member", username, password, email);
        return true;
    }
}
