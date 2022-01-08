package dao;

import models.User;

//import java.util.List;

public interface UserDAOInterface {
//    List<User> getAllUsers();
    User getOneUser(Integer id);
    Integer loginUser(String username, String password);
}
