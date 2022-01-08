package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2UtilReimbursement {
    public static String url = "jdbc:h2:./h2/db";
    public static String username = "sa";
    public static String password = "sa";

    public static void createTable(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "CREATE TABLE ERS_REIMBURSEMENT (\n" +
                    "\tREIMB_ID serial PRIMARY KEY NOT NULL,\n" +
                    "\tREIMB_AMOUNT decimal(30, 2) NOT NULL,\n" +
                    "\tREIMB_SUBMITTED Timestamp NOT NULL DEFAULT current_timestamp,\n" +
                    "\tREIMB_RESOLVED Timestamp DEFAULT current_timestamp,\n" +
                    "\tREIMB_DESCRIPTION varchar(250),\n" +
                    "\tREIMB_RECEIPT bytea, --Optional\n" +
                    "\tREIMB_AUTHOR int NOT NULL,\n" +
                    "\tREIMB_RESOLVER int,\n" +
                    "\tREIMB_STATUS_ID int NOT NULL,\n" +
                    "\tREIMB_TYPE_ID int NOT NULL);";

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

            String sql = "DROP TABLE ERS_REIMBURSEMENT;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
