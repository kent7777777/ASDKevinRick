/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.Factories;

import Framework.User;

/**
 *
 * @author coolk
 */
public interface UserFactory {

    public User createUser(String type, String username, String password, String email);
}
