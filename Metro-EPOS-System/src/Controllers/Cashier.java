package Controllers;

import Models.DataBaseHandler;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cashier extends Employee {
private Product comfirmProduct;
    public Cashier(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime) {
        super(name, password, email, employeeNumber, branchCode, salary, joiningDate, leavingDate, isActive, branch, firstTime);
    }

    public Bill createNewBill() {
        return new Bill();
    }


    public boolean isProductExist(int productId,int branchId){
        comfirmProduct=DataBaseHandler.getProduct(productId, branchId);
        if(comfirmProduct!=null){
            return true;
        }
        return false;
    }
    public  double getProductPrice() {
    return comfirmProduct.getPricePerUnit();
    }
    public  String getProductName() {
        return comfirmProduct.getProductName();
    }



    public File saveBill(ArrayList<String> list, int cashAmount, int additionalAmount, double discount, int branchid, boolean isVendor) throws Exception {
       Bill bill=new Bill();
       for(String product:list){
           String data[]=product.split(",");
           bill.addProduct(Integer.parseInt(data[0]),branchid,isVendor,Integer.parseInt(data[1]));

       }
       bill.addAdditionalAmount(additionalAmount,isVendor);
       bill.addDiscount(discount,isVendor);
       return bill.saveBill(cashAmount);
       // Printer.deleteTempFiles(tempDir); add it in gui

    }
}
