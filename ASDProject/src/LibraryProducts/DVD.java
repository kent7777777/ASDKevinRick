/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryProducts;

import Framework.Physical;

/**
 *
 * @author coolk
 */
public class DVD extends Physical{
    
    public DVD(String productIdentifier, String productName, double costToStock, double price) {
        super(productIdentifier, productName, costToStock, price);
    }
    
}
