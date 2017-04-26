/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.Factories;

import Framework.Digital;
import Framework.Physical;

/**
 *
 * @author coolk
 */
public interface ProductFactory {

    public Digital createDigitalProduct(String productIdentifier, String productName, double costToStock, double price);

    public Physical createPhysicalProduct(String productIdentifier, String productName, double costToStock, double price);
}
