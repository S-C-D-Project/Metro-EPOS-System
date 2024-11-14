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
