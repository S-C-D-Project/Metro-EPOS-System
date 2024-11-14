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

    public static int saveBill(Bill bill) throws SQLException {
        return BillModel.saveBill(connection, bill.getCashAmount(), bill.getReturnAmount(),
            bill.getTotalbill(), bill.getAdditionalCharges(), bill.getSalesTaxAmount(),
            bill.getDiscount(), bill.getProductList());
    }

    public int insertProductData(int branchId, String productName, String category,
                                 double originalPrice, int salePrice, double pricePerUnit, String productSize,
                                 int stockQuantity, String manufacturer) throws SQLException {

        if (ProductModel.productExists(productName, branchId, connection)) {
            System.out.println("Error: Product with name '" + productName + "' already exists in branch " + branchId);
            return -1;
        }

        String storedProcCall = "{CALL InsertProductData(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        int productId = -1;

        // Use existing connection, no need to open a new one
        double salesTax = getSalesTax();
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
            callableStatement.setString(7, productSize);   // ProductSize (NVARCHAR)
            callableStatement.setInt(8, stockQuantity);    // StockQuantity (INT)
            callableStatement.setDouble(9, temporary);     // SalesTax (DOUBLE)
            callableStatement.setString(10, manufacturer); // Manufacturer (NVARCHAR)
            callableStatement.registerOutParameter(11, java.sql.Types.INTEGER);  // Output ProductID

            callableStatement.executeUpdate();
            productId = callableStatement.getInt(11);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productId;
    }

    public int insertVendorData(String VendorName, String contactInfo) throws SQLException {
        if (VendorModel.vendorExists(VendorName, connection)) {
            System.out.println("Error: Vendor with name '" + VendorName + "' already exists.");
            return -1;
        }

        String storedProcCall = "{CALL InsertVendorData(?, ?, ?)}";
        int vendorId = -1;

        try (CallableStatement callableStatement = connection.prepareCall(storedProcCall)) {
            callableStatement.setString(1, VendorName);  // VendorName (NVARCHAR)
            callableStatement.setString(2, contactInfo); // ContactInfo (NVARCHAR)
            callableStatement.registerOutParameter(3, java.sql.Types.INTEGER); // Output VendorID

            callableStatement.executeUpdate();
            vendorId = callableStatement.getInt(3);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendorId;
    }

    public static void main(String[] args) {
        // Main method can be used to test methods, if needed
    }
}
