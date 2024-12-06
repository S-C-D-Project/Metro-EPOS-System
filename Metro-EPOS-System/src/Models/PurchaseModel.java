package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Models.DataBaseHandler.getSalesTax;

/*
CREATE TABLE Purchases (
        PurchaseID INT PRIMARY KEY AUTO_INCREMENT,
        ProductID INT,
        VendorID INT,
        Amount INT,
        PurchaseDate DATE,
        FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
FOREIGN KEY (VendorID) REFERENCES Vendors(VendorID)
        );
*/
public class PurchaseModel {

    public static int addOrUpdateProductAndPurchase(int branchId, String productName, String category, String manufacturer, float originalPrice, int salePrice, float pricePerUnit, int vendorId, String vendorName) {
        String checkProductQuery = """
            SELECT ProductID
            FROM Product
            Where Branchid=? and productName = ?;
            """;

        String updateProductQuery = """
            UPDATE Product
            SET stockQuantity = stockQuantity + 1
            OUTPUT INSERTED.ProductID
            WHERE BranchId = ? AND productName = ?;
            """;
            double salesTax = getSalesTax();
            double temporary = salesTax;
            salesTax = salesTax / 100 * originalPrice;
            salePrice += (int) salesTax;
            String insertProductQuery = """
            INSERT INTO Product (BranchId, productName, category, Manufacturer, originalPrice, salePrice,pricePerUnit, stockQuantity,salesTax,ProductSize)
            OUTPUT INSERTED.ProductID
            VALUES (?, ?, ?, ?, ?, ?, ?, 1,?,?);
            """;

        String insertPurchaseQuery = """
            INSERT INTO Purchase (VendorId, VendorName, productId)
            VALUES (?, ?, ?);
            """;

        try (Connection con = DataBaseConnection.getConnection()) {
            int productId;


            try (PreparedStatement checkStmt = con.prepareStatement(checkProductQuery)) {
                checkStmt.setInt(1, branchId);
                checkStmt.setString(2, productName);

                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {

                    try (PreparedStatement updateStmt = con.prepareStatement(updateProductQuery)) {
                        updateStmt.setInt(1, branchId);
                        updateStmt.setString(2, productName);

                        ResultSet updateRs = updateStmt.executeQuery();
                        if (updateRs.next()) {
                            productId = updateRs.getInt("ProductID");
                        } else {
                            throw new SQLException("Failed to update product stock quantity.");
                        }
                    }
                } else {

                    try (PreparedStatement insertStmt = con.prepareStatement(insertProductQuery)) {
                        insertStmt.setInt(1, branchId);
                        insertStmt.setString(2, productName);
                        insertStmt.setString(3, category);
                        insertStmt.setString(4, manufacturer);
                        insertStmt.setInt(5, (int) originalPrice);
                        insertStmt.setInt(6, salePrice);
                        insertStmt.setInt(7, (int) pricePerUnit);
                        insertStmt.setInt(8, (int) salesTax);
                        insertStmt.setString(9,"Normal");

                        ResultSet insertRs = insertStmt.executeQuery();
                        if (insertRs.next()) {
                            productId = insertRs.getInt("ProductID");
                        } else {
                            throw new SQLException("Failed to insert product.");
                        }
                    }
                }
            }


            try (PreparedStatement purchaseStmt = con.prepareStatement(insertPurchaseQuery)) {
                purchaseStmt.setInt(1, vendorId);
                purchaseStmt.setString(2, vendorName);
                purchaseStmt.setInt(3, productId);

                int rowsInserted = purchaseStmt.executeUpdate();
                if (rowsInserted > 0) {
                    return productId;
                } else {
                    throw new SQLException("Failed to insert purchase record.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error managing product and purchase: " + e.getMessage());
            return -1;
        }
    }
}
