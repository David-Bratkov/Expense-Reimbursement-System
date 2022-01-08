package services;

import dao.UserDAO;
import models.User;

import java.util.List;

public class UserServices {
    UserDAO userDAO;

    public UserServices (UserDAO userDAO) {this.userDAO = userDAO;}


//    public List<User> getAllUsers() {return userDAO.getAllUsers();}

    public User getOneUser(Integer userId) {return userDAO.getOneUser(userId);}

    public User loginUser(String username, String password) {
        Integer userId = userDAO.loginUser(username, password);

        if (userId == null) {

            return null;
        }else {
            return getOneUser(userId);
        }
    }


}
