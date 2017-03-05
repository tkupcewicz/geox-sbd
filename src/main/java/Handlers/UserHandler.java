package Handlers;

import General.UserDaoImp;
import Interfaces.UserDao;
import Objects.User;

/**
 * Created by Tymek on 04.03.2017.
 */
public class UserHandler {
    private static UserHandler ourInstance = new UserHandler();

    public static UserHandler getInstance() {
        return ourInstance;
    }

    private UserDao userDao;

    private UserHandler() {
        this.userDao = new UserDaoImp();
    }

    public Boolean verifyUser(String login, String password){
        return userDao.verifyUser(login, password);
    }

    public User getUser(String login) {
        return userDao.getUser(login);
    }

    public Boolean registerUser(User user){
        return userDao.registerUser(user);
    }
}
