package Models;

import Controllers.Vendor;
import java.sql.*;
import java.util.ArrayList;

public class VendorModel {


    public static boolean vendorExists(String vendorId, Connection connection) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM Vendors WHERE vendorId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vendorId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
    public static int getVendorIdByName(String vendorName) throws SQLException {
        String query = "SELECT VendorID FROM Vendors WHERE VendorName = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vendorName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("VendorID");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error checking vendor: " + e.getMessage());
            return -1;
        }
    }
    public static boolean vendorExistsByName(String vendorName, Connection connection) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM Vendors WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vendorName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
    public static ArrayList<String> getVendorsList(int branchid){
        ArrayList<String> vendorList = new ArrayList<>();
        String query = """
            SELECT 
                v.vendorId,
                v.VendorName,
                v.City,
                v.Address,
                v.Status,
                COUNT(p.productId) AS TotalProducts
            FROM 
                Vendor v
            LEFT JOIN 
                Purchase p ON v.vendorId = p.VendorId
            WHERE 
                v.BranchId = ?
            GROUP BY 
                v.vendorId, v.VendorName, v.City, v.Address, v.Status;
            """;

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, branchid);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int vendorId = rs.getInt("vendorId");
                    String vendorName = rs.getString("VendorName");
                    String city = rs.getString("City");
                    String address = rs.getString("Address");
                    String status = rs.getString("Status");
                    int totalProducts = rs.getInt("TotalProducts");

                    String vendorDetails = String.format(
                            "%d,%s,%s,%s,%d,%s",
                            vendorId, vendorName, city, address,totalProducts,status
                    );

                    vendorList.add(vendorDetails);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving vendor list: from a specific branch");
        }

        return vendorList;
    }
    public static boolean updateVendorInfo(int id,String newName, String newCity, String newAddress,String status) {
        String query = """
            UPDATE Vendor
            SET VendorName = ?, City = ?, Address = ?,Status=?
            WHERE vendorId = ?;
            """;

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, newName);
            stmt.setString(2, newCity);
            stmt.setString(3, newAddress);
            stmt.setString(4,status);
            stmt.setInt(5,id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error updating vendor: ");
            return false;
        }
    }
    public static boolean insertVendor(String vendorName, String city, String address, String status, int branchId) {
        String query = """
            INSERT INTO Vendor (VendorName, City, Address, Status, BranchId)
            VALUES (?, ?, ?, ?, ?);
            """;

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, vendorName);
            stmt.setString(2, city);
            stmt.setString(3, address);
            stmt.setString(4, status);
            stmt.setInt(5, branchId);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting vendor: ");
            return false;
        }
    }
    public static String getVendorName(int vendorId) {
        String vendorName = null;


        String query = "SELECT VendorName FROM Vendor WHERE vendorId = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {


            stmt.setInt(1, vendorId);


            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {

                vendorName = rs.getString("VendorName");
            } else {

                System.out.println("No vendor found with ID: " + vendorId);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching vendor name: ");
        }

        return vendorName;
    }

    public static ArrayList<String> getVendorProducts(int vendorId) {
        ArrayList<String> productsList = new ArrayList<>();

        String getProductIdsQuery = "SELECT DISTINCT p.productId FROM Purchase pur " +
                "JOIN Product p ON pur.productId = p.productId " +
                "WHERE pur.VendorId = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(getProductIdsQuery)) {

            stmt.setInt(1, vendorId);

            ResultSet rs = stmt.executeQuery();

            ArrayList<Integer> productIds = new ArrayList<>();
            while (rs.next()) {
                productIds.add(rs.getInt("productId"));
            }
            for (Integer productId : productIds) {
                String getProductDetailsQuery = "SELECT * FROM Product WHERE productId = ?";

                try (PreparedStatement productStmt = con.prepareStatement(getProductDetailsQuery)) {
                    productStmt.setInt(1, productId);
                    ResultSet productRs = productStmt.executeQuery();

                    if (productRs.next()) {
                        String productDetails =

                                productRs.getString("category") + "," + productRs.getString("productName") + "," +
                                productRs.getFloat("originalPrice") + "," +
                                productRs.getInt("salePrice") + "," +
                                productRs.getFloat("pricePerUnit") + "," +
                                productRs.getString("Manufacturer") ;

                        productsList.add(productDetails);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching vendor products: "+e.getMessage());
        }

        return productsList;
    }

}
