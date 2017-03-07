package General;

import Database.PostgresDB;
import Database.Query;
import GraphicalInterface.Categories;
import Interfaces.ProductDao;
import Objects.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tymek on 04.03.2017.
 */
public class ProductDaoImpl implements ProductDao {

    @Override
    public ArrayList<Product> getAllProducts() throws SQLException {
        String query = Query.getAllProducts();
        ResultSet rs = PostgresDB.getInstance().select(query).getSet();
        ArrayList<Product> products = new ArrayList<>();
        while(rs.next()){
            products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("title"),
                    Categories.valueOf(rs.getString("genre")),
                    rs.getDate("date"),
                    rs.getString("price"),
                    rs.getString("artist"),
                    rs.getString("img"),
                    rs.getInt("quantity")
            ));
        }
        return products;
    }

    @Override
    public Product getProduct(int id) {
        String query = Query.getProduct(id);
        ResultSet rs = PostgresDB.getInstance().select(query).getSet();
        Product product = null;
        try {
            while(rs.next()){
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("title"),
                        Categories.valueOf(rs.getString("genre")),
                        rs.getDate("date"),
                        rs.getString("price"),
                        rs.getString("artist"),
                        rs.getString("img"),
                        rs.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public Boolean reduceQuantity(int id) {
        String query = Query.reduceAmountOfProduct(id);
        try {
            PostgresDB.getInstance().update(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean createProduct(String title, String genre, String date, String artist, String imgURL, int quantity, String price) {
        String query = Query.createProduct(title, genre, date, artist, imgURL, quantity, price);
        try {
            PostgresDB.getInstance().insert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean createArtist(String name, String date, String desc) {
        String query = Query.createArtist(name, date, desc);
        try {
            PostgresDB.getInstance().insert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
