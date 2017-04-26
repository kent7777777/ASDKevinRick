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
public interface IShoppingCart extends IData {

    public void addItem(Item item);

    public void deleteItem(Item item);

    public void clearCart();
}
