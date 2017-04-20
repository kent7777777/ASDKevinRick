/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.sql.Date;

/**
 *
 * @author coolk
 */
public class Item implements IData{
    private int id;
    private Product product;
    private User owner;
    private Date dateOut;
    private Date dateDue;

    public Item(int id, Product product, User owner) {
        this.id = id;
        this.product = product;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }
    
    
}
