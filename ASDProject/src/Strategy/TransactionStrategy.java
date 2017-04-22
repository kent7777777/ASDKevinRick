/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Framework.Item;
import Framework.User;

/**
 *
 * @author yeerick
 */
public interface TransactionStrategy{
    
    public boolean rentBook(User user, Item item);
    public double returnBook(User user, Item item);
}
