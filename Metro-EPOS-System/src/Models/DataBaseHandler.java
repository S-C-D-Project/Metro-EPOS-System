package Models;

import Controllers.Bill;
import Controllers.Product;
import java.sql.*;

public class DataBaseHandler {
    private static DataBaseHandler instance = null;
    private static Connection connection;

    private DataBaseHandler() throws SQLException {
        connection = DataBaseConnection.getConnection();
    }

    public static DataBaseHandler getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseHandler();
        }
        return instance;
    }

    public static double getSalesTax() {
        return TaxModel.getSalesTax(connection);
    }

    public static Product getProduct(int productId, int branchId) {
        return ProductModel.getProduct(productId, connection, branchId);
    }

    public static int saveBill(Bill bill) {
        return BillModel.saveBill(connection, bill.getCashAmount(), bill.getReturnAmount(),
            bill.getTotalbill(), bill.getAdditionalCharges(), bill.getSalesTaxAmount(),
            bill.getDiscount(), bill.getProductList());
    }

    public int insertProductData(int branchId, String productName, String category,
                                 double originalPrice, int salePrice, double pricePerUnit, String productSize,
                                 int stockQuantity, String manufacturer) {

        String storedProcCall = "{CALL InsertProductData(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
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

            double temporary = salesTax;
            salesTax = salesTax / 100 * originalPrice;
            salePrice += (int) salesTax;

            try (CallableStatement callableStatement = connection.prepareCall(storedProcCall)) {
                callableStatement.setInt(1, branchId);         // BranchID (INT)
                callableStatement.setString(2, productName);   // ProductName (NVARCHAR)
                callableStatement.setString(3, category);      // Category (NVARCHAR)
                callableStatement.setDouble(4, originalPrice); // OriginalPrice (FLOAT)
                callableStatement.setInt(5, salePrice);        // SalePrice (INT)
                callableStatement.setDouble(6, pricePerUnit);  // PricePerUnit (FLOAT)
                callableStatement.setInt(7, stockQuantity);    // ProductSize (NVARCHAR)
                callableStatement.setString(8, productSize);   // StockQuantity (INT)
                callableStatement.setDouble(10, temporary);    // SalesTax (DOUBLE)
                callableStatement.setString(11, manufacturer); // Manufacturer (NVARCHAR)
                callableStatement.registerOutParameter(9, java.sql.Types.INTEGER);  // Output ProductID

                callableStatement.executeUpdate();
                productId = callableStatement.getInt(9);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productId;
    }

    public static void main(String[] args) throws SQLException {
        // Main method implementation, if needed
    }
}
