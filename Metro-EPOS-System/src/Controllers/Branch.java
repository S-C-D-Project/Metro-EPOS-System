package Controllers;

import Models.BranchModel;
import Models.DataBaseHandler;

import java.sql.SQLException;

public class Branch {

    private int id;
    private String address;
    private String phoneNumber;
    private int numberOfEmployees;
    private boolean isActive;

    public Branch(int code, String address, String phoneNumber, int numberOfEmployees, boolean isActive) {
        this.id = code;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numberOfEmployees = numberOfEmployees;
        this.isActive = isActive;
    }
    public Branch(int id){
        Branch branch=DataBaseHandler.getBranch(id);
        if(branch==null) {
            this.id = 1;
            this.address = "700b";
            this.phoneNumber = "030394124";
            this.numberOfEmployees = 32;
            this.isActive = false;
            return;
        }
        this.id=branch.getId();
        this.address=branch.getAddress();
        this.phoneNumber=branch.getPhoneNumber();
        this.numberOfEmployees=branch.getNumberOfEmployees();
        this.isActive=branch.isActive();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
