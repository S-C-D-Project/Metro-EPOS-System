package Models;

import java.sql.*;

public class DataBaseHandler {

    public int insertProductData(int branchId, String productName, String contactInfo, String category,
                                 double originalPrice, int salePrice, double pricePerUnit, String productSize,
                                 int stockQuantity) {

        String storedProcCall = "{CALL InsertProductData(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";

        int productId = -1;

        try (Connection connection = InternetConnection.getConnection()) {

            double salesTax = 0.0;
            String sql = "EXEC GetSalesTaxPrice";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    salesTax = resultSet.getDouble("price");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

double temporary=salesTax;
            salesTax = salesTax / 100 * originalPrice;
            salePrice += (int) salesTax;

            try (CallableStatement callableStatement = connection.prepareCall(storedProcCall)) {

                // Set input parameters (9 parameters)
                callableStatement.setInt(1, branchId);         // BranchID (INT)
                callableStatement.setString(2, productName);   // ProductName (NVARCHAR)
                callableStatement.setString(3, contactInfo);   // ContactInfo (NVARCHAR)
                callableStatement.setString(4, category);      // Category (NVARCHAR)
                callableStatement.setDouble(5, originalPrice); // OriginalPrice (FLOAT)
                callableStatement.setInt(6, salePrice);        // SalePrice (INT)
                callableStatement.setDouble(7, pricePerUnit);  // PricePerUnit (FLOAT)
                callableStatement.setInt(8, stockQuantity);   // ProductSize (NVARCHAR)
                callableStatement.setString(9, productSize);    // StockQuantity (INT)
                callableStatement.setDouble(11,temporary);

                callableStatement.registerOutParameter(10, java.sql.Types.INTEGER);  // Output ProductID


                callableStatement.executeUpdate();


                productId = callableStatement.getInt(10);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return productId;
    }
}
