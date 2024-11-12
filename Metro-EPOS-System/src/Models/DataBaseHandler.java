package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseHandler {

    public int insertProductData(int branchId,String productName, String contactInfo, String category, double originalPrice, int salePrice, double pricePerUnit, double pricePerCarton, int stockQuantity) {

        // procedure call krne ka format
        String storedProcCall = "{CALL InsertProductData(?, ?, ?, ?, ?, ?, ?, ?)}";
        int productId = -1;
        // InternerConnection ki Class se Connection lya
        try (Connection connection = InternetConnection.getConnection()){

            CallableStatement callableStatement = connection.prepareCall(storedProcCall);


            callableStatement.setInt(1, branchId);        // BranchID
            callableStatement.setString(2, productName);  // ProductName
            callableStatement.setString(3, contactInfo);  // ContactInfo
            callableStatement.setString(4, category);     // Category
            callableStatement.setDouble(5, originalPrice);  // OriginalPrice
            callableStatement.setInt(6, salePrice);       // SalePrice
            callableStatement.setDouble(7, pricePerUnit); // PricePerUnit
            callableStatement.setDouble(8, pricePerCarton); // PricePerCarton
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
