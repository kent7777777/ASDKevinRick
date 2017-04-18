/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryProducts.LibraryFatories;

import Framework.Admin;
import Framework.Factories.UserFactory;
import Framework.Member;
import Framework.Staff;
import Framework.User;

/**
 *
 * @author coolk
 */
public class LibraryUserFactory implements UserFactory{

    @Override
    public User createUser(String type, String username, String password, String email) {
        User user = null;
        
        switch (type) {
            case "Admin":
                user = new Admin(username, password, email);
                break;
            case "Staff":
                user = new Staff(username, password, email);
                break;
            case "Member":
                user = new Member(username, password, email);
                break;
            default:
                break;
        }
        
        return user;
    }
    
}
