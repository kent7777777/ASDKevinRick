/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.DAO;

import Framework.Factories.ProductFactory;
import Framework.IData;
import Framework.Product;
import LibraryProducts.AudioBook;
import LibraryProducts.Book;
import LibraryProducts.EAudioBook;
import LibraryProducts.EBook;
import LibraryProducts.EDVD;
import LibraryProducts.LibraryFatories.AudioBookFactory;
import LibraryProducts.LibraryFatories.BookFactory;
import LibraryProducts.LibraryFatories.DVDFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yeerick
 */
public class ProductDAO implements IDAO {

    @Override
    public List<IData> findALL(Connection cn) throws SQLException {
        String query = "SELECT * FROM product";
        String query2 = "SELECT * FROM item WHERE productidentifier = ?";
        ResultSet rs = null;
        ResultSet rs2 = null;
        PreparedStatement st = cn.prepareStatement(query);
        rs = st.executeQuery();

        PreparedStatement st2 = cn.prepareStatement(query2);

        List<IData> items = new ArrayList<>();

        while (rs.next()) {

            String category = rs.getString("category");
            ProductFactory pf;
            Product product;
            if (category.equals("EBook")) {
                pf = new BookFactory();
                product = pf.createDigitalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("EAudioBook")) {
                pf = new AudioBookFactory();
                product = pf.createDigitalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("EDVD")) {
                pf = new DVDFactory();
                product = pf.createDigitalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("Book")) {
                pf = new DVDFactory();
                product = pf.createPhysicalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("AudioBook")) {
                pf = new DVDFactory();
                product = pf.createPhysicalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else {
                pf = new DVDFactory();
                product = pf.createPhysicalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            }
            items.add(product);
        }
        return items;
    }

    @Override
    public IData findUnique(Connection cn, String identifier) throws SQLException {
        String query = "SELECT * FROM product WHERE productidentifier = ?";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, identifier);
        rs = st.executeQuery();
        while (rs.next()) {
            String category = rs.getString("category");
            ProductFactory pf;
            Product product;
            if (category.equals("EBook")) {
                pf = new BookFactory();
                product = pf.createDigitalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("EAudioBook")) {
                pf = new AudioBookFactory();
                product = pf.createDigitalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("EDVD")) {
                pf = new DVDFactory();
                product = pf.createDigitalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("Book")) {
                pf = new DVDFactory();
                product = pf.createPhysicalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else if (category.equals("AudioBook")) {
                pf = new DVDFactory();
                product = pf.createPhysicalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            } else {
                pf = new DVDFactory();
                product = pf.createPhysicalProduct(rs.getString("productidentifier"), rs.getString("productname"), rs.getDouble("costtostock"), rs.getDouble("price"));
            }
            return product;
        }
        return null;
    }

    @Override
    public void AddData(Connection cn, IData data) throws SQLException {
        String query = "INSERT INTO product (productidentifier, productname, costtostock, price, category) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = cn.prepareStatement(query);
        Product product = (Product) data;
        st.setString(1, product.getProductIdentifier());
        st.setString(2, product.getProductName());
        st.setDouble(3, product.getCostToStock());
        st.setDouble(4, product.getPrice());
        if (product.getClass() == EBook.class) {
            st.setString(5, "Ebook");
        } else if (product.getClass() == EAudioBook.class) {
            st.setString(5, "EAudioBook");
        } else if (product.getClass() == EDVD.class) {
            st.setString(5, "EDVD");
        } else if (product.getClass() == Book.class) {
            st.setString(5, "Book");
        } else if (product.getClass() == AudioBook.class) {
            st.setString(5, "AudioBook");
        } else {
            st.setString(5, "DVD");
        }
        st.execute();
    }

    @Override
    public void deleteData(Connection cn, String identifier) throws SQLException {
        String query = "DELETE FROM product WHERE productidentifier = ?";
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, identifier);
        st.execute();
    }

    @Override
    public void updateData(Connection cn, IData data) throws SQLException {
        String query = "UPDATE product SET productname = ?, costtostock = ?, price = ? WHERE productidentifier = ?";
        PreparedStatement st = cn.prepareStatement(query);
        Product product = (Product) data;
        st.setString(1, product.getProductName());
        st.setDouble(2, product.getCostToStock());
        st.setDouble(3, product.getPrice());
        st.setString(4, product.getProductIdentifier());
        st.execute();
    }

}
