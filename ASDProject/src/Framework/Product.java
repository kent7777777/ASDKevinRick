/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coolk
 */
public abstract class Product implements IProduct {
    private String productIdentifier;
    private String productName;
    private double costToStock;
    private double price;
    
    

    public Product(String productIdentifier, String productName, double costToStock, double price) {
        this.productIdentifier = productIdentifier;
        this.productName = productName;
        this.costToStock = costToStock;
        this.price = price;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public String getProductName() {
        return productName;
    }
    
    public double getCostToStock() {
        return costToStock;
    }

    public void setCostToStock(double costToStock) {
        this.costToStock = costToStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
