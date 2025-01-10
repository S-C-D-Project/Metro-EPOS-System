package Models;

import Controllers.Product;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class BillModel {
    public static int saveBill(Connection connection, int cashAmount, int returnAmount, int totalbill,
                               int additionalCharges, double salesTaxAmount, double discount,
                               ArrayList<Product> productList) throws SQLException{
        int billId = -1;
        String sql = "{CALL SaveBill(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = connection.prepareCall(sql);
            SQLServerCallableStatement sqlServerStmt = (SQLServerCallableStatement) stmt;

            sqlServerStmt.setInt(1, cashAmount);
            sqlServerStmt.setInt(2, returnAmount);
            sqlServerStmt.setInt(3, totalbill);
            sqlServerStmt.setInt(4, additionalCharges);
            sqlServerStmt.setDouble(5, salesTaxAmount);
            sqlServerStmt.setDouble(6, discount);

            SQLServerDataTable productTable = new SQLServerDataTable();
            productTable.addColumnMetadata("productId", Types.INTEGER);
            productTable.addColumnMetadata("quantity", Types.INTEGER);
            productTable.addColumnMetadata("price", Types.INTEGER);

            for (Product product : productList) {
                productTable.addRow(product.getProductId(), product.getStockQuantity(), product.getSalePrice());
            }

            sqlServerStmt.setStructured(7, "dbo.ProductListType", productTable);
            sqlServerStmt.registerOutParameter(8, Types.INTEGER);

            sqlServerStmt.execute();
            billId = sqlServerStmt.getInt(8);

        return billId;
    }
    public static void insertBillMigration(int billid, int cashAmount, int returnAmount, int totalBill, int additionalCharges,
                                           double discount, String billDate, int salesTaxAmount) {
        String enableIdentityInsert = "SET IDENTITY_INSERT Bill ON";
        String disableIdentityInsert = "SET IDENTITY_INSERT Bill OFF";
        String query = "INSERT INTO Bill (BillID, CashAmount, ReturnAmount, TotalBill, AdditionalCharges, Discount, BillDate, SalesTaxAmount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DataBaseConnection.getConnection();
             Statement stmt = con.createStatement();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            stmt.execute(enableIdentityInsert);


            pstmt.setInt(1, billid);
            pstmt.setInt(2, cashAmount);
            pstmt.setInt(3, returnAmount);
            pstmt.setInt(4, totalBill);
            pstmt.setInt(5, additionalCharges);
            pstmt.setDouble(6, discount);
            pstmt.setString(7, billDate);
            pstmt.setInt(8, salesTaxAmount);


            int rowsAffected = pstmt.executeUpdate();

            stmt.execute(disableIdentityInsert);

            if (rowsAffected > 0) {
                System.out.println("Bill inserted successfully!");
            } else {
                System.out.println("Failed to insert bill.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void insertBillProductMigrationForeign(int billId, int productId, int price, int quantity) throws InterruptedException {

        String query = "INSERT INTO BillProduct (BillID, ProductID, Price, Quantity) VALUES (?, ?, ?, ?)";
        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, price);
            pstmt.setInt(4, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
