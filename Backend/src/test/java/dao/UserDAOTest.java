package dao;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import util.H2UtilUser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    UserDAO userDAO;

    UserDAOTest() {
        this.userDAO = new UserDAO(H2UtilUser.url, H2UtilUser.username, H2UtilUser.password);
    }

    @BeforeEach
    void setUp() {
        H2UtilUser.createTable();
        H2UtilUser.addUsers();
    }

    @AfterEach
    void tearDown() {
        H2UtilUser.dropTable();
    }

    @Test
    void getOneUser() {

        List<User> expectedResult = new ArrayList<>();
        expectedResult.add(new User(1, "JoeBob", "TestPassword", "Joe", "Bob", "joebob@joebob.com", 1));
        expectedResult.add(new User(2, "BigNerd", "TestPassword2", "Manager", "Nerd", "Nerdbob@Nerdbob.com", 2));
        expectedResult.add(new User(3, "SmallNerd", "TestPassword3", "Bin", "Who", "BinWho@BinWho.com", 1));

        User actualResult = userDAO.getOneUser(2);

        assertEquals(expectedResult.get(1).toString(), actualResult.toString());

    }

    @Test
    void loginUser() {

        List<User> expectedResult = new ArrayList<>();
        expectedResult.add(new User(1, "JoeBob", "TestPassword", "Joe", "Bob", "joebob@joebob.com", 1));
        expectedResult.add(new User(2, "BigNerd", "TestPassword2", "Manager", "Nerd", "Nerdbob@Nerdbob.com", 2));
        expectedResult.add(new User(3, "SmallNerd", "TestPassword3", "Bin", "Who", "BinWho@BinWho.com", 1));

        Integer actualResult = userDAO.loginUser("SmallNerd", "TestPassword3");

        assertEquals(expectedResult.get(2).getId(), actualResult);

    }
}