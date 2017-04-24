/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DBConnection;
import DAO.ItemDAOTemp;
import DAO.ProductDAOTEMP;
import Framework.Factories.ProductFactory;
import Framework.Product;
import LibraryProducts.LibraryFatories.AudioBookFactory;
import LibraryProducts.LibraryFatories.BookFactory;
import LibraryProducts.LibraryFatories.DVDFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coolk
 */
public class CreateFormController {
    
    
    
    public boolean createProduct(String title, String identifier, int cost, int price, boolean digital, String type, int number){
        ProductFactory factory = null;
        Product product;
        switch (type) {
            case "Book":
                factory = new BookFactory();
                break;
            case "Audiobook":
                factory = new AudioBookFactory();
                break;
            case "DVD":
                factory = new DVDFactory();
                break;
            default:
                return false;
        }
        
        if(digital){
            product = factory.createDigitalProduct(identifier, title, cost, price);
        }else{
            product = factory.createPhysicalProduct(identifier, title, cost, price);
        }
        
        ItemDAOTemp iid = new ItemDAOTemp();
        ProductDAOTEMP pid = new ProductDAOTEMP();
        
        try(Connection cn = DBConnection.getCon()){
            if(pid.findByProductIdentifier(cn, identifier) == null){
                pid.addProduct(cn, product.getClass().getSimpleName(), 
                        product.getProductIdentifier(), product.getProductName(),
                        product.getCostToStock(), product.getPrice());
            }
            iid.addItem(cn, number, identifier, null);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CreateFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return true;
    }
}
