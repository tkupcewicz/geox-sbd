package Interfaces;

import Objects.Product;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tymek on 04.03.2017.
 */
public interface ProductDao {
    public ArrayList<Product> getAllProducts() throws SQLException;
    public Product getProduct(int id);
    public void updateProduct(Product product);
    public void deleteProduct(Product product);
}
