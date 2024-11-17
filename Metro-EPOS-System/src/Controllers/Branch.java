package Controllers;

public class Branch {

    private String name;
    private int code;
    private String address;
    private String phoneNumber;
    private int numberOfEmployees;
    private boolean isActive;

    public Branch(String name, int code, String address, String phoneNumber, int numberOfEmployees, boolean isActive) {
        this.name = name;
        this.code = code;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numberOfEmployees = numberOfEmployees;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
