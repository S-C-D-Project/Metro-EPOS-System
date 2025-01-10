package Controllers;

import Models.DataBaseHandler;
import java.sql.SQLException;
import java.util.ArrayList;

public class SuperAdmin {
    private String name;
    private String password;
    private String email;
    private int employeeNumber;
    private boolean isActive;

    private static SuperAdmin superAdmin = null;

    SuperAdmin(String name, String password, String email, int employeeNumber, boolean isActive) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.employeeNumber = employeeNumber;
        this.isActive = isActive;
    }

    SuperAdmin(String id, String password, String choice) throws SQLException {
        Object result = DataBaseHandler.getInstance().getEmployee(id, password, choice);

        if (result == null) {
            throw new SQLException("Failed to retrieve a valid SuperAdmin from the database.");
        }

        if (!(result instanceof Object[])) {
            throw new SQLException("Unexpected result type from DataBaseHandler.getEmployee.");
        }

        Object[] data = (Object[]) result;

        if (data.length != 5) {
            throw new SQLException("Invalid data structure returned from DataBaseHandler.getEmployee.");
        }

        this.name = (String) data[0];
        this.password = (String) data[1];
        this.email = (String) data[2];
        this.employeeNumber = (int) data[3];
        this.isActive = (boolean) data[4];
    }
    public static SuperAdmin getInstance(String id, String password, String choice) throws SQLException {
        if (superAdmin == null) {
            superAdmin = new SuperAdmin(id, password, choice);
        }
        return superAdmin;
    }
public static void logout(){
        superAdmin=null;
}
    public static SuperAdmin getInstance(String name, String password, String email, String employeeID, boolean isActive, String choice) throws SQLException {
        if (superAdmin == null) {
            int empNumber = Integer.parseInt(employeeID);
            superAdmin = new SuperAdmin(name, password, email, empNumber, isActive);
        }
        return superAdmin;
    }

   public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean changePassword(String newPassword) {
        return DataBaseHandler.changePassword(newPassword, employeeNumber);
    }

    @Override
    public String toString() {
        return "SuperAdmin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", employeeNumber=" + employeeNumber +
                ", isActive=" + isActive +
                '}';
    }
    public static ArrayList<String> getEmployees(){
        return DataBaseHandler.getAllEmployees();
    }
}
