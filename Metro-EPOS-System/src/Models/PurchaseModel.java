package Models;

import java.sql.*;

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

    public static int addOrUpdateProductAndPurchase(int branchId, String productName, String category, String manufacturer,
                                                    float originalPrice, int salePrice, float pricePerUnit, int vendorId,
                                                    String vendorName, String size, int stocks) {
        String checkProductQuery = """
        SELECT ProductID
        FROM Product
        WHERE BranchId = ? AND productName = ?;
        """;

        String updateProductQuery = """
        UPDATE Product
        SET stockQuantity = stockQuantity + ?, 
            Manufacturer = ?, 
            ProductSize = ?, 
            originalPrice = ?, 
            salePrice = ?, 
            pricePerUnit = ?
        WHERE BranchId = ? AND productName = ?;
        """;

        String insertProductQuery = """
        INSERT INTO Product (BranchId, productName, category, Manufacturer, originalPrice, salePrice, pricePerUnit, stockQuantity, salesTax, ProductSize)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        String insertPurchaseQuery = """
        INSERT INTO Purchase (VendorId, VendorName, ProductID)
        VALUES (?, ?, ?);
        """;

        double salesTaxRate = getSalesTax();
        double salesTax = (salesTaxRate / 100) * originalPrice;
        salePrice += (int) salesTax;

        try (Connection con = DataBaseConnection.getConnection()) {
            int productId;

            try (PreparedStatement checkStmt = con.prepareStatement(checkProductQuery)) {
                checkStmt.setInt(1, branchId);
                checkStmt.setString(2, productName);

                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    // Product exists, update it
                    productId = rs.getInt("ProductID");
                    try (PreparedStatement updateStmt = con.prepareStatement(updateProductQuery)) {
                        updateStmt.setInt(1, stocks);
                        updateStmt.setString(2, manufacturer);
                        updateStmt.setString(3, size);
                        updateStmt.setFloat(4, originalPrice);
                        updateStmt.setInt(5, salePrice);
                        updateStmt.setFloat(6, pricePerUnit);
                        updateStmt.setInt(7, branchId);
                        updateStmt.setString(8, productName);

                        updateStmt.executeUpdate(); // Execute the update
                    }
                } else {
                    // Product does not exist, insert it
                    try (PreparedStatement insertStmt = con.prepareStatement(insertProductQuery, Statement.RETURN_GENERATED_KEYS)) {
                        insertStmt.setInt(1, branchId);
                        insertStmt.setString(2, productName);
                        insertStmt.setString(3, category);
                        insertStmt.setString(4, manufacturer);
                        insertStmt.setFloat(5, originalPrice);
                        insertStmt.setInt(6, salePrice);
                        insertStmt.setFloat(7, pricePerUnit);
                        insertStmt.setInt(8, stocks);
                        insertStmt.setDouble(9, salesTax);
                        insertStmt.setString(10, size);

                        int rowsInserted = insertStmt.executeUpdate();
                        if (rowsInserted > 0) {
                            try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    productId = generatedKeys.getInt(1);
                                } else {
                                    throw new SQLException("Failed to retrieve ProductID.");
                                }
                            }
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
    public static void insertPurchaseForeign(int purchaseid, int vendorId, String vendorName, int productId) {
        String enableIdentityInsert = "SET IDENTITY_INSERT Purchase ON";
        String disableIdentityInsert = "SET IDENTITY_INSERT Purchase OFF";
        String query = "INSERT INTO Purchase (PurchaseID, VendorId, VendorName, ProductId) VALUES (?, ?, ?, ?)";

        try (Connection con = DataBaseConnection.getConnection();
             Statement stmt = con.createStatement();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            stmt.execute(enableIdentityInsert);
            System.out.println(vendorName+" "+purchaseid);
            pstmt.setInt(1, purchaseid);
            pstmt.setInt(2, vendorId);
            pstmt.setString(3, vendorName);
            pstmt.setInt(4, productId);


            int rowsAffected = pstmt.executeUpdate();

            stmt.execute(disableIdentityInsert);

            if (rowsAffected > 0) {
                System.out.println("Purchase inserted successfully!");
            } else {
                System.out.println("Failed to insert purchase.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
