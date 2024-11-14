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

        if (ProductModel.productExists(productName, branchId, connection)) {
            System.out.println("Error: Product with name '" + productName + "' already exists in branch " + branchId);
            return -1;
        }

        String storedProcCall = "{CALL InsertProductData(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        int productId = -1;

        try (Connection connection = DataBaseConnection.getConnection()) {
            double salesTax =  getSalesTax();
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
    public int insertVendorData( String VendorName, int vendorId,
                                 String contactInfo) {

        if (VendorModel.vendorExists(VendorName, connection)) {
            System.out.println("Error: Vendor with name '" + VendorName );
            return -1;
        }

        String storedProcCall = "{CALL InsertVendorData(?, ?, ?,)}";
        int VendorId = -1;

        try (Connection connection = DataBaseConnection.getConnection()) {


            try (CallableStatement callableStatement = connection.prepareCall(storedProcCall)) {
                callableStatement.setString(1, VendorName);     //
                callableStatement.setString(2, contactInfo);   //
                callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);  // Output VendorID

                callableStatement.executeUpdate();
                VendorId = callableStatement.getInt(3);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return VendorId;
    }
    public static void main(String[] args) throws SQLException {
        // Main method implementation, if needed
    }
}
