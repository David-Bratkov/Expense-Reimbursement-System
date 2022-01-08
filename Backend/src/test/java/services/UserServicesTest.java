package services;


import dao.UserDAO;
import models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServicesTest {

    UserDAO userDAO = Mockito.mock(UserDAO.class);

    UserServices userServices;

    public UserServicesTest() {
        this.userServices = new UserServices(userDAO);
    }

    @Test
    void getOneUser() {

        User expectedResult = new User(4, "Jim Pickens", "CheeseLover3", "Jim", "Pickens", "JimPickens@Seagulls.com", 1);
        Mockito.when(userDAO.getOneUser(expectedResult.getId())).thenReturn(expectedResult);

        User actualResult = userServices.getOneUser(expectedResult.getId());

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void loginUser() {// this is broken because there are no users in the database

        User expectedResult = new User(4, "Jim Pickens", "CheeseLover3", "Jim", "Pickens", "JimPickens@Seagulls.com", 1);

        Mockito.when(userDAO.loginUser(expectedResult.getUsername(), expectedResult.getPassword())).thenReturn(4);
        Mockito.when(userDAO.getOneUser(expectedResult.getId())).thenReturn(expectedResult);

        User actualResult = userServices.loginUser(expectedResult.getUsername(), expectedResult.getPassword());


        assertEquals(expectedResult, actualResult);

    }
}