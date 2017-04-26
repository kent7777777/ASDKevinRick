/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Framework.User;

/**
 *
 * @author yeerick
 */
public class Transaction {

    private TransactionStrategy strategy;

    public Transaction(User user) {
        if (user.getClass().getSimpleName().equals("Admin")) {
            strategy = new AdminStrategy();
        } else if (user.getClass().getSimpleName().equals("Staff")) {
            strategy = new StaffStrategy();
        } else {
            strategy = new MemberStrategy();
        }
    }

    public TransactionStrategy getStrategy() {
        return strategy;
    }

}
