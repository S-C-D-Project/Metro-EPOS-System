package Models;
import Controllers.Branch;

import java.sql.*;

public class BranchModel {

    public static Branch getBranchById(int branchId, Connection connection) {
        Branch branch = null;
        String sql = "{CALL GetBranchById(?)}";

        try (CallableStatement statement = connection.prepareCall(sql)) {
            statement.setInt(1, branchId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int code = resultSet.getInt("code");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("PhoneNumber");
                int numberOfEmployees = resultSet.getInt("NumberOfEmployees");
                boolean isActive = resultSet.getBoolean("IsActive");

                branch = new Branch(code, address, phoneNumber, numberOfEmployees, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branch;
    }
}
