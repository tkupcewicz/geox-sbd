package Database;

import Objects.Review;
import Objects.User;

import java.util.Date;

/**
 * Created by Tymek on 04.03.2017.
 */
public class Query {
    public static String registerUser(String login, String password, String first_name, String second_name, String email,
                                    String address, String postal_code, String phone_number,
                                    Boolean manager){
        return String.format("INSERT INTO users (login, password, first_name, second_name, email, address, " +
                "postal_code, phone_number, manager) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                login, password, first_name, second_name, email, address, postal_code, phone_number, manager);
    }

    public static String verifyUser(String login){
        return String.format("SELECT password FROM users WHERE login = '%s';", login);
    }

    public static String getAllProducts(){
        return "SELECT * FROM products";
    }

    public static String getUser(String login) {
        return String.format("SELECT * FROM users WHERE login = '%s';", login);
    }

    public static String getProduct(int id){
        return String.format("SELECT * FROM products WHERE id = '%d';", id);
    }

    public static String getProductReviews(int id){
        return String.format("SELECT * FROM reviews WHERE product_id = '%d' ORDER BY reviews.date;", id);
    }

    public static String addReviews(Review review){
        return String.format("INSERT INTO reviews (date, content, product_id, \"user\") VALUES ('%s', '%s', '%s', '%s');",
                review.getDate(), review.getContent(), review.getProductId(), review.getUserLogin());
    }

    public static String updateUser(String email, String fName, String sName, String addr,
                                    String postal, String phone, String login) {
        return String.format("UPDATE users SET email='%s', first_name='%s', second_name='%s'," +
                        " address='%s', postal_code='%s', phone_number='%s' WHERE login='%s'", email, fName, sName, addr,
                postal, phone, login);
    }

    public static String getUserOrders(String userLogin){
        return String.format("SELECT * FROM orders WHERE user_login = '%s' ORDER BY orders.date", userLogin);
    }

    public static String createOrder(String userId, String date, String value){
        return String.format("INSERT INTO orders (date, value, user_login)" +
                " VALUES ('%s', '%s', '%s') RETURNING orders.number;", date, value, userId);
    }

    public static String createProductOnOrder(int order_id, int product_id, String price){
        return String.format("INSERT INTO product_on_order (product_id, price, order_number) VALUES ('%d', '%s', '%s')",
                product_id, price, order_id);
    }

    public static String reduceAmountOfProduct(int productId){
        return String.format("UPDATE products SET quantity = quantity - 1 WHERE id='%d';", productId);
    }

    public static String updateOrderValue(int number, String val){
        return String.format("UPDATE orders SET value = '%s' WHERE number = '%d';", val, number);
    }

    public static String createProduct(String title, String genre, String date,
                                       String artist, String imgURL, int quantity, String price){
        return String.format("INSERT INTO products (title, genre, date, artist, img, quantity, price)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%d', '%s')", title, genre, date, artist, imgURL, quantity, price);
    }

    public static String createArtist(String name, String date, String description){
        return String.format("INSERT INTO artists (name, date, description) VALUES ('%s', '%s', '%s');",
                name, date, description);
    }
}
