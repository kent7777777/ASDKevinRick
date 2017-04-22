/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import DAO.DBConnection;
import DAO.ItemDAOExtension;
import Framework.IData;
import Framework.Item;
import Framework.Physical;
import Framework.Product;
import LibraryProducts.Book;
import LibraryProducts.EBook;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coolk
 */
public class BookSearchController {
    
    //returns an object array with the first field being the product and the second being the number in stock
    public Item[] getBooks(){
        ItemDAOExtension id = new ItemDAOExtension();
        List<IData> data = null;
        
        try(Connection cn = DBConnection.getCon()){
            data = id.findALL(cn);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BookSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Item[] items = new Item[data.size()];
        
        for(int i = 0; i < data.size(); i++){
            items[i] = (Item) data.get(i);
        }
        
        
        
        return items;
    }
    
    public Object[] getEBooks(String ebookName){
        return new Object[]{new EBook[10], new int[10]};
    }
    
    public boolean putBookInCart(Product product){
        return true;
    }
}
