package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBaseConnection {

    private static final String JDBC_URL = "jdbc:sqlserver://103.31.104.114:1433;databaseName=SCD_Project;user=SCD_User;password=12345678;encrypt=true;trustServerCertificate=true;";
    private static final String Local_URL = "jdbc:sqlserver://DESKTOP-RB5R7MQ\\SQLEXPRESS;databaseName=Test_db;user=SCD_User;password=12345678;encrypt=true;trustServerCertificate=true";
    private static boolean DataMigrated = false;

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(JDBC_URL);
           // System.out.println("Connection established successfully!");
            if (!DataMigrated) {
                DataMigration();
            }
            return con;
        } catch (SQLException e) {
            try {
                Connection con = DriverManager.getConnection(Local_URL);
                System.out.println(e.getMessage());
                return con;
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
                return null;
            }
        }


    public static Connection getLocalConnection() {
        try {
            Connection con = DriverManager.getConnection(Local_URL);
            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void DataMigration() {
        DataMigrated = true;
        ArrayList<String> data = getDatafromLocal();

        try (Connection onlineConnection = getConnection()) {
            if (onlineConnection == null) {
                System.out.println("Failed to connect to the online database.");
                return;
            }



            String[][] tables = {
                    {"Product", "BranchId, productName, category, Manufacturer, originalPrice, salePrice, pricePerUnit, stockQuantity, ProductSize, salestax"},
                    {"Branch", "Address, PhoneNumber, NumberofEmployees, isActive"},
                    {"Vendor", "VendorName, City, Address, Status, BranchId"},
                    {"Employee", "Name, Password, Email, BranchID, Salary, JoiningDate, LeavingDate, isActive, FirstTime, Role"},
                    {"Bill", "CashAmount, ReturnAmount, TotalBill, AdditionalCharges, Discount, BillDate, SalesTaxAmount"},
                    {"BillProduct", "Billid, ProductID, Price, Quantity"},
                    {"graph", "profitDate, profit"},
                    {"monthlyprofit", "monthlyid, time, profit"},
                    {"Purchase", "Vendorid, VendorName, ProductId"},
                    {"tax", "type, price"}
            };

            for (String[] table : tables) {
                String tableName = table[0];
                String columns = table[1];
                String[] columnArray = columns.split(", ");

                StringBuilder placeholders = new StringBuilder();
                for (int i = 0; i < columnArray.length; i++) {
                    placeholders.append("?");
                    if (i < columnArray.length - 1) {
                        placeholders.append(", ");
                    }
                }
                String insertQuery = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";

                try (PreparedStatement preparedStatement = onlineConnection.prepareStatement(insertQuery)) {
                    for (String rowData : data) {
                        if (rowData.startsWith("Table: " + tableName)) {
                            String[] values = rowData.replace("Table: " + tableName + " -> ", "").split(" \\| ");

                            if (values.length == columnArray.length) {
                                for (int i = 0; i < values.length; i++) {

                                    if (tableName.equals("Purchase") && i == 0) {

                                        String localVendorName = values[1];


                                        String vendorCheckQuery = "SELECT VendorId FROM Vendor WHERE VendorName = ?";
                                        try (PreparedStatement vendorStmt = onlineConnection.prepareStatement(vendorCheckQuery)) {
                                            vendorStmt.setString(1, localVendorName);
                                            ResultSet vendorRs = vendorStmt.executeQuery();

                                            if (vendorRs.next()) {

                                                int onlineVendorId = vendorRs.getInt("VendorId");
                                                preparedStatement.setInt(1, onlineVendorId);
                                            } else {

                                                String insertVendorQuery = "INSERT INTO Vendor (VendorName) VALUES (?)";
                                                try (PreparedStatement insertVendorStmt = onlineConnection.prepareStatement(insertVendorQuery, Statement.RETURN_GENERATED_KEYS)) {
                                                    insertVendorStmt.setString(1, localVendorName);
                                                    insertVendorStmt.executeUpdate();


                                                    ResultSet generatedKeys = insertVendorStmt.getGeneratedKeys();
                                                    if (generatedKeys.next()) {
                                                        int newVendorId = generatedKeys.getInt(1);
                                                        preparedStatement.setInt(1, newVendorId);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if(tableName.equals("BillProduct")){
                                           values[0]= String.valueOf(getBillid());
                                        }
                                        preparedStatement.setString(i + 1, values[i].trim());
                                    }
                                }


                                if (tableName.equals("Tax")) {
                                    onlineConnection.createStatement().execute("SET IDENTITY_INSERT Tax ON");
                                }
                                preparedStatement.executeUpdate();
                                if (tableName.equals("Tax")) {
                                    onlineConnection.createStatement().execute("SET IDENTITY_INSERT Tax OFF");
                                }
                            } else {

                            }
                        }
                    }
                    System.out.println("Data migrated successfully for table: " + tableName);
                } catch (SQLException e) {
                    System.out.println("Error inserting data into table " + tableName + ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {

        }
        deleteAllData();
    }
    public static void deleteAllData() {
        String[] deleteQueries = {
                "DELETE FROM BillProduct",
                "DELETE FROM Bill",
                "DELETE FROM Purchase",
                "DELETE FROM Employee",
                "DELETE FROM graph",
                "DELETE FROM monthlyprofit",
                "DELETE FROM tax",
                "DELETE FROM Product",
                "DELETE FROM Vendor",
                "DELETE FROM Branch"
        };

        try (Connection connection = getLocalConnection()) {
            if (connection == null) {
               // System.out.println("Connection failed.");
                return;
            }

            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                for (String query : deleteQueries) {
                    statement.executeUpdate(query);
                }


                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
        }
    }
    public static int getBillid(){
        String query = "SELECT TOP 1 Billid FROM Bill ORDER BY BillDate DESC";
        int billId=0;
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
              billId = resultSet.getInt("BillID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billId;
    }


    public static ArrayList<String> getDatafromLocal() {
        ArrayList<String> dataList = new ArrayList<>();
        String[][] tables = {
                {"Product", "BranchId, productName, category, Manufacturer, originalPrice, salePrice, pricePerUnit, stockQuantity, ProductSize, salestax"},
                {"Branch", "Address, PhoneNumber, NumberofEmployees, isActive"},
                {"Vendor", "VendorName, City, Address, Status, BranchId"},
                {"Employee", "Name, Password, Email, BranchID, Salary, JoiningDate, LeavingDate, isActive, FirstTime, Role"},
                {"Bill", "CashAmount, ReturnAmount, TotalBill, AdditionalCharges, Discount, BillDate, SalesTaxAmount"},
                {"BillProduct", "Billid, ProductID, Price, Quantity"},
                {"graph", "profitDate, profit"},
                {"monthlyprofit", "monthlyid, time, profit"},
                {"Purchase", "VendorId, VendorName, ProductId"},
                {"tax", "type, price"}
        };

        try (Connection connection = getLocalConnection()) {
            System.out.println("Connected to the database!");

            for (String[] table : tables) {
                String tableName = table[0];
                String columns = table[1];

                String query = "SELECT " + columns + " FROM " + tableName;
                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    while (resultSet.next()) {
                        StringBuilder row = new StringBuilder();
                        String[] columnArray = columns.split(", ");
                        for (String column : columnArray) {
                            row.append(resultSet.getString(column)).append(" | ");
                        }
                        dataList.add("Table: " + tableName + " -> " + row.toString());
                    }
                } catch (SQLException e) {
                 //   System.out.println("Error querying table " + tableName + ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
        //    System.out.println("Database connection failed: " + e.getMessage());
        }

        return dataList;
    }

}
