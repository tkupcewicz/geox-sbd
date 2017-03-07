package Interfaces;

import GraphicalInterface.Categories;
import Objects.Product;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tymek on 04.03.2017.
 */
public interface ProductDao {
    public ArrayList<Product> getAllProducts() throws SQLException;
    public Product getProduct(int id);
    public void updateProduct(Product product);
    public void deleteProduct(Product product);
    public Boolean reduceQuantity(int id);
    public Boolean createProduct(String title, String genre, String date,
                                 String artist, String imgURL, int quantity, String price);
    public Boolean createArtist(String name, String date, String desc);
}
