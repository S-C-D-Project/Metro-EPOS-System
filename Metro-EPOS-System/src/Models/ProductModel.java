package Models;

import Controllers.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductModel {
    public static Product getProduct(int productId, Connection connection, int branchId) {
        Product product = null;
        String sql = "EXEC GetProduct @ProductID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve product data from the ResultSet and create a Product object
                String productName = resultSet.getString("productName");
                String contactInfo = resultSet.getString("contactInfo");
                String category = resultSet.getString("category");
                String productSize = resultSet.getString("productSize");

                double originalPrice = resultSet.getDouble("originalPrice");
                int salePrice = resultSet.getInt("salePrice");
                double pricePerUnit = resultSet.getDouble("pricePerUnit");
                int stockQuantity = resultSet.getInt("stockQuantity");
                double salesTax=resultSet.getDouble("salesTax");

                // Create Product object and pass all necessary parameters
                product = new Product(productId,branchId, productName, contactInfo, category, productSize, originalPrice, salePrice, pricePerUnit, stockQuantity, salesTax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }


}
