package Views;

import Controllers.*;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class UIHandler
{
    private static  Cashier cashier=new Cashier();
    private static BranchManager branchManager=null;
    private static DataEntryOperator dataEntryOperator=null;
    public static void createCashier(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime){
        cashier=new Cashier(name, password,  email,  employeeNumber,  branchCode, salary,  joiningDate,  leavingDate,  isActive,  branch,  firstTime);
    }
    public static void createBranchManager(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime){
        branchManager=new BranchManager(name, password,  email,  employeeNumber,  branchCode, salary,  joiningDate,  leavingDate,  isActive,  branch,  firstTime);
    }
    public static void createDataEntryOperator(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime){
        dataEntryOperator=new DataEntryOperator(name, password,  email,  employeeNumber,  branchCode, salary,  joiningDate,  leavingDate,  isActive,  branch,  firstTime);
    }
    public static boolean isProductExist(int pID,int qty) throws SQLException {

        return cashier.isProductExist(pID,cashier.getBranch().getId(),qty);
    }

    public static String getProductPrice(int qty)
    {
        return String.valueOf(qty * cashier.getProductPrice());
    }

    public static double getProductPriceUsingName(int id,int branchid,int qty) throws SQLException {

        return cashier.getProductPriceByid(id,branchid)*qty;
    }

    public static String getProductName(int pID)
    {

        return cashier.getProductName();
    }

    public static File showBillImage(ArrayList<String> list, double cashAmount, double additionalAmount, double discount, int branchid, boolean isVendor) throws Exception {
        return cashier.saveBill(list, (int) cashAmount, (int) additionalAmount,discount,branchid,isVendor);
    }
    public  static void deleteTempBill(File file){
        cashier.deleteTempBill(file);

    }

    public static String isValidAdmin(String id, String pass){
        // string is passed such that we can verify if even the string is valid or not
        // you can use isNumber function of UIHandler as well for validation
        // If admin is valid It should return the name of the admin, if not then it should return "not"

        if(true){
            return "Asfandyar";
        }
        else{
            return "not";
        }
    }
    public static String isValidManager(String id, String pass){
        // string is passed such that we can verify if even the string is valid or not
        // you can use isNumber function of UIHandler as well for validation
        // If manager is valid It should return the name,branchID of the manager comma separated,
        // if not then it should return "not"

        if(true){
            return "Asfandyar,1234";
        }
        else{
            return "not";
        }
    }
    public static String isValidCashier(String id, String pass) throws SQLException {
        cashier= (Cashier) cashier.vallidateEmployee(id,pass,"cashier");
        if(cashier!=null){
            return cashier.getName()+","+cashier.getEmployeeNumber();
        }
        else{
            return "not";
        }
    }
    public static String isValidDataOperator(String id, String pass){
        // string is passed such that we can verify if even the string is valid or not
        // you can use isNumber function of UIHandler as well for validation
        // If operator is valid It should return the name,branchID of the operator comma separated,
        // if not then it should return "not"

        if(true){
            return "Asfandyar,1234";
        }
        else{
            return "not";
        }
    }

    public static ArrayList<String> getVendorsList(int branchID){
        // I will provide the branchID and I should get all the vendors in comma separated string list
        ArrayList<String> list = new ArrayList<>();
        list.add("22,Asfandyar,Lahore,170-D Rehman,2,Inactive");
        list.add("22,Asfandyar,Lahore,170-D Rehman,2,Inactive");
        list.add("22,Asfandyar,Lahore,170-D Rehman,2,Inactive");
        list.add("22,Asfandyar,Lahore,170-D Rehman,2,Inactive");
        return list;
    }
    public static ArrayList<String> updateVendorInfo(int id,String str){
        // I will provide with the vendor id and a string str where data is stored comma separated
        // like (Name,City,Address,Products,Status) and we update vendor data in DB using vendor ID
        // then the updated VendorList is returned
        // below code is just for testing
        ArrayList<String> list = new ArrayList<>();
        list.add("22,Asfandyar,Lahore,170-D Rehman,2,InActive");
        list.add("22,Asfandyar,Lahore,170-D Rehman,2,InActive");
        return list;
    }
    public static ArrayList<String> addVendor(int branchId,String vendorName, String vendorAddress, String vendorCity){
        // I will provide with the branch id and strings to add in vendors list
        // then the updated VendorList is returned
        // below code is just for testing
        ArrayList<String> list = new ArrayList<>();
        list.add("22,Asfandyar,Lahore,170-D Rehman,2,InActive");
        list.add("22,Asfandyar,Lahore,170-D Rehman,2,InActive");
        return list;
    }

    public static ArrayList<String> getVendorProducts(int Vid){
        // I provide vendor ID and I get all its products in comma separate
        // list like (Category,Name,Original Price,Sale Price,Price Per Units)
        ArrayList<String> list = new ArrayList<>();
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        return list;
    }
    public static ArrayList<String> addNewVendorProduct(int vID,String str){
        // I will provide the Vendor ID and Product in string comma separated (Catagory,Name,Original Price, Sale Price, Price Per Unit)
        // the new product should be added against the vendor and should return the updated list of this vendor
        ArrayList<String> list = new ArrayList<>();
        list.add("Vegetable,Potato,300,500,20000");
        return list;
    }
    public static ArrayList<String> updateVendorProductInfo(int vID,String str, String productName){
        // here you get Vendor ID with its product name to update info of
        // in str we get update data with comma seperated (Catagory,Name,Original Price, Sales Price, Price Per Unit)
        // this function updates the product and return the update list of particular vendor products
        ArrayList<String> list = new ArrayList<>();
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        return list;
    }
    public static ArrayList<String> deleteVendorProduct(int id, String catagory, String name, String originalPrice, String salesPrice, String pricePerUnit){
        // here the product of a vendor should be deleted
        // I provide Vendor ID, catagory, name and many other values so you can use any of these or all of these
        // for deletion, after that I should get the updated list of particular vendor products

        ArrayList<String> list = new ArrayList<>();
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        list.add("Food,Milk,300,500,20000");
        return list;
    }

    public static boolean isNumbers(String line) {
        for(int i=0; i<line.length(); i++){
            char c = line.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}