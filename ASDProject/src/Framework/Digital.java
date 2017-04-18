/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

/**
 *
 * @author coolk
 */
public abstract class Digital extends Product {
    
    public Digital(String productIdentifier, String productName, double costToStock, double price) {
        super(productIdentifier, productName, costToStock, price);
    }
    
}
