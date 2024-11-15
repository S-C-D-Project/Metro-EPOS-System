package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=SCD_DB;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    public static Connection getConnection() throws SQLException {

        Connection con = DriverManager.getConnection(JDBC_URL);
        System.out.println("Connection established successfully!");
        return con;
    }
    /* For Debugging Purpose ONLY
    public static void main(String args[]) {
        try {
            Connection con = getConnection();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
