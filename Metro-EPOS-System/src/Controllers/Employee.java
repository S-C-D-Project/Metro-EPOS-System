package Controllers;

import Models.DataBaseHandler;
import Models.EmployeeModel;

import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;

public abstract class Employee {
    private String name;
    private String password;
    private String email;
    private int employeeNumber;

    private int salary;
    private String joiningDate;
    private String leavingDate;
    private boolean isActive;
    private boolean firstTime;
    private Branch branch;
    private String role;
    private String phoneNumber;
    int branchid;

    public Employee(String name, String password, String email, int employeeID, int branchId, int salary, boolean isActive, String role, String phoneNumber) {

    this.name = name;
        this.password = password;
        this.email = email;
        this.employeeNumber = employeeID;
        this.salary = salary;
        this.isActive = isActive;
        this.role = role;
    this.branchid=branchId;
    this.phoneNumber=phoneNumber;}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Employee() {
    }
    public Employee( String name,String password,String email,int employeeID,int branchId, int salary, String joiningDate, String leavingDate, boolean isActive,boolean firstTime, String role) {
        this.employeeNumber = employeeID;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.leavingDate = leavingDate;
        this.isActive = isActive;

            this.branch = new Branch(branchId);

        this.firstTime = firstTime;
        this.role = role;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }


    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public Branch getBranch() {
        return branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Object vallidateEmployee(String id, String password, String choice) throws SQLException {
        return DataBaseHandler.getInstance().getEmployee(id, password, choice);
    }

    public boolean changePassword(String newPassword) throws SQLException {
        firstTime = false;
        return DataBaseHandler.changePassword(newPassword, employeeNumber);
    }

    public void setBranchid(int branchId) {
        if (this.branch == null) {
            this.branch = new Branch(branchId);
        } else {
            this.branch.setId(branchId);
        }
    }

    @Override
    public String toString() {
        return
                 name + ',' +email+','+
                 password  + ","+salary +','+phoneNumber+ "," + role +","+isActive;
    }

    public int getBranchid() {
        return branchid;
    }
}
