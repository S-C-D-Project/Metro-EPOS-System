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

        public int saveBill(Connection connection, int cashAmount, int returnAmount, int totalbill,
                            int additionalCharges, int salesTaxAmount, double discount, double salesTax,
                            ArrayList<Product> productList) {

            int billId = -1;
            String sql = "EXEC SaveBill @cashAmount = ?, @returnAmount = ?, @totalBill = ?, @additionalCharges = ?, " +
                    "@salesTaxAmount = ?, @discount = ?, @salesTax = ?, @productList = ?, @billId = ? OUTPUT";

            try (CallableStatement stmt = connection.prepareCall(sql)) {
                // Cast to SQLServerCallableStatement
                SQLServerCallableStatement sqlServerStmt = (SQLServerCallableStatement) stmt;

                sqlServerStmt.setInt(1, cashAmount);
                sqlServerStmt.setInt(2, returnAmount);
                sqlServerStmt.setInt(3, totalbill);
                sqlServerStmt.setInt(4, additionalCharges);
                sqlServerStmt.setInt(5, salesTaxAmount);
                sqlServerStmt.setDouble(6, discount);
                sqlServerStmt.setDouble(7, salesTax);

                SQLServerDataTable productTable = new SQLServerDataTable();
                productTable.addColumnMetadata("productId", Types.INTEGER);
                productTable.addColumnMetadata("quantity", Types.INTEGER);
                productTable.addColumnMetadata("price", Types.FLOAT);

                for (Product product : productList) {
                    productTable.addRow(product.getProductId(), product.getStockQuantity(), product.getSalePrice());
                }

                sqlServerStmt.setStructured(8, "dbo.ProductListType", productTable);

                sqlServerStmt.registerOutParameter(9, Types.INTEGER);

                sqlServerStmt.execute();

                billId = sqlServerStmt.getInt(9);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return billId;
        }
    }




