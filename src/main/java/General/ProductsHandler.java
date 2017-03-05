package General;

import Interfaces.ProductDao;
import Objects.Product;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tymek on 03.03.2017.
 */
public class ProductsHandler {
    private static ProductsHandler ourInstance = new ProductsHandler();
    private ProductDao productDao;

    public static ProductsHandler getInstance() {
        return ourInstance;
    }

    private ArrayList<Product> products;


    private ProductsHandler() {
        this.productDao = new ProductDaoImpl();

    }

    public ArrayList<Product> getAllProducts() throws SQLException {
        this.products = this.productDao.getAllProducts();
        return products;
    }

    public Product getProduct(int id){
        return this.productDao.getProduct(id);
    }
}
