package Models;

import Controllers.Product;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class BillModel {
    public static int saveBill(Connection connection, int cashAmount, int returnAmount, int totalbill,
                               int additionalCharges, double salesTaxAmount, double discount,
                               ArrayList<Product> productList) {
        int billId = -1;
        String sql = "{CALL SaveBill(?, ?, ?, ?, ?, ?, ?, ?)}"; // Simplified SQL call syntax

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            SQLServerCallableStatement sqlServerStmt = (SQLServerCallableStatement) stmt;

            sqlServerStmt.setInt(1, cashAmount);
            sqlServerStmt.setInt(2, returnAmount);
            sqlServerStmt.setInt(3, totalbill);
            sqlServerStmt.setInt(4, additionalCharges);
            sqlServerStmt.setDouble(5, salesTaxAmount);
            sqlServerStmt.setDouble(6, discount);

            // Setup for product table
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billId;
    }
}
