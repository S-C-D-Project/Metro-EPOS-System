package Controllers;

import Models.BranchModel;
import Models.DataBaseHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BranchManager extends Employee {
    public BranchManager( String name,String password,String email,int employeeID,int branchId, int salary, String joiningDate, String leavingDate, boolean isActive,boolean firstTime, String role) {

        super(name,password,email, employeeID, branchId,salary,joiningDate,leavingDate,isActive,firstTime,role);
    }

    public BranchManager() {
        super();
    }

    public BranchManager( String name,String password,String email,int employeeID,int branchId, int salary, boolean isActive, String role,String phoneNumber) {
        super(name,password,email,employeeID,branchId,salary,isActive,role,phoneNumber);
    }

    public int addEmployee(String name, String email, int salary, int branchid, String role) {
        return DataBaseHandler.addEmployee(name, email, salary, branchid, role);
    }

    public boolean updateEmployee(Employee employee) {
        if (employee == null) {
            return false; // Return false if employee is null
        }

        if (!employee.isActive()) {
            employee.setLeavingDate(String.valueOf(new Date()));
        }
        return DataBaseHandler.updateEmployee(employee.getEmployeeNumber(), employee.getName(), employee.getEmail(),employee.getPassword(), employee.getBranchid(), employee.getSalary(),  employee.isActive(), employee.getRole(),employee.getPhoneNumber());
    }

        public ArrayList<String> getEmployeesByBranch(int branchID) {
        return DataBaseHandler.getEmployeesByBranch(branchID);
    }
    public static int  addBranch(String name,String address, String city) throws SQLException {
        return DataBaseHandler.addBranch(name,address,city);
    }
    public static String getAllBranchIds() throws SQLException {
        return DataBaseHandler.getAllBranchIds();
    }
    public static String[] getAllBranches() throws SQLException {return DataBaseHandler.getAllBranches();}
    public static void updateBranch(int branchID,String name,String city,String address,int numberOfEmployees,Boolean status) throws SQLException {
        DataBaseHandler.updateBranch(branchID, name, city, address, numberOfEmployees, status);

    }

    }
