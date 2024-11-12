package Models;

import java.sql.*;

public class DataBaseHandler {

    public int insertProductData(int branchId,String productName, String contactInfo, String category, double originalPrice, int salePrice, double pricePerUnit, String ProductSize, int stockQuantity) {

        // procedure call krne ka format
        String storedProcCall = "{CALL InsertProductData(?, ?, ?, ?, ?, ?, ?, ?)}";
        int productId = -1;
        // InternerConnection ki Class se Connection lya
        try (Connection connection = InternetConnection.getConnection()){

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

            salesTax/=100;
            salesTax*=originalPrice;

            salePrice+= (int) salesTax;
            CallableStatement callableStatement = connection.prepareCall(storedProcCall);


            callableStatement.setInt(1, branchId);        // BranchID
            callableStatement.setString(2, productName);  // ProductName
            callableStatement.setString(3, contactInfo);  // ContactInfo
            callableStatement.setString(4, category);     // Category
            callableStatement.setDouble(5, originalPrice);  // OriginalPrice
            callableStatement.setInt(6, salePrice);       // SalePrice
            callableStatement.setDouble(7, pricePerUnit); // PricePerUnit
            callableStatement.setString(8, ProductSize); // ProductSize
            callableStatement.setInt(9, stockQuantity);   // StockQuantity

            // Register the OUTPUT parameter
            callableStatement.registerOutParameter(10, java.sql.Types.INTEGER);


            callableStatement.executeUpdate();

            productId = callableStatement.getInt(10);


        }
        catch (SQLException e) {
        }
        return productId;
    }
}
