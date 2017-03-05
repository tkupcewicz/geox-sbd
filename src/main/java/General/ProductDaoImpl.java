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
                    rs.getString("publisher"),
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
                        rs.getString("publisher"),
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
}
