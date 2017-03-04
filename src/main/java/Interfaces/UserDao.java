package Interfaces;

import Objects.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by Tymek on 04.03.2017.
 */
public interface UserDao {
    public Boolean verifyUser(String login, String Password);
    public void updateUser(User user);
    public void deleteUser(User user);
    public User getUser(String login);
    public Boolean registerUser(User user);

}
