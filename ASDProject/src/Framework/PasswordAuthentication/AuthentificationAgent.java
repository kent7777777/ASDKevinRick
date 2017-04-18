/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.PasswordAuthentication;

import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yeerick
 */
public class AuthentificationAgent extends AbstractAgent {

    private static final Pattern layout = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.{43})");

    @Override
    public String handleRequest(String password, String token) {
        char[] pass = password.toCharArray();
        Matcher m = layout.matcher(token);
        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid token format");
        }
        int iterations = iterations(Integer.parseInt(m.group(1)));
        byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
        byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
        byte[] check = HashAgent.pbkdf2(pass, salt, iterations);
        int zero = 0;
        for (int idx = 0; idx < check.length; ++idx) {
            zero |= hash[salt.length + idx] ^ check[idx];
        }

        if (zero == 0) {
            return "right";
        } else {
            return "wrong";
        }
    }

    private static int iterations(int cost) {
        if ((cost & ~0x1F) != 0) {
            throw new IllegalArgumentException("cost: " + cost);
        }
        System.out.println(1<<cost);
        return 1 << cost;
    }
}
