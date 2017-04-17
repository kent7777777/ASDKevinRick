/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.util.List;

/**
 *
 * @author coolk
 */
public abstract class User implements IUser {
    List<IProduct> products; //all product currently owned by the user
    ShoppingCart cart;
}
