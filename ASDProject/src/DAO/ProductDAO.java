/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Framework.Factories.ProductFactory;
import Framework.Factories.UserFactory;
import Framework.PasswordAuthentication.PasswordAuthentificationChainBuilder;
import Framework.Permission;
import Framework.Product;
import LibraryProducts.LibraryFatories.AudioBookFactory;
import LibraryProducts.LibraryFatories.BookFactory;
import LibraryProducts.LibraryFatories.DVDFactory;
import LibraryProducts.LibraryFatories.LibraryUserFactory;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeerick
 */
public class ProductDAO {
    
    public List<Product> findAll(Connection cn) throws SQLException {
        String query = "SELECT * FROM user";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        rs = st.executeQuery();
        
        List<Product> products = new ArrayList<>();
        
        while (rs.next()) {            
            String category = rs.getString("category");
            ProductFactory pf;
            Product product;
            if(category.equals("EBook")){
                pf = new BookFactory();
                product = pf.createDigitalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("EAudioBook")){
                pf = new AudioBookFactory();
                product = pf.createDigitalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("EDVD")){
                pf = new DVDFactory();
                product = pf.createDigitalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("Book")){
                pf = new DVDFactory();
                product = pf.createPhysicalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("AudioBook")){
                pf = new DVDFactory();
                product = pf.createPhysicalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else {
                pf = new DVDFactory();
                product = pf.createPhysicalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            }           
            products.add(product);
        }
        return products;
    }
    
    public void addProduct(Connection cn, String category, String productIdentifier, String productName, double costToStock, double price) throws SQLException {
        String query = "INSERT INTO product (productidentifier, productname, costtostock, price, category) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, productIdentifier);
        st.setString(2, productName);
        st.setDouble(3, costToStock);
        st.setDouble(4, price);
        st.setString(5, category);
        st.execute();
    }
    
    public void deleteProduct(Connection cn, String productIdentifier) throws SQLException {
        String query = "DELETE FROM product WHERE productidentifier = ?";
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, productIdentifier);
        st.execute();
    }
    
    public static void main(String[] args) throws SQLException {
        try (Connection con = DBConnection.getCon()) {
            //ProductDAO pd = new ProductDAO();
            UserDAO ud = new UserDAO();
            PasswordAuthentificationChainBuilder pa = new PasswordAuthentificationChainBuilder();
            String token = pa.getHandler().handleRequest("123456789", null);
            ud.addUser(con, Permission.HIGH, "kent", token, "kevin@gmail.com");
            //pd.addProduct(con, "EBook", "00001", "Over the Rainbow", 20.0, 25.0);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
