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
public class PasswordAuthentificationChainBuilder {

    private AbstractAgent handler;

    public AbstractAgent getHandler() {
        return handler;
    }

    public PasswordAuthentificationChainBuilder() {
        buildChain();
    }

    private void buildChain() {
        AbstractAgent validator = new ValidationAgent();
        AbstractAgent hasher = new HashAgent();
        AbstractAgent authenficater = new AuthentificationAgent();
        validator.nextAgent = hasher;
        hasher.nextAgent = authenficater;
        handler = validator;
    }

    public static void main(String[] args) {
        PasswordAuthentificationChainBuilder pb = new PasswordAuthentificationChainBuilder();
        String love = pb.getHandler().handleRequest("Jackyloveemily", null);
        System.out.println(love);
        System.out.println(pb.getHandler().handleRequest("Jackyloveemily", null));
        System.out.println(pb.getHandler().handleRequest("Jackyloasdmily", love));

    }
}
