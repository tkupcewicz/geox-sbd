package Database;

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

}
