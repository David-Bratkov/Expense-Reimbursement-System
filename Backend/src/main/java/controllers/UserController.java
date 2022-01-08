package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import dto.UserDTO;
import io.javalin.http.Context;
import models.JsonResponse;
import models.User;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;
import services.UserServices;

import java.util.List;

public class UserController {

    Logger log = Logger.getLogger(UserController.class);
    static UserServices userServices = new UserServices(new UserDAO());

    /*public void getAllUsers(Context context) throws JsonProcessingException {

        context.contentType("application/json");

        List<User> userList = userServices.getAllUsers();

        String jsonString = new ObjectMapper().writeValueAsString(userList);

        context.result(jsonString);

        context.status(200);

        log.info("Successfully returned all Users");

    }*/


    public void getOneUser(Context context) {

        context.contentType("application/json");

        try {

            Integer userId = Integer.parseInt(context.pathParam("userId"));

            User user = userServices.getOneUser(userId);

            if (user.getId() == null) {

                context.status(404);

                log.info("Failed to get user");
            }else {

                context.result(new ObjectMapper().writeValueAsString(user));
                log.info("Successfully returned user with id: " + userId);
            }

        }catch (Exception e) {log.error(e);}

    }

    public void loginUser(Context context) {

        //System.out.println("DEBUG: Ran login user!");

        context.contentType("application/json");

        try {

            String username = context.formParam("username");
            String password = context.formParam("password");

            //log.info("DEBUG: " + username + " " + password);

            User user = userServices.loginUser(username, password);

            if (user == null) {

                context.json(new JsonResponse(false, "login failed", null));
                context.status(404);
                log.info("failed to find user with user login");
            }

            context.sessionAttribute("login-key", user);
            context.json(new JsonResponse(true, "login successful", new UserDTO((user.getFirstname() + " " + user.getLastname()), user.getId(), user.getRole_id())));

        }catch (Exception e){
            log.error(e);
        }

    }

    public void checkSession(Context context) {

        User user = context.sessionAttribute("login-key");

        if (user == null){
            context.json(new JsonResponse(false, "no session found", null));
            log.info("No session found");
        }else{
            context.json(new JsonResponse(true, "session found", new UserDTO((user.getFirstname() + " " + user.getLastname()), user.getId(), user.getRole_id())));
            log.info("Returned userId: " + user.getId());
        }
    }

    public void deleteSession(Context context) {
        context.sessionAttribute("login-key",null);
        context.json(new JsonResponse(false, "Session has been destroyed and you have successfully logged out", null));
        log.info("Logged out of the Session");
    }

    public void userInfo(Context context) {

        context.contentType("application/json");

        try {

            Integer userId = Integer.parseInt(context.pathParam("userId"));

            User user = userServices.getOneUser(userId);

            if (user.getId() == null) {

                context.status(404);

                log.info("Failed to get user");
            }else {

                context.json(new JsonResponse(true, "user data returned", new UserDTO((user.getFirstname() + " " + user.getLastname()), user.getId(), user.getRole_id())));
                log.info("Successfully returned user with id: " + userId);
            }

        }catch (Exception e) {log.error(e);}
    }
}
