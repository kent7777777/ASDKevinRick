/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Framework.User;

/**
 *
 * @author coolk
 */
public class GUIController {
    private Login login;
    private Homepage homepage;
    private BookSearch bookSearch;
    private AudiobookSearch audiobook;
    private DVDSearch dvdSearch;
    private Cart cart;
    private Users users;
    private Account account;
    
    private User user;
    
    
    private static GUIController controller = new GUIController();
    
    private GUIController(){
        login = new Login();
        homepage = new Homepage();
        bookSearch = new BookSearch();
        audiobook = new AudiobookSearch();
        dvdSearch = new DVDSearch();
        cart = new Cart();
        users = new Users();
        account = new Account();
    }
    
    public static GUIController getController(){
        return controller;
    }
    
    public void updateCart(){
        
    }
    
    
    public void start(){
        login.setVisible(true);
    }
    
    public void switchScene(GUIParent oldScene, GUIParent newScene){
        oldScene.setVisible(false);
        newScene.setVisible(true);
    }

    public Login getLogin() {
        return login;
    }

    public Homepage getHomepage() {
        return homepage;
    }

    public BookSearch getBookSearch() {
        return bookSearch;
    }

    public AudiobookSearch getAudiobook() {
        return audiobook;
    }

    public DVDSearch getDvdSearch() {
        return dvdSearch;
    }

    public Cart getCart() {
        return cart;
    }

    public Users getUsers() {
        return users;
    }

    public Account getAccount() {
        return account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
