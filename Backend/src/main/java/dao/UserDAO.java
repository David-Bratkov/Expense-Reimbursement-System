package dao;

import models.Reimbursement;
import models.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAOInterface{

    String url;
    String username;
    String password;

    Logger log = Logger.getLogger(ReimbursementDAO.class);

    public UserDAO() {

        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/Project1-Database";
        this.username =  System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public UserDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

/*    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "Select * FROM ERS_USERS;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }

        }catch (Exception e) {log.error(e);}

        log.info("Was successful returning all Users");
        return userList;
    }*/

    @Override
    public User getOneUser(Integer id) {

        User user = new User();

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            rs.next();

            user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));

        }catch (Exception e) {log.error(e);}

        log.info("User was successfully fetched with Id: " + user.getId());
        return user;
    }

    public Integer loginUser(String Username, String Password) {

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT ers_users_id FROM ERS_USERS WHERE ERS_USERNAME = ? AND ERS_PASSWORD = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, Username);
            ps.setString(2, Password);

            ResultSet rs = ps.executeQuery();

            rs.next();

            return rs.getInt(1);

        }catch (Exception e) {log.error(e);}

        return null;
    }

    //make a create user to add to the h2 database
}
