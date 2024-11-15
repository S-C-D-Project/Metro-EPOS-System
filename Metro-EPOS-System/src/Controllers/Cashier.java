package Controllers;

import Models.DataBaseHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class Cashier extends Employee {

    public Cashier(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime) {
        super(name, password, email, employeeNumber, branchCode, salary, joiningDate, leavingDate, isActive, branch, firstTime);
    }

    public Bill createNewBill() {
        return new Bill();
    }

    public void addProductToBill(Bill bill, int productId, int branchId, boolean isVendor, int quantity) {
        try {
            bill.addProduct(productId, branchId, isVendor, quantity);
        } catch (SQLException e) {
            System.out.println("Error adding product to bill: " + e.getMessage());
        }
    }

    public void removeProductFromBill(Bill bill, int productId, boolean isVendor) {
        bill.removeProduct(productId, isVendor);
    }

    public void applyDiscountToBill(Bill bill, double discount, boolean isVendor) {
        bill.addDiscount(discount, isVendor);
    }

    public void finalizeBill(Bill bill, int cashAmount) {
        try {
            if (bill.saveBill(cashAmount) == null) {
                System.out.println("Insufficient cash amount. Cannot save the bill.");
            } else {
                System.out.println("Bill saved successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error saving bill: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
