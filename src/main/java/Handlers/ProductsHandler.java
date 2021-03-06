package Handlers;

import General.ProductDaoImpl;
import GraphicalInterface.Categories;
import Interfaces.ProductDao;
import Objects.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;


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

    public ArrayList<Product> filterProducts(Categories cat){
        if(cat.toString().equals("ALL")) return products;
        else return products.stream()
                .filter(product -> product.getGenre().equals(cat))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Product getProduct(int id){
        return this.productDao.getProduct(id);
    }

    public Boolean reduceProductAmount(int id){
        return this.productDao.reduceQuantity(id);
    }

    public Boolean createProduct(String title, String genre, String date,
                                 String artist, String imgURL, int quantity, String price){
        return this.productDao.createProduct(title, genre, date, artist, imgURL, quantity, price);
    }

    public Boolean createArtist(String name, String date, String desc){
        return this.productDao.createArtist(name, date, desc);
    }
}
