package Database;

import Objects.Review;
import Objects.User;

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
}
