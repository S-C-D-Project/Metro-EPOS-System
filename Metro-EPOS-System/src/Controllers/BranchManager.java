package Controllers;

import Models.DataBaseHandler;

import java.util.Date;

public class BranchManager extends Employee {
    public BranchManager(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime) {

            super(Integer.parseInt(employeeNumber), name, email,salary,joiningDate,leavingDate,isActive,Integer.parseInt(branchCode),firstTime,"Branch manager",branch); }
    public BranchManager(){super();}
    public boolean addEmployee(String name, String email,  int salary  ,int branchid, String role){
        return DataBaseHandler.addEmployee(name, email, salary, branchid, role);
    }

    public boolean updateEmployee(Employee employee) {
        if(!employee.isActive()) {
            employee.setLeavingDate(String.valueOf(new Date()));
        }
        return DataBaseHandler.updateEmployee(employee.getEmployeeNumber(),employee.getName(), employee.getEmail(), employee.getBranch(), employee.getSalary(), employee.getJoiningDate(), employee.getLeavingDate(), employee.isActive(), employee.isFirstTime(), employee.getRole());
    }
}
