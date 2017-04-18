/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryProducts.LibraryFatories;

import Framework.Digital;
import Framework.Factories.ProductFactory;
import Framework.Physical;
import Framework.Product;
import LibraryProducts.DVD;
import LibraryProducts.EDVD;

/**
 *
 * @author coolk
 */
public class DVDFactory implements ProductFactory{
    

    @Override
    public Digital createDigitalProduct(String productIdentifier, String productName, double costToStock, double price) {
        return new EDVD(productIdentifier, productName, costToStock, price);
    }

    @Override
    public Physical createPhysicalProduct(String productIdentifier, String productName, double costToStock, double price) {
        return new DVD(productIdentifier, productName, costToStock, price);

    }
    
}
