package dao;
import models.Reimbursement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements ReimbursementDAOInterface {

    String url;
    String username;
    String password;

    Logger log = Logger.getLogger(ReimbursementDAO.class);

    public ReimbursementDAO() {

        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/Project1-Database";
        this.username =  System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ReimbursementDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        List<Reimbursement> reimbursementList = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_id;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                reimbursementList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getBytes(6),  rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
                //make timestamp pretty
                //reimbursement.setSubmittedDate(new SimpleDateFormat("MM/dd/yyyy").format(reimbursement.getSubmitted()));
                //
            }

        }catch (Exception e) {log.error(e);}

        log.info("Was successful returning all Reimbursements");
        return reimbursementList;
    }

    @Override
    public Reimbursement getOneReimbursement(Integer id) {

        Reimbursement reimbursement = new Reimbursement();

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            rs.next();

            reimbursement = (new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getBytes(6),  rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));

        }catch (Exception e) {log.error(e);}

        log.info("Reimbursement was successfully fetched with Id: " + reimbursement.getId());
        return reimbursement;

    }

    @Override
    public void createReimbursement(Reimbursement reimbursement) {

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            //System.out.println("" + reimbursement.getAmount() + reimbursement.getDescription() + reimbursement.getAuthor() + reimbursement.getType_id());

            String sql = "INSERT INTO ERS_REIMBURSEMENT VALUES (DEFAULT, ?, DEFAULT, ?, ?, ?, ?, NULL, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, reimbursement.getAmount());
            //ps.setTimestamp(2, reimbursement.getSubmitted_time());
            ps.setTimestamp(2, null);
            ps.setString(3, reimbursement.getDescription());
            ps.setBytes(4, null);
            ps.setInt(5, reimbursement.getAuthor());
            //ps.setInt(6, Integer.); //check to see if works as a string
            ps.setInt(6, 1);
            ps.setInt(7, reimbursement.getType_id());

            int rs = ps.executeUpdate();

            log.info("Number of rows created: " + rs);

        }catch (Exception e) {log.error(e);}

    }

    @Override
    public void updateReimbursement(Reimbursement reimbursement) {

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "UPDATE ers_reimbursement SET reimb_resolved = current_timestamp, REIMB_RESOLVER = ?, REIMB_STATUS_ID = ? WHERE reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, reimbursement.getResolver());
            ps.setInt(2, reimbursement.getStatus_id());
            ps.setInt(3, reimbursement.getId());

            log.info("Successfully updated a Reimbursement with Id: " + reimbursement.getId() + " Status_Id: " + reimbursement.getStatus_id() + " Resolver Id: " + reimbursement.getResolver());

            ps.executeUpdate();

        }catch (SQLException e){
            log.error(e);
        }
    }

 /*   @Override
    public void deleteReimbursement(Integer id) {
        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "DELETE FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            log.info("Successfully deleted Reimbursement with Id: " + id);

        }catch (SQLException e){
            log.error(e);
        }
    }
*/
    @Override
    public List<Reimbursement> getSpecificReimbursements(Integer userId) {

        List<Reimbursement> reimbursementList = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_id;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                reimbursementList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getBytes(6),  rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }

        }catch (Exception e) {log.error(e);}

        log.info("Was successful returning all Reimbursements from user Id: " + userId);
        return reimbursementList;
    }

    @Override
    public List<Reimbursement> filteredReimbursements(Integer filterId) {

        List<Reimbursement> reimbursementList = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ? ORDER BY reimb_id;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, filterId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                reimbursementList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getBytes(6),  rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
                //make timestamp pretty
                //reimbursement.setSubmittedDate(new SimpleDateFormat("MM/dd/yyyy").format(reimbursement.getSubmitted()));
                //
            }

        }catch (Exception e) {log.error(e);}

        log.info("Was successful filtered Reimbursements by Id: " + filterId);
        return reimbursementList;

    }
}
