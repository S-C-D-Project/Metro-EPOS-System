package Models;
import Controllers.*;

import java.sql.*;
import java.util.ArrayList;

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
                        employee = new BranchManager(name,password,email, employeeID, branchID,salary,joiningDate,leavingDate,isActive,firstTime,role);
                        break;
                    case "dataentryoperator":
                        employee = new DataEntryOperator(name,password,email, employeeID, branchID,salary,joiningDate,leavingDate,isActive,firstTime,role);
                        break;
                    case "cashier":
                        employee = new Cashier(name,password,email, employeeID, branchID,salary,joiningDate,leavingDate,isActive,firstTime,role);
                        break;
                    case "superadmin": {
                        return new Object[]{name, password, email, employeeID, isActive};   }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static boolean changePassword(String newPassword,int employeeID,Connection connection){
        String sql = "EXEC ChangePassword @NewPassword = ?, @EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeID);
            statement.setString(2, newPassword);
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            return  false;
        }
        return true;
    }
    public static boolean updateEmployee(int employeeId,String name, String email, Branch branch, int salary, String joiningDate, String leavingDate, boolean active, boolean firstTime, String role, Connection connection) {
        try {
            String sql = "EXEC UPDATEEMPLOYEE ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, employeeId);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setInt(4, branch.getId());
            stmt.setInt(5, salary);
            stmt.setDate(6, Date.valueOf(joiningDate));
            stmt.setDate(7, Date.valueOf(leavingDate));
            stmt.setBoolean(8, active);
            stmt.setBoolean(9, firstTime);
            stmt.setString(10, role);

            return stmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static int addEmployee(String name, int salary, int branchId, String role, Connection connection) {
        try {
            String sql = "{ call ADDEMPOLYEE(?, ?, ?, ?, ?, ?) }";
            CallableStatement stmt = connection.prepareCall(sql);

            stmt.setString(1, name);
            stmt.setInt(2, salary);
            stmt.setInt(3, branchId);
            stmt.setString(4, role);
            stmt.setString(5, "123");
            stmt.registerOutParameter(6, Types.INTEGER);

            stmt.execute();

            return stmt.getInt(6);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }  public static boolean isValidDataOperator(String id, String pass) {
        boolean isValid = false;
        String sql = "SELECT * FROM employee WHERE EmployeeID = ? AND Password = ? AND Role LIKE 'DataEntryOperator'";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, id);
            stmt.setString(2, pass);


            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    isValid = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in Data entry login");
        }

        return isValid;
    }
    public static String getEmployeeName(String id) {
        String employeeName = null;
        String sql = "SELECT Name FROM employee WHERE EmployeeID = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);


            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    employeeName = rs.getString("Name");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in Data entry login's getting name");
        }

        return employeeName;
    }
    public static String getEmployeeBranch(String id) {
        String branchid = null;
        String sql = "SELECT branchId FROM employee WHERE EmployeeID = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);


            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    branchid = rs.getString("branchId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Data entry login's getting branch");
        }
        return branchid;
    }
    public static ArrayList<String> getEmployeesByBranch(Connection connection, int branchID) {
        ArrayList<String> employeeList = new ArrayList<>();
        String storedProc = "{call GetEmployeesByBranch(?)}";

        try (CallableStatement callableStatement = connection.prepareCall(storedProc)) {
            callableStatement.setInt(1, branchID);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("Employeeid");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String passwordFromDb = resultSet.getString("Password");
                double salary = resultSet.getDouble("Salary");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String role = resultSet.getString("Role");
                String isActive = resultSet.getBoolean("IsActive") ? "Active" : "Inactive";

                String formattedString = String.format("%03d,%s,%s,%s,%.2f,%s,%s,%s",
                        employeeId, name, email, passwordFromDb, salary, phoneNumber, role, isActive);
                employeeList.add(formattedString);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }


}






