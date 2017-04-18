/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.PasswordAuthentication;

import java.security.SecureRandom;
import java.util.List;

/**
 *
 * @author yeerick
 */
public abstract class AbstractAgent {
    protected AbstractAgent nextAgent;
    
    static final String ID = "$31$";
    static final int cost = 16;
    static final int SIZE = 128;
    static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    static final SecureRandom random = new SecureRandom();
        
    public abstract String handleRequest(String password, String token);
    
    
}
