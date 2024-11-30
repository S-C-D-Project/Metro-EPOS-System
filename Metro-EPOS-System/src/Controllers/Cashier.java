package Controllers;

import Models.DataBaseHandler;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cashier extends Employee {
private Product comfirmProduct;
    public Cashier(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime) {
        super(Integer.parseInt(employeeNumber), name, email,salary,joiningDate,leavingDate,isActive,Integer.parseInt(branchCode),firstTime,"Cashier",null); }

    public Bill createNewBill() {
        return new Bill();
    }

    public Cashier(int employeeID, String name, String email, int salary, String joiningDate, String leavingDate, boolean isActive, int branchID, boolean firstTime, String role, Branch branch, Product comfirmProduct) {
        super(employeeID, name, email, salary, joiningDate, leavingDate, isActive, branchID, firstTime, role, branch);
        this.comfirmProduct = comfirmProduct;
    }
    public Cashier() {
        super();
     }

    public boolean isProductExist(int productId, int branchId, int qty) throws SQLException {
        comfirmProduct=DataBaseHandler.getInstance().getProduct(productId, branchId);
        if(comfirmProduct!=null&&comfirmProduct.getStockQuantity()>=qty){
            return true;
        }
        return false;
    }
    public double getProductPriceByid(int productId,int branchId) throws SQLException {
        return DataBaseHandler.getInstance().getProduct(productId, branchId).getSalePrice();

    }

    public  double getProductPrice() {
    return comfirmProduct.getSalePrice();
    }

    public  String getProductName() {
        return comfirmProduct.getProductName();
    }



    public File saveBill(ArrayList<String> list, int cashAmount, int additionalAmount, double discount, int branchid, boolean isVendor) throws Exception {
       Bill bill=new Bill();

       for(String product:list){
           String data[]=product.split(",");
           bill.addProduct(Integer.parseInt(data[0]),branchid,isVendor,Integer.parseInt(data[2]));

       }
       bill.addAdditionalAmount(additionalAmount,isVendor);
       bill.addDiscount(discount,isVendor);

return  bill.saveBill(cashAmount);

    }
    public void deleteTempBill(File file){
        Printer.deleteTempFiles(file);
    }
}
