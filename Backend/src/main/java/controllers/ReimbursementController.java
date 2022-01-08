package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ReimbursementDAO;
import io.javalin.http.Context;
import models.JsonResponse;
import models.Reimbursement;
import org.apache.log4j.Logger;
import services.ReimbursementServices;

import java.sql.Timestamp;
import java.util.List;


public class ReimbursementController {
    Logger log = Logger.getLogger(ReimbursementController.class);
    static ReimbursementServices reimbursementServices = new ReimbursementServices(new ReimbursementDAO());

    public void getAllReimbursements(Context context) throws JsonProcessingException {

        context.contentType("application/json");

        List<Reimbursement> reimbursementList = reimbursementServices.getAllReimbursements();

        String jsonString = new ObjectMapper().writeValueAsString(reimbursementList);

        context.result(jsonString);

        context.status(200);

        log.info("Successfully returned all reimbursements");
    }

    public void createReimbursement(Context context) {//make logic to check if it actually created

        boolean flag = true;

        try {

            Double amount = Double.parseDouble(context.formParam("amount"));
            //Timestamp submitted_time = new Timestamp(System.currentTimeMillis());
            String description = context.formParam("description");
            //byte[] bytearray = new byte[0]; //=  valueOf(context.formParam("bytearray")); optional
            Integer author = Integer.parseInt(context.formParam("author"));
            //Integer status_id = Integer.parseInt(context.formParam("status_id"));
            Integer type_id = Integer.parseInt(context.formParam("type_id"));

            Reimbursement reimbursement = new Reimbursement(amount, description, author, type_id);

            reimbursementServices.createReimbursement(reimbursement);
            context.result("Reimbursement Created");
            context.status(201);
            log.info("Successfully created Reimbursement");

            flag = false;

        }catch (Exception e) {
            log.error(e);
        }

        try {

            Reimbursement reimbursement = context.bodyAsClass(Reimbursement.class);

            if (reimbursement == null) {
                context.json(new JsonResponse(false, "no session found", null));
            }else {
                reimbursementServices.createReimbursement(reimbursement);
                context.result("Reimbursement Created");
                context.status(201);
                log.info("Successfully created Reimbursement");
                flag = false;
            }

        }catch (Exception e) {
            log.error(e);
        }
        if (flag) {
            context.result("Invalid Input");
            context.status(400);
            log.info("Invalid Input");
        }
    }

    public void getOneReimbursement(Context context) {

        context.contentType("application/json");

        try {

            Integer reimbursementId = Integer.parseInt(context.pathParam("reimbursementId"));

            Reimbursement reimbursement = reimbursementServices.getOneReimbursement(reimbursementId);

            if (reimbursement.getId() == null) {

                context.status(404);

                log.info("Failed to get reimbursement");
            } else {

                context.result(new ObjectMapper().writeValueAsString(reimbursement));
                log.info("Successfully returned reimbursement with id: " + reimbursementId);

            }
        }catch (Exception e) {
            context.result("Invalid Input");
            context.status(400);
            log.error(e);
        }
    }

    public void updateReimbursement(Context context) {

        try {

            Integer reimbursementId = Integer.parseInt(context.pathParam("reimbursementId"));

            Reimbursement reimbursement = reimbursementServices.getOneReimbursement(reimbursementId);

            if (reimbursement.getId() == null) {

                context.status(404);
                log.error("Failed to find reimbursement");

            }

            try {

                Integer resolver = Integer.parseInt(context.formParam("resolver"));
                Integer status_id = Integer.parseInt(context.formParam("status_id"));

                reimbursement.setResolver(resolver);
                reimbursement.setStatus_id(status_id);

                //System.out.println("DEBUG: resolverId: " + reimbursement.getResolver() + " statusId: " + reimbursement.getStatus_id() + " reimb Id: " + reimbursement.getId() + " Description: " + reimbursement.getDescription());

                reimbursementServices.updateReimbursement(reimbursement);

            }catch (Exception e) {
                context.result("Invalid Input");
                context.status(400);
                log.error(e);
            }


        }catch (Exception e) {
            context.result("Invalid Input");
            context.status(400);
            log.error(e);
        }

    }

   /* public void deleteReimbursement(Context context) {

        try {

            Integer reimbursementId = Integer.parseInt(context.pathParam("reimbursementId"));

            if (reimbursementServices.getOneReimbursement(reimbursementId).getId() == null) {

                context.status(404);
                log.error("Failed to find reimbursement");

            }else {

                reimbursementServices.deleteReimbursement(reimbursementId);

                context.result("Deleted Reimbursement with id: " + reimbursementId);

                context.status(205);
            }

        }catch (Exception e) {
            log.error(e);
        }
    }*/

    public void getSpecificReimbursements(Context context) throws JsonProcessingException {

        try {

            Integer userId = Integer.parseInt(context.pathParam("userId"));

            context.contentType("application/json");

            List<Reimbursement> reimbursementList = reimbursementServices.getSpecificReimbursements(userId);

            String jsonString = new ObjectMapper().writeValueAsString(reimbursementList);

            context.result(jsonString);

            context.status(200);

            log.info("Successfully returned all reimbursements for user Id: ");

        }catch (Exception e) {log.error(e);}
    }

    public void filteredReimbursements(Context context) throws JsonProcessingException {

        try {

            Integer filterId = Integer.parseInt(context.pathParam("filterId"));

            if (filterId == 0) {
                getAllReimbursements(context);
            }

            //System.out.println("DEBUG: FilterId: " + filterId);

            context.contentType("application/json");

            List<Reimbursement> reimbursementList = reimbursementServices.filteredReimbursements(filterId);

            String jsonString = new ObjectMapper().writeValueAsString(reimbursementList);

            context.result(jsonString);

            context.status(200);

            log.info("Successfully returned all reimbursements");

        }catch (Exception e) {
            log.error(e);
        }



    }
}
