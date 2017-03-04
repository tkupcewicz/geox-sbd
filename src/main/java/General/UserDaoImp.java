package General;

import Database.PostgresDB;
import Database.Query;
import Interfaces.UserDao;
import Objects.User;
import javafx.geometry.Pos;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tymek on 04.03.2017.
 */
public class UserDaoImp implements UserDao {
    @Override
    public Boolean verifyUser(String login, String password) {
        String query = Query.verifyUser(login);
        String pass = null;
        ResultSet rs = PostgresDB.getInstance().select(query).getSet();
        try {
            rs.next();
            pass = rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password.equals(pass);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User getUser(String login) {
        String query = Query.getUser(login);
        ResultSet rs = PostgresDB.getInstance().select(query).getSet();
        User user = null;
        try {
            rs.next();
            user = new User(
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("second_name"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("postal_code"),
                rs.getString("phone_number"),
                rs.getBoolean("manager")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Boolean registerUser(User user) {
        String query = Query.registerUser(user.getLogin(), user.getPassword(),
                user.getFirstName(), user.getSecondName(), user.getEmail(), user.getAddress(),
                user.getPostalCode(), user.getPhoneNumber(), user.getManager());
        try {
            PostgresDB.getInstance().insert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
