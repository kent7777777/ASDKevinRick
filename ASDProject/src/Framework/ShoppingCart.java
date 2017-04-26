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
public class ShoppingCart implements IShoppingCart {

    private List<Item> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void deleteItem(Item item) {
        items.remove(item);
    }

    public List<Item> getCart() {
        return items;
    }

    @Override
    public void clearCart() {
        items.clear();
    }
}
