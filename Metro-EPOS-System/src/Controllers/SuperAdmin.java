package Controllers;

public class SuperAdmin {
    private String name;
    private String password;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SuperAdmin(String name, String password, String email, int employeeNumber, boolean isActive) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.employeeNumber = employeeNumber;
        this.isActive = isActive;
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

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static SuperAdmin getSuperAdmin() {
        return superAdmin;
    }

    public static void setSuperAdmin(SuperAdmin superAdmin) {
        SuperAdmin.superAdmin = superAdmin;
    }

    private int employeeNumber;
    private boolean isActive;

    private static  SuperAdmin superAdmin=null;
    private SuperAdmin() {
    }
    public static SuperAdmin getInstance(){
        if(superAdmin==null){
            superAdmin=new SuperAdmin();
        }
        return superAdmin;
    }
    public static SuperAdmin getInstance(String name, String password, String email, int employeeNumber, boolean isActive){
        if(superAdmin==null){
            superAdmin=superAdmin;
        }
        return superAdmin;
    }
}
