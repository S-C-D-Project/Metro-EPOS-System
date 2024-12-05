package Controllers;

import Models.DataBaseHandler;
import Models.ProductModel;
import Models.VendorModel;
import Models.PurchaseModel;
import java.sql.SQLException;

public class DataEntryOperator extends Employee {

    public DataEntryOperator(String name, String password, String email, String employeeNumber,
                             String branchCode, int salary, String joiningDate, String leavingDate,
                             boolean isActive, Branch branch, boolean firstTime) {
        super(Integer.parseInt(employeeNumber), name, email,salary,joiningDate,leavingDate,isActive,Integer.parseInt(branchCode),firstTime,"Data Entry operator",null);
    }
    public DataEntryOperator() {
        super();
    }

    public int selectOrAddVendor(String vendorName, String contactInfo) {
        try {
            int vendorId = VendorModel.getVendorIdByName(vendorName);
            if (vendorId > 0) {
                System.out.println("Vendor already exists. Vendor ID: " + vendorId);
                return vendorId;
            } else {

                vendorId = addVendor(vendorName, contactInfo);
                return vendorId;
            }
        } catch (SQLException e) {
            System.out.println("Error checking or adding vendor: " + e.getMessage());
            return -1;
        }
    }


    public int addProduct(int branchId, String productName, String category, double originalPrice,
                          int salePrice, double pricePerUnit, String productSize, int stockQuantity,
                          String manufacturer) {
        try {

            int productId = DataBaseHandler.getInstance().insertProductData(branchId, productName, category,
                    originalPrice, salePrice, pricePerUnit,
                    productSize, stockQuantity, manufacturer);
            if (productId > 0) {
                System.out.println("Product added successfully with Product ID: " + productId);
            } else {
                System.out.println("Failed to add the product.");
            }
            return productId;
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            return -1;
        }
    }





}
