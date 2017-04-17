/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coolk
 */
public abstract class User implements IUser {
    private String username;
    private String password;
    private String email;
    private Permission permission;
    
    List<Item> ownedItems; //all products currently owned by the user
    ShoppingCart cart; //all products user is prepairing to buy

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        ownedItems = new ArrayList<>();
    }
    
    @Override
    public void Login(){
        //TODO implement login
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public List<Item> getItems() {
        return ownedItems;
    }

    public void setItems(List<Item> items){
        this.ownedItems = items;
    }
    
    
}
