package Controllers;

public class BranchManager extends Employee {
    public BranchManager(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime) {

            super(Integer.parseInt(employeeNumber), name, email,salary,joiningDate,leavingDate,isActive,Integer.parseInt(branchCode),firstTime,"Branch manager",branch); }
}
