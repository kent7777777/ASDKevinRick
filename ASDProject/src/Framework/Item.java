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
public class Item implements IData{
    private int id;
    private Product product;
    private User owner;

    public Item(int id, Product product, User owner) {
        this.id = id;
        this.product = product;
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    
}
