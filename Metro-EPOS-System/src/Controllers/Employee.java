package Controllers;

public abstract class Employee {
    private String name;
    private String password;
    private String email;
    private String employeeNumber;
    private String branchCode;
    private int salary;
    private String joiningDate;
    private String leavingDate;
    private boolean isActive;
private Branch branch;
private boolean firstTime;

    public Employee(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.employeeNumber = employeeNumber;
        this.branchCode = branchCode;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.leavingDate = leavingDate;
        this.isActive = isActive;
        this.branch = branch;
        this.firstTime = firstTime;
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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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
}
