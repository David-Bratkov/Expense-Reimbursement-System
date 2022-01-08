package services;

import dao.ReimbursementDAO;
import models.Reimbursement;

import java.util.List;

public class ReimbursementServices {
    ReimbursementDAO reimbursementDAO;
    
    public ReimbursementServices(ReimbursementDAO reimbursementDAO) {this.reimbursementDAO = reimbursementDAO;}

    public List<Reimbursement> getAllReimbursements() {return reimbursementDAO.getAllReimbursements();}

    public void createReimbursement(Reimbursement reimbursement) {reimbursementDAO.createReimbursement(reimbursement);}//return something so i can test it to see if it was successful

    public Reimbursement getOneReimbursement(Integer reimbursementId) {return reimbursementDAO.getOneReimbursement(reimbursementId);}

    public void updateReimbursement(Reimbursement reimbursement) {reimbursementDAO.updateReimbursement(reimbursement);}

//    public void deleteReimbursement(Integer reimbursementId) {reimbursementDAO.deleteReimbursement(reimbursementId);} // not required

    public List<Reimbursement> getSpecificReimbursements(Integer userId) {return reimbursementDAO.getSpecificReimbursements(userId);}

    public List<Reimbursement> filteredReimbursements(Integer filterId) {return reimbursementDAO.filteredReimbursements(filterId);}
}
