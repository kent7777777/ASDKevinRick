/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import Framework.User;

/**
 *
 * @author coolk
 */
public class UsersController {
    
    public User[] getUsers(){
        return new User[10]; //TODO implement getUser
    }
    
    public boolean addUser(String username, String password, String email){
        return true; //TODO implement addUser
    }
    
    public boolean deleteUser(User user){
        return true;
    }
}
