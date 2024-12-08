package Models;
import Controllers.*;

import java.sql.*;

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
    public static boolean addEmployee(String name, String email, int salary, int branchid, String role,  Connection connection) {
        try {
            String sql = "{ ? = call ADDEMPOLYEE(?, ?, ?, ?, ?) }";
            PreparedStatement stmt = connection.prepareStatement(sql);


            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, salary);
            stmt.setInt(4, branchid);
            stmt.setString(5, role);

            return stmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean isValidDataOperator(String id, String pass) {
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
        System.out.println();
        return branchid;
    }

}






