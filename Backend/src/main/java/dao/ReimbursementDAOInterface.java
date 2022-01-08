package dao;

import models.Reimbursement;

import java.util.List;

public interface ReimbursementDAOInterface {
    List<Reimbursement> getAllReimbursements();
    Reimbursement getOneReimbursement(Integer id);
    void createReimbursement(Reimbursement reimbursement);
    void updateReimbursement(Reimbursement reimbursement);
/*    void deleteReimbursement(Integer id);*/
    List<Reimbursement> getSpecificReimbursements(Integer userId);
    List<Reimbursement> filteredReimbursements(Integer filterId);
}
