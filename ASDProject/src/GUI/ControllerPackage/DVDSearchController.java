/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ControllerPackage;

import Framework.Product;
import LibraryProducts.DVD;
import LibraryProducts.EDVD;

/**
 *
 * @author coolk
 */


public class DVDSearchController {
    
    //returns an object array with the first field being the product and the second being the number in stock
    public Object[] getDVD(String dvdName){
        return new Object[]{new DVD[10], new int[10]};
    }
    
    public Object[] getEDVD(String edvdName){
        return new Object[]{new EDVD[10], new int[10]};
    }
    
    public boolean putDVDInCart(Product product){
        return true;
    }
}
