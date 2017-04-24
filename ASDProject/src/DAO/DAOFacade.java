/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Framework.IData;
import Framework.Item;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yeerick
 */
public class DAOFacade {

    private ItemDAOExtension itemDAOExtension;
    private ProductDAOExtension productDAOExtension;
    private UserDAOExtension userDAOExtension;

    public DAOFacade() {
        itemDAOExtension = new ItemDAOExtension();
        productDAOExtension = new ProductDAOExtension();
        userDAOExtension = new UserDAOExtension();
    }

    public List<IData> UserfindALL(Connection cn) throws SQLException {
        return userDAOExtension.findALL(cn);
    }

    public IData UserfindUnique(Connection cn, String identifier) throws SQLException {
        return userDAOExtension.findUnique(cn, identifier);
    }

    public void UserAddData(Connection cn, IData data) throws SQLException {
        userDAOExtension.AddData(cn, data);
    }

    public void UserdeleteData(Connection cn, String identifier) throws SQLException {
        userDAOExtension.deleteData(cn, identifier);
    }

    public void UserupdateData(Connection cn, IData data) throws SQLException {
        userDAOExtension.updateData(cn, data);
    }

    public String UserGetToken(Connection cn, String username) throws SQLException {
        return userDAOExtension.getToken(cn, username);
    }

    public IData UserfindUniqueWithCartAndOwned(Connection cn, String identifier) throws SQLException {
        return userDAOExtension.findUniqueWithCartAndOwned(cn, identifier);
    }

    public void addCart(Connection cn, String username, int itemid) throws SQLException {
        userDAOExtension.addCart(cn, username, itemid);
    }

    public void deleteCart(Connection cn, String username, int itemid) throws SQLException {
        userDAOExtension.deleteCart(cn, username, itemid);
    }

    public List<IData> ProductfindALL(Connection cn) throws SQLException {
        return productDAOExtension.findALL(cn);
    }

    public IData ProductfindUnique(Connection cn, String identifier) throws SQLException {
        return productDAOExtension.findUnique(cn, identifier);
    }

    public void ProductAddData(Connection cn, IData data) throws SQLException {
        productDAOExtension.AddData(cn, data);
    }

    public void ProductdeleteData(Connection cn, String identifier) throws SQLException {
        productDAOExtension.deleteData(cn, identifier);
    }

    public void ProductupdateData(Connection cn, IData data) throws SQLException {
        productDAOExtension.updateData(cn, data);
    }

    public List<IData> ItemfindALL(Connection cn) throws SQLException {
        return itemDAOExtension.findALL(cn);
    }

    public IData ItemfindUnique(Connection cn, String identifier) throws SQLException {
        return itemDAOExtension.findUnique(cn, identifier);
    }

    public void ItemAddData(Connection cn, IData data) throws SQLException {
        itemDAOExtension.AddData(cn, data);
    }

    public void ItemdeleteData(Connection cn, String identifier) throws SQLException {
        itemDAOExtension.deleteData(cn, identifier);
    }

    public void ItemupdateData(Connection cn, IData data) throws SQLException {
        itemDAOExtension.updateData(cn, data);
    }

    public List<Item> ItemfindByProductIdentifier(Connection cn, String productidentifier) throws SQLException {
        return itemDAOExtension.findByProductIdentifier(cn, productidentifier);
    }

}
