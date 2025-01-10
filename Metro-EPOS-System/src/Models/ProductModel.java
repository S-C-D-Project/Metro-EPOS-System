package Models;

import Controllers.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductModel {
    public static Product getProduct(int productId, Connection connection, int branchId) {
        Product product = null;
        String sql = "EXEC GetProduct @ProductID = ?, @branchid=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.setInt(2,branchId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String productName = resultSet.getString("productName");
                String category = resultSet.getString("category");
                String productSize = resultSet.getString("productSize");

                double originalPrice = resultSet.getDouble("originalPrice");
                int salePrice = resultSet.getInt("salePrice");
                int stockQuantity = resultSet.getInt("stockQuantity");
                double salesTax=resultSet.getDouble("salesTax");

                product = new Product(productId,branchId, productName, category, productSize, originalPrice, salePrice, stockQuantity, salesTax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
    public static boolean insertProduct(int productid, int branchId, String productName, String category, String manufacturer,
                                        double originalPrice, int salePrice, double pricePerUnit,
                                        int stockQuantity, String productSize, double salestax) {

        String enableIdentityInsert = "SET IDENTITY_INSERT Product ON";
        String disableIdentityInsert = "SET IDENTITY_INSERT Product OFF";
        String query = "INSERT INTO Product (ProductID, BranchId, productName, category, Manufacturer, originalPrice, " +
                "salePrice, pricePerUnit, stockQuantity, ProductSize, salestax) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DataBaseConnection.getConnection();
             Statement stmt = con.createStatement();
             PreparedStatement pstmt = con.prepareStatement(query)) {


            stmt.execute(enableIdentityInsert);

            pstmt.setInt(1, productid);
            pstmt.setInt(2, branchId);
            pstmt.setString(3, productName);
            pstmt.setString(4, category);
            pstmt.setString(5, manufacturer);
            pstmt.setDouble(6, originalPrice);
            pstmt.setInt(7, salePrice);
            pstmt.setDouble(8, pricePerUnit);
            pstmt.setDouble(9, stockQuantity);
            pstmt.setString(10, productSize);
            pstmt.setDouble(11, salestax);

            int rowsAffected = pstmt.executeUpdate();

            stmt.execute(disableIdentityInsert);

            if (rowsAffected > 0) {
                System.out.println("Product inserted successfully!");
                return true;
            } else {
                System.out.println("Failed to insert product.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ArrayList<String> getProductStockStatus(int branchid) {
        ArrayList<String> productStockList = new ArrayList<>();

        String query = "SELECT productName, stockQuantity FROM Product WHERE BranchId = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, branchid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String productName = resultSet.getString("productName");
                    int stockQuantity = resultSet.getInt("stockQuantity");

                    String productDetails = productName + ", " + stockQuantity ;
                    productStockList.add(productDetails);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productStockList;
    }
    public static ArrayList<String> getAllProductsLocal() {
        ArrayList<String> products = new ArrayList<>();


        try (Connection connection = DataBaseConnection.getConnection()) {
            if (connection == null) {
                System.out.println("Failed to establish connection.");
                return products;
            }


            String query = "SELECT BranchId, productName, category, Manufacturer, originalPrice, " +
                    "salePrice, pricePerUnit, stockQuantity, ProductSize, salestax FROM Product";

            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet resultSet = stmt.executeQuery()) {


                while (resultSet.next()) {
                    String productDetails = resultSet.getInt("BranchId") +
                            ","+ resultSet.getString("productName") +
                            ","+ resultSet.getString("category") +
                            "," + resultSet.getString("Manufacturer") +
                            "," + resultSet.getFloat("originalPrice") +
                            "," + resultSet.getInt("salePrice") +
                            "," + resultSet.getInt("pricePerUnit") +
                            "," + resultSet.getInt("stockQuantity") +
                            "," + resultSet.getString("ProductSize") +
                            "," + resultSet.getInt("salestax");


                    products.add(productDetails);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
          //  e.printStackTrace();
        }

        return products;
    }
    public static boolean productExists(String productName, int branchId, Connection connection) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM Products WHERE productName = ? AND branchId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productName);
            statement.setInt(2, branchId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
public static void DecreaseProductQuantity(int pid,int qty,Connection connection){
    String sql = "EXEC DecreaseProductQuantity @ProductID = ?, @QtyToDecrease=?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, pid);
        statement.setInt(2,qty);

        int rowsAffected = statement.executeUpdate();
 } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public static int getProductidbyName(String PrdouctName){
        String sql="Select productid from Product where productName = ?";
        try(Connection conn=DataBaseConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(sql)) {
            ps.setString(1,PrdouctName);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("productid");
            }
        }
        catch (SQLException e) {
           return -1;
        }
        return -1;
    }
}
