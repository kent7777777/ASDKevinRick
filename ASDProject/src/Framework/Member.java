/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

/**
 *
 * @author coolk
 */
public class Member extends User{
    
    public Member(String username, String password, String email) {
        super(username, password, email);
        super.setPermission(Permission.LOW);
    }
    
}
