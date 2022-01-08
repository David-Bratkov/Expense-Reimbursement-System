package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2UtilUser {
    public static String url = "jdbc:h2:./h2/db";
    public static String username = "sa";
    public static String password = "sa";

    public static void createTable(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "CREATE TABLE ERS_USERS (\n" +
                    "\tERS_USERS_ID serial PRIMARY KEY,\n" +
                    "\tERS_USERNAME varchar(50) UNIQUE NOT NULL,\n" +
                    "\tERS_PASSWORD varchar(50) NOT NULL, --optionally can be encripted IF attempted increase the SPACE OF the varchar(100)\n" +
                    "\tUSER_FIRST_NAME varchar(100) NOT NULL,\n" +
                    "\tUSER_LAST_NAME varchar(100) NOT NULL,\n" +
                    "\tUSER_EMAIL varchar(150) UNIQUE NOT NULL,\n" +
                    "\tUSER_ROLE_ID int NOT NULL);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void dropTable(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "DROP TABLE ERS_USERS;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addUsers() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO ERS_USERS VALUES (DEFAULT, 'JoeBob', 'TestPassword', 'Joe', 'bob', 'joebob@joebob.com', 1);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();

            sql = "INSERT INTO ERS_USERS VALUES (DEFAULT, 'BigNerd', 'TestPassword2', 'Manager', 'Nerd', 'Nerdbob@Nerdbob.com', 2);";

            ps = conn.prepareStatement(sql);

            ps.executeUpdate();

            sql = "INSERT INTO ERS_USERS VALUES (DEFAULT, 'SmallNerd', 'TestPassword3', 'Bin', 'Who', 'BinWho@BinWho.com', 1);";

            ps = conn.prepareStatement(sql);

            ps.executeUpdate();

            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
