/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author coolk
 */
public class GUIController {
    private Login login;
    private Homepage homepage;
    private BookSearch bookSearch;
    
    
    private static GUIController controller = new GUIController();
    
    private GUIController(){
        login = new Login();
        homepage = new Homepage();
        bookSearch = new BookSearch();
    }
    
    public static GUIController getController(){
        return controller;
    }
    

    public void loginOn(){
        login.setVisible(true);
    }
    
    public void loginOff(){
        login.setVisible(false);
    }
    
    public void homepageOn(){
        homepage.setVisible(true);
    }
    
    public void homepageOff(){
        homepage.setVisible(false);
    }
    
    public void bookSearchOn(){
        bookSearch.setVisible(true);
    }
    
    public void bookSearchOff(){
        bookSearch.setVisible(false);
    }
}
