package Models;

import Controllers.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeModel {

    public static Object validateEmployee(String employeeNumber, String password, Connection connection, String choice) {
        Employee employee = null;
        String sql = "EXEC GetEmployeeByCredentials @EmployeeNumber = ?, @Password = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(employeeNumber));
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int employeeID = resultSet.getInt("EmployeeID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                int salary = resultSet.getInt("Salary");
                String joiningDate = resultSet.getString("JoiningDate");
                String leavingDate = resultSet.getString("LeavingDate");
                boolean isActive = resultSet.getBoolean("IsActive");
                int branchID = resultSet.getInt("BranchID");
                boolean firstTime = resultSet.getBoolean("FirstTime");
                String role = resultSet.getString("Role");
                if(!role.equalsIgnoreCase(choice)){
                    return null;
                }
                switch (role.toLowerCase()) {
                    case "branchmanager":
                        employee = new BranchManager(name,password,email, String.valueOf(employeeID), String.valueOf(branchID),salary,joiningDate,leavingDate,isActive,null,firstTime);
                        break;

                    case "dataentryoperator":
                        employee = new DataEntryOperator(name,password,email, String.valueOf(employeeID), String.valueOf(branchID),salary,joiningDate,leavingDate,isActive,null,firstTime);
                        break;

                    case "cashier":
                        employee = new Cashier(name,password,email, String.valueOf(employeeID), String.valueOf(branchID),salary,joiningDate,leavingDate,isActive,null,firstTime);
                        break;

                    case "superadmin":
                        return SuperAdmin.getInstance(name, password, email,String.valueOf(employeeID),isActive,"dataEntryOperator");


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }
}
