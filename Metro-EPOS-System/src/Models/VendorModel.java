package Models;

import Controllers.Vendor;
import java.sql.*;

public class VendorModel {


    public static boolean vendorExists(String vendorId, Connection connection) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM Vendors WHERE vendorId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vendorId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
    public static int getVendorIdByName(String vendorName) throws SQLException {
        String query = "SELECT VendorID FROM Vendors WHERE VendorName = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vendorName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("VendorID");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error checking vendor: " + e.getMessage());
            return -1;
        }
    }
    public static boolean vendorExistsByName(String vendorName, Connection connection) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM Vendors WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vendorName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

}
