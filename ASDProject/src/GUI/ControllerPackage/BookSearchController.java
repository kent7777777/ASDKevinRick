/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import Framework.Item;
import Framework.Product;
import LibraryProducts.Book;
import LibraryProducts.EBook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coolk
 */
public class BookSearchController {
    
    
    public Object[] getBooks(String bookName){
        return new Object[]{new Book[]{new Book("1", "Book1", 1, 1), new Book("2", "Book2", 1, 1)}, new int[]{3, 8}};
    }
    
    public Object[] getEBooks(String ebookName){
        return new Object[]{new EBook[10], new int[10]};
    }
    
    public boolean putBookInCart(Product product){
        return true;
    }
}
