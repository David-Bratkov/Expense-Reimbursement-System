package frontcontroller;

import controllers.ReimbursementController;
import controllers.UserController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Dispatcher {

    ReimbursementController reimbursementController = new ReimbursementController();
    UserController userController = new UserController();


    public Dispatcher(Javalin app) { //dispatcher waits until it receives an HTTP request

        app.routes(() -> {

            path("Reimbursements", () -> {
               get(reimbursementController::getAllReimbursements);
               post(reimbursementController::createReimbursement);
               path("{filterId}/Filtered", () -> {
                  get(reimbursementController::filteredReimbursements);
               });
               path("{reimbursementId}", () -> {
                  get(reimbursementController::getOneReimbursement);
                  put(reimbursementController::updateReimbursement);
                  //delete(reimbursementController::deleteReimbursement);
               });
               path("User", () -> {
                   path("{userId}", () -> {
                       get(reimbursementController::getSpecificReimbursements);
                   });
               });
            });

            path("Users", () -> {
               //get(userController::getAllUsers);
               //post(userController::createUser);
               path("{userId}", () -> {
                  get(userController::getOneUser);
                  //put(userController::updateUser);
                  // delete(userController::deleteUser);
                   path("UserInfo", () -> {
                       get(userController::userInfo);
                   });
               });
               path("Login", () -> {
                   post(userController::loginUser);
                   path("CheckSession", () -> {
                      get(userController::checkSession);
                   });
                   path("Delete", () -> {
                      delete(userController::deleteSession);
                   });
               });
            });
        });
    }
}



