package services;

import dao.ReimbursementDAO;
import models.Reimbursement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServicesTest {


    ReimbursementDAO reimbursementDAO = Mockito.mock(ReimbursementDAO.class);

    ReimbursementServices reimbursementServices;

    public ReimbursementServicesTest() {
        this.reimbursementServices = new ReimbursementServices(reimbursementDAO);
    }

    @Test
    void getAllReimbursements() {

        List<Reimbursement> expectedResult = new ArrayList<>();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        expectedResult.add(new Reimbursement(1, 69.69, ts, null, "test", null, 3, null, 1, 2));
        expectedResult.add(new Reimbursement(2, 69.69, ts, null, "test", null, 2, null, 2, 1));
        expectedResult.add(new Reimbursement(3, 69.69, ts, null, "test", null, 1, null, 3, 3));

        Mockito.when(reimbursementServices.getAllReimbursements()).thenReturn(expectedResult);
        
        List<Reimbursement> actualResult = reimbursementServices.getAllReimbursements();
        
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createReimbursement() { //for void methods

        Timestamp ts = new Timestamp(System.currentTimeMillis());

        Reimbursement expectedResult = new Reimbursement(1, 69.69, ts, null, "test", null, 3, null, 1, 2);

        reimbursementServices.createReimbursement(expectedResult);

        Mockito.verify(reimbursementDAO, Mockito.times(1)).createReimbursement(expectedResult);

    }

    @Test
    void getOneReimbursement() { //I have to send in the timestamps because of the delay between the database and the test

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement expectedResult = new Reimbursement(1, 69.69, ts, ts, "test", null, 3, 2, 3, 2);

        Mockito.when(reimbursementDAO.getOneReimbursement(1)).thenReturn(expectedResult);

        Reimbursement actualResult = reimbursementServices.getOneReimbursement(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateReimbursement() {

        List<Reimbursement> expectedResult = new ArrayList<>();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        expectedResult.add(new Reimbursement(1, 69.69, ts, ts, "test", null, 3, 2, 1, 2));
        expectedResult.add(new Reimbursement(2, 69.69, ts, ts, "test", null, 2, 2, 2, 1));
        expectedResult.add(new Reimbursement(3, 69.69, ts, ts, "test", null, 1, 2, 3, 3));

        Mockito.when(reimbursementServices.getAllReimbursements()).thenReturn(expectedResult);

        List<Reimbursement> actualResult = reimbursementServices.getAllReimbursements();

        assertEquals(expectedResult, actualResult);
    }
}