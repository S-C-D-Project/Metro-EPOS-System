package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    // Method to insert a purchase into the database
    public static int insertPurchase(int productId, int vendorId, int amount, String purchaseDate) throws SQLException {
        String insertSQL = "INSERT INTO Purchases (ProductID, VendorID, Amount, PurchaseDate) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, productId); // Set ProductID
            preparedStatement.setInt(2, vendorId);  // Set VendorID
            preparedStatement.setInt(3, amount);    // Set Amount
            preparedStatement.setString(4, purchaseDate);  // Set Purchase Date

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Get the auto-generated PurchaseID
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);  // Return the generated PurchaseID
                    }
                }
            }
            return -1; // Return -1 if the insertion failed
        } catch (SQLException e) {
            System.out.println("Error inserting purchase: " + e.getMessage());
            throw e;  // Rethrow the exception
        }
    }
}
