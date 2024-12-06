package Models;

import Controllers.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
