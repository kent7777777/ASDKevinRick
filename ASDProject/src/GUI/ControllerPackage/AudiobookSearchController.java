/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import Framework.Product;
import LibraryProducts.AudioBook;

/**
 *
 * @author coolk
 */
public class AudiobookSearchController {
    
    public Object[] getAudiobooks(String audiobookName){
        return new Object[]{new AudioBook[10], new int[10]};
    }
    
    public Object[] getEAudiobooks(String audiobookName){
        return new Object[]{new AudioBook[10], new int[10]};
    }
    
    public boolean putAudiobookInCart(Product product){
        return true;
    }
}
