/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.time.LocalDate;



/**
 *
 * @author coolk
 */
public class Item implements IData{
    private int id;
    private Product product;
    private User owner;
    private LocalDate dateOut;
    private LocalDate dateDue;

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

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }
    
    
}
