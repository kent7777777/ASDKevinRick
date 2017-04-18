/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.PasswordAuthentication;

/**
 *
 * @author yeerick
 */
public class ValidationAgent extends AbstractAgent{

    
    @Override
    public String handleRequest(String password, String token) {
        if(password.length() < 8 && password.length() > 32){
            return "invalid";
        } else {
            return nextAgent.handleRequest(password, token);
        }
    }
    
}
