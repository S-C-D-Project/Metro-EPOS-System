package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBaseConnection {

    private static final String JDBC_URL = "Enter your global database url";
    private static final String Local_URL = "Enter your local database url";
    private static boolean DataMigrated = false;

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(JDBC_URL);
            System.out.println("Connection established successfully!");
            if (!DataMigrated) {
                DataMigration();
                //storeBackup();
            }
            return con;
        } catch (SQLException e) {
            try {
                Connection con = DriverManager.getConnection(Local_URL);
                System.out.println("Local Connection established successfully!");

                return con;
            } catch (SQLException e1) {
               System.out.println("failed");
               // System.out.println(e1.getMessage());
                return null;
            }
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
    public static void storeBackup() {
        try (Connection jdbcConnection = DriverManager.getConnection(JDBC_URL);
             Connection localConnection = getLocalConnection()) {

            if (localConnection == null) {
                System.out.println("Failed to connect to the local database.");
                return;
            }

            String selectQuery = "SELECT BranchId, productName, category, Manufacturer, originalPrice, salePrice, " +
                    "pricePerUnit, stockQuantity, ProductSize, salestax FROM Product";
            try (PreparedStatement selectStmt = jdbcConnection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStmt.executeQuery()) {


                String insertQuery = "INSERT INTO Product (BranchId, productName, category, Manufacturer, " +
                        "originalPrice, salePrice, pricePerUnit, stockQuantity, ProductSize, salestax) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement insertStmt = localConnection.prepareStatement(insertQuery)) {

                    while (resultSet.next()) {
                        insertStmt.setInt(1, resultSet.getInt("BranchId"));
                        insertStmt.setString(2, resultSet.getString("productName"));
                        insertStmt.setString(3, resultSet.getString("category"));
                        insertStmt.setString(4, resultSet.getString("Manufacturer"));
                        insertStmt.setFloat(5, resultSet.getFloat("originalPrice"));
                        insertStmt.setInt(6, resultSet.getInt("salePrice"));
                        insertStmt.setFloat(7, resultSet.getFloat("pricePerUnit"));
                        insertStmt.setInt(8, resultSet.getInt("stockQuantity"));
                        insertStmt.setString(9, resultSet.getString("ProductSize"));
                        insertStmt.setFloat(10, resultSet.getFloat("salestax"));


                        insertStmt.executeUpdate();
                    }

                    System.out.println("Data migration to local database completed successfully.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error during storing bakcup " + e.getMessage());
           // e.printStackTrace();
        }
    }
    public static void DataMigration() {
        DataMigrated = true;
        ArrayList<ArrayList<String>> datalist = getDatafromLocal();
        String[] queries = {
                "SELECT * FROM Product",
                "SELECT * FROM Vendor",
                "SELECT * FROM Employee",
                "SELECT * FROM Bill",
                "SELECT * FROM BillProduct",
                "SELECT * FROM graph",
                "SELECT * FROM monthlyprofit",
                "SELECT * FROM Purchase"
        };

        for (int i = 0; i < datalist.size(); i++) {
            if (i >= queries.length) {
                System.out.println("No query defined for table at index: " + i);
                continue;
            }

            String query = queries[i];
            try (Connection con = DataBaseConnection.getConnection();
                 PreparedStatement pstmt = con.prepareStatement(query)) {

                // Iterate over local rows for the current table
                for (int j = 1; j < datalist.get(i).size(); j++) { // Start from 1 to skip the table name
                    String[] localRow = datalist.get(i).get(j).split(",");
                    boolean exists = false;

                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        if (tableRowMatches(i, rs, localRow)) { // Check if the local row matches the DB row
                            exists = true;
                            System.out.println("Row already exists: " + localRow[1]);
                            break;
                        }
                    }

                    if (!exists) {
                        System.out.println("Inserting into table at index: " + i + ", row: " + localRow[1]);
                        insertRow(i, localRow);
                    }
                }

            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        deleteAllData(); // Optional cleanup if required
    }

    /**
     * Compare a row in the ResultSet with a row in the datalist for a specific table.
     */
    private static boolean tableRowMatches(int tableIndex, ResultSet rs, String[] localRow) throws SQLException {
        switch (tableIndex) {
            case 0: // Product
                return localRow[1].trim().equals(rs.getString("BranchId")) &&
                        localRow[2].trim().equals(rs.getString("productName")) &&
                        localRow[3].trim().equals(rs.getString("category")) &&
                        localRow[4].trim().equals(rs.getString("Manufacturer"));
            case 1: // Vendor
                return localRow[1].trim().equals(rs.getString("VendorName")) &&
                        localRow[2].trim().equals(rs.getString("City")) &&
                        localRow[5].trim().equals(rs.getString("BranchId"));
            case 2: // Employee
                return localRow[1].trim().equals(rs.getString("Name")) &&
                        localRow[3].trim().equals(rs.getString("Email"));
            case 3: // Bill
                return localRow[1].trim().equals(rs.getString("CashAmount")) &&
                        localRow[6].trim().equals(rs.getString("BillDate"));
            case 4: // BillProduct
                return localRow[1].trim().equals(rs.getString("Billid")) &&
                        localRow[2].trim().equals(rs.getString("ProductID"));
            case 5: // Graph
                return localRow[1].trim().equals(rs.getString("profitDate"));
            case 6: // MonthlyProfit
                return localRow[1].trim().equals(rs.getString("monthlyid"));
            case 7: // Purchase
                return localRow[1].trim().equals(rs.getString("VendorId")) &&
                        localRow[3].trim().equals(rs.getString("ProductId"));
            default:
                return false;
        }
    }

    /**
     * Insert a row into the corresponding table based on the table index.
     */
    private static void insertRow(int tableIndex, String[] localRow) throws InterruptedException {
        switch (tableIndex) {
            case 0:
                DataBaseHandler.insertProduct(Integer.parseInt(localRow[0]),
                        Integer.parseInt(localRow[1]), localRow[2], localRow[3], localRow[4],
                        Double.parseDouble(localRow[5]), Integer.parseInt(localRow[6]),
                        Integer.parseInt(localRow[7]), localRow[8],
                        Double.parseDouble(localRow[9]), Double.parseDouble(localRow[10])

                );
                break;
            case 1:
                DataBaseHandler.insertVendor(Integer.parseInt(localRow[0]),localRow[1], localRow[2], localRow[3], localRow[4], Integer.parseInt(localRow[5]));
                break;
            case 2:
                DataBaseHandler.insertEmployeeMigration(Integer.parseInt(localRow[0]),
                        localRow[1], localRow[2], localRow[3], Integer.parseInt(localRow[4]),
                        Double.parseDouble(localRow[5]), localRow[6], localRow[7],
                        Boolean.parseBoolean(localRow[8]), Boolean.parseBoolean(localRow[9]), localRow[10]
                );
                break;
            case 3:
                DataBaseHandler.insertBillMigration(Integer.parseInt(localRow[0]),
                        Integer.parseInt(localRow[1]), Integer.parseInt(localRow[2]),
                        Integer.parseInt(localRow[3]), Integer.parseInt(localRow[4]),
                        Double.parseDouble(localRow[5]), localRow[6], Integer.parseInt(localRow[7])
                );
                break;
            case 4:
                DataBaseHandler.insertBillProductMigrationForeign(
                        Integer.parseInt(localRow[0]), Integer.parseInt(localRow[1]),
                        Integer.parseInt(localRow[2]), Integer.parseInt(localRow[3])
                );
                break;
            case 5:
                DataBaseHandler.insertGraph(localRow[0],localRow[1],Integer.parseInt(localRow[2]));
                break;
            case 6:
                DataBaseHandler.insertMonthlyProfit(localRow[0], localRow[1], Integer.parseInt(localRow[2]));
                break;
            case 7:
                System.out.println("Reached");
                DataBaseHandler.insertPurchaseForeign(Integer.parseInt(localRow[0]),Integer.parseInt(localRow[1]), localRow[2], Integer.parseInt(localRow[3]));
                break;
            default:
                System.out.println("Insert operation not defined for table index: " + tableIndex);
        }
    }

    public static void deleteAllData() {
        String[] deleteQueries = {
                "DELETE FROM BillProduct",
                "DELETE FROM Bill",
                "DELETE FROM Purchase",
                "DELETE FROM Employee",
                "DELETE FROM graph",
                "DELETE FROM monthlyprofit",
              //  "DELETE FROM tax",
                "DELETE FROM Product",
                "DELETE FROM Vendor",
              //  "DELETE FROM Branch"
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


    public static ArrayList<ArrayList<String>> getDatafromLocal() {
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Database connection details

            // Establish connection
            connection = getLocalConnection();

            // Define tables to fetch
            String[] tables = {"Product", "Vendor", "Employee", "Bill", "BillProduct", "graph", "monthlyprofit", "Purchase"};

            // Fetch data for each table
            for (String table : tables) {
                ArrayList<String> tableData = new ArrayList<>();
                tableData.add(table); // Add table name as the first element

                // Query to fetch all data from the table
                String query = "SELECT * FROM " + table;
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                // Process the result set and convert rows to comma-separated strings
                while (resultSet.next()) {
                    StringBuilder rowData = new StringBuilder();

                    // Get column count
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Append column values
                    for (int i = 1; i <= columnCount; i++) {
                        rowData.append(resultSet.getString(i));
                        if (i < columnCount) {
                            rowData.append(",");
                        }
                    }

                    // Add the row data to tableData
                    tableData.add(rowData.toString());
                }

                // Add tableData to the main dataList
                dataList.add(tableData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }



}
