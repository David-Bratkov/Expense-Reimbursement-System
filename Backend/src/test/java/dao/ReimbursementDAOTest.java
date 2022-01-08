package dao;

import models.Reimbursement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2UtilReimbursement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementDAOTest {

    ReimbursementDAO reimbursementDAO;

    ReimbursementDAOTest() {this.reimbursementDAO = new ReimbursementDAO(H2UtilReimbursement.url, H2UtilReimbursement.username, H2UtilReimbursement.password);}

    @BeforeEach
    void setUp() {
        H2UtilReimbursement.createTable();
    }

    @AfterEach
    void tearDown() {
        H2UtilReimbursement.dropTable();
    }

    @Test
    void getAllReimbursements() {

        List<Reimbursement> expectedResult = new ArrayList<>();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        expectedResult.add(new Reimbursement(1, 69.69, ts, null, "test", null, 3, 0, 1, 2));
        expectedResult.add(new Reimbursement(2, 69.69, ts, null, "test", null, 2, 0, 1, 1));
        expectedResult.add(new Reimbursement(3, 69.69, ts, null, "test", null, 1, 0, 1, 3));

        reimbursementDAO.createReimbursement(expectedResult.get(0));
        reimbursementDAO.createReimbursement(expectedResult.get(1));
        reimbursementDAO.createReimbursement(expectedResult.get(2));

        List<Reimbursement> actualResult = reimbursementDAO.getAllReimbursements();

        for (Reimbursement reimbursement: actualResult) {
            reimbursement.setSubmitted_time(ts);
            reimbursement.setResolver(0);
        }
        
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void getOneReimbursement() {

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement expectedResult = new Reimbursement(1, 69.69, ts, null, "test", null, 3, 0, 1, 2);

        reimbursementDAO.createReimbursement(expectedResult);

        Reimbursement actualResult = reimbursementDAO.getOneReimbursement(expectedResult.getId());

        actualResult.setSubmitted_time(ts); //because of the delay

        assertEquals(expectedResult.toString(), actualResult.toString());

    }

    @Test
    void createReimbursement() {

        Timestamp ts = new Timestamp(System.currentTimeMillis());

        Reimbursement expectedResult = new Reimbursement(1, 69.69, ts, null, "test", null, 3, 0, 1, 2);

        reimbursementDAO.createReimbursement(expectedResult);

        Reimbursement actualResult = reimbursementDAO.getOneReimbursement(expectedResult.getId()); //calling get one reimbursement because the return is a void method for create reimbursement

        actualResult.setSubmitted_time(ts);

        assertEquals(actualResult.toString(), expectedResult.toString());

    }

    @Test
    void updateReimbursement() {

        List<Reimbursement> expectedResult = new ArrayList<>();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        expectedResult.add(new Reimbursement(1, 69.69, ts, null, "test", null, 3, 0, 1, 2));
        expectedResult.add(new Reimbursement(2, 69.69, ts, null, "test", null, 2, 0, 1, 1));
        expectedResult.add(new Reimbursement(3, 69.69, ts, null, "test", null, 1, 0, 1, 3));

        Reimbursement reimbursementUpdated = expectedResult.get(0);
        reimbursementUpdated.setResolved_time(ts);
        reimbursementUpdated.setResolver(2);

        reimbursementDAO.createReimbursement(expectedResult.get(0)); //adding the reimbursements to the h2 database
        reimbursementDAO.createReimbursement(expectedResult.get(1));
        reimbursementDAO.createReimbursement(expectedResult.get(2));

        reimbursementDAO.updateReimbursement(expectedResult.get(0));

        Reimbursement actualResult = reimbursementDAO.getOneReimbursement(reimbursementUpdated.getId());

        actualResult.setSubmitted_time(ts); // I have to set these to fields because of the delay between the database and the backend
        actualResult.setResolved_time(ts);

        assertEquals(reimbursementUpdated.toString(), actualResult.toString());
    }

}
