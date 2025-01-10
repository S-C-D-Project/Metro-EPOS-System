package Models;
import Controllers.Branch;

import java.sql.*;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BranchModel {

    public static Branch getBranchById(int branchId, Connection connection) {
        connection = DataBaseConnection.getConnection();
        Branch branch = null;
        String sql = "{CALL GetBranchById(?)}";

        try (CallableStatement statement = connection.prepareCall(sql)) {
            statement.setInt(1, branchId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int code = resultSet.getInt("code");
                String address = resultSet.getString("Address");
             //   String phoneNumber = resultSet.getString("PhoneNumber");
                int numberOfEmployees = resultSet.getInt("NumberOfEmployees");
                boolean isActive = resultSet.getBoolean("IsActive");

                branch = new Branch(code, address, null, numberOfEmployees, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branch;
    }

    public static int addBranch(Connection connection, String name, String address, String city) throws SQLException {
        String sql = "{CALL dbo.ADD_BRANCH(?, ?, ?, ?, ?)}";
        int branchId;
        int numberOfEmployees = 0;
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, city);
            stmt.setInt(4, numberOfEmployees);
            stmt.registerOutParameter(5, Types.INTEGER);
            stmt.execute();
            branchId = stmt.getInt(5);
        }

        return branchId;
    }

    public static String getAllBranchIds(Connection connection) throws SQLException {
        String sql = "{CALL dbo.GET_ALL_BRANCH_IDS}";
        StringBuilder result = new StringBuilder();

        try (CallableStatement stmt = connection.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int branchId = rs.getInt("code");
                result.append(branchId).append(",");
            }
        }

        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }

    public static String[] getAllBranches(Connection connection) throws SQLException {
        String sql = "{CALL dbo.GET_ALL_BRANCHES}";
        List<String> branches = new ArrayList<>();

        try (CallableStatement stmt = connection.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int branchId = rs.getInt("Code");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                int numberOfEmployees = rs.getInt("NumberOfEmployees");
                int active = rs.getInt("isActive");
                if (branchId == 0) {
                    continue;
                }
                String result = String.format("%d,%s,%s,%s,%d,%s",
                        branchId, name, city, address, numberOfEmployees, (active == 1 ? "Active" : "Inactive"));
                branches.add(result);
            }
        }

        return branches.toArray(new String[0]);
    }

    public static void updateBranch(int branchID, String name, String city, String address, int numberOfEmployees, Boolean status, Connection connection) throws SQLException {
        String sql = "{CALL dbo.UPDATE_BRANCH_INFO(?, ?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, branchID);
            stmt.setString(2, name);
            stmt.setString(3, city);
            stmt.setString(4, address);
            stmt.setInt(5, numberOfEmployees);
            stmt.setBoolean(6, status);
            stmt.executeUpdate();
        }
    }


    public static int getTotalProfit(int branchid, String type) {
        // Declare the total profit variable
        int totalProfit = 0;
        LocalDate today = LocalDate.now();
        LocalDate startDate = today;

        // Set the date range based on the 'type'
        switch (type.toLowerCase()) {
            case "daily":
                // For daily profit, set the start date to today
                startDate = today;
                break;

            case "weekly":
                // For weekly profit, set the start date to 7 days ago
                startDate = today.minusWeeks(1);
                break;

            case "monthly":
                // For monthly profit, set the start date to the same day last month
                startDate = today.minusMonths(1);
                break;

            case "yearly":
                // For yearly profit, set the start date to the same day last year
                startDate = today.minusYears(1);
                break;

            default:
                // If the type is not recognized, default to monthly
                startDate = today.minusMonths(1);
                break;
        }

        // Format the dates to match the SQL format (yyyy-MM-dd)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = startDate.format(formatter);
        String end = today.format(formatter);

        // SQL query to sum the profit
        String query = "SELECT SUM(g.profit) AS TotalProfit " +
                "FROM Bill b " +
                "JOIN BillProduct bp ON b.BillID = bp.BillID " +
                "JOIN Product p ON bp.ProductID = p.ProductID " +
                "JOIN graph g ON CAST(b.BillDate AS DATE) = g.profitDate " +
                "WHERE p.BranchId = ? " +
                "AND CAST(b.BillDate AS DATE) BETWEEN ? AND ?;";


        // Try-with-resources to handle database connections and statement cleanup
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            stmt.setInt(1, branchid);
            stmt.setString(2, start); // Start date (e.g., '2024-11-01')
            stmt.setString(3, end);   // End date (e.g., '2024-11-30')

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalProfit = rs.getInt("TotalProfit");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalProfit;
    }

    public static int getTotalStockQuantity(int branchId, String type) {
        int totalStockQuantity = 0;
        LocalDate today = LocalDate.now();
        LocalDate startDate = today;

        // Determine the start date based on the type
        switch (type.toLowerCase()) {
            case "daily":
                startDate = today.minusDays(1);  // Previous day
                break;
            case "weekly":
                startDate = today.minusWeeks(1);  // Last week
                break;
            case "monthly":
                startDate = today.minusMonths(1);  // Last month
                break;
            case "yearly":
                startDate = today.minusYears(1);  // Last year
                break;
            default:
                throw new IllegalArgumentException("Invalid type. Use 'daily', 'weekly', 'monthly', or 'yearly'.");
        }

        // Format the dates as strings
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = startDate.format(formatter);
        String end = today.format(formatter);

        // SQL query to sum stock quantities
        String query = "SELECT SUM(DISTINCT p.StockQuantity) AS Stocks " +
                "FROM Bill b " +
                "JOIN BillProduct bp ON b.BillID = bp.BillID " +
                "JOIN Product p ON bp.ProductID = p.ProductID " +
                "JOIN graph g ON CAST(b.BillDate AS DATE) = g.profitDate " +
                "WHERE p.BranchId = ? " +
                "AND CAST(b.BillDate AS DATE) BETWEEN ? AND ?;";


        // Try-with-resources to handle database connections and statement cleanup
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            stmt.setInt(1, branchId);
            stmt.setString(2, start);  // Start date
            stmt.setString(3, end);    // End date

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalStockQuantity = rs.getInt("Stocks");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalStockQuantity;
    }

    public static int getTotalSales(int branchId, String type) {
        int totalSales = 0;
        LocalDate today = LocalDate.now();
        LocalDate startDate = today;

        switch (type.toLowerCase()) {
            case "daily":
                startDate = today.minusDays(1);  // Previous day
                break;
            case "weekly":
                startDate = today.minusWeeks(1);  // Last week
                break;
            case "monthly":
                startDate = today.minusMonths(1);  // Last month
                break;
            case "yearly":
                startDate = today.minusYears(1);  // Last year
                break;
            default:
                throw new IllegalArgumentException("Invalid type. Use 'daily', 'weekly', 'monthly', or 'yearly'.");
        }

        // Format the dates as strings
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = startDate.format(formatter);
        String end = today.format(formatter);

        // SQL query to sum sales (salePrice * Quantity)
        String query = "SELECT SUM(p.salePrice * bp.quantity) AS TotalSales " +
                "FROM Bill b " +
                "JOIN BillProduct bp ON b.BillID = bp.BillID " +
                "JOIN Product p ON bp.ProductID = p.ProductID " +
                "JOIN graph g ON CAST(b.BillDate AS DATE) = g.profitDate " +
                "WHERE p.BranchId = ? " +
                "AND CAST(b.BillDate AS DATE) BETWEEN ? AND ?;";


        // Try-with-resources to handle database connections and statement cleanup
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            stmt.setInt(1, branchId);
            stmt.setString(2, start);  // Start date
            stmt.setString(3, end);    // End date

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalSales = rs.getInt("TotalSales");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalSales;
    }

    public static ArrayList<String> getProductsByBranchId(int branchId) {
        ArrayList<String> products = new ArrayList<>();


        String query = "SELECT productName, stockQuantity FROM Product WHERE BranchId = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, branchId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String productName = resultSet.getString("productName");
                    int stockQuantity = resultSet.getInt("stockQuantity");

                    // Create a new Product object and add it to the list

                    products.add(productName + "," + stockQuantity);

                }
            }
        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e; // Re-throw the exception for handling elsewhere
        }

        return products;
    }
}
