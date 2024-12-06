package Views;

import Controllers.*;
import Models.DataBaseHandler;
import Models.VendorModel;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class UIHandler {
    private static Cashier cashier = new Cashier();
    private static BranchManager branchManager = new BranchManager();
    private static DataEntryOperator dataEntryOperator = new DataEntryOperator();
    private static SuperAdmin superAdmin = null;

    public static void createCashier(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime) {
        cashier = new Cashier(name, password, email, employeeNumber, branchCode, salary, joiningDate, leavingDate, isActive, branch, firstTime);
    }

    public static void createBranchManager(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime) {
        branchManager = new BranchManager(name, password, email, employeeNumber, branchCode, salary, joiningDate, leavingDate, isActive, branch, firstTime);
    }

    public static void createDataEntryOperator(String name, String password, String email, String employeeNumber, String branchCode, int salary, String joiningDate, String leavingDate, boolean isActive, Branch branch, boolean firstTime) {
        dataEntryOperator = new DataEntryOperator(name, password, email, employeeNumber, branchCode, salary, joiningDate, leavingDate, isActive, branch, firstTime);
    }

    public static boolean isProductExist(int pID, int qty) throws SQLException {

        return cashier.isProductExist(pID, cashier.getBranch().getId(), qty);
    }

    public static String getProductPrice(int qty) {
        return String.valueOf(qty * cashier.getProductPrice());
    }

    public static double getProductPriceUsingName(int id, int branchid, int qty) throws SQLException {

        return cashier.getProductPriceByid(id, branchid) * qty;
    }

    public static String getProductName(int pID) {

        return cashier.getProductName();
    }

    public static File showBillImage(ArrayList<String> list, double cashAmount, double additionalAmount, double discount, int branchid, boolean isVendor) throws Exception {
        return cashier.saveBill(list, (int) cashAmount, (int) additionalAmount, discount, branchid, isVendor);
    }

    public static void deleteTempBill(File file) {
        cashier.deleteTempBill(file);

    }

    public static String isValidAdmin(String id, String pass) throws SQLException {
        // string is passed such that we can verify if even the string is valid or not
        // you can use isNumber function of UIHandler as well for validation
        // If admin is valid It should return the name of the admin, if not then it should return "not"

        if (superAdmin != null) {
            superAdmin = SuperAdmin.getInstance(id, pass, "superAdmin");

            return superAdmin.getName() + "," + superAdmin.getEmployeeNumber();
        } else {
            return "not";
        }
    }

    public static String isValidManager(String id, String pass) throws SQLException {
        // string is passed such that we can verify if even the string is valid or not
        // you can use isNumber function of UIHandler as well for validation
        // If manager is valid It should return the name,branchID of the manager comma separated,
        // if not then it should return "not"

        if (branchManager != null) {
            branchManager = (BranchManager) branchManager.vallidateEmployee(id, pass, "branchManager");

            return branchManager.getName() + "," + branchManager.getEmployeeNumber();
        } else {
            return "not";
        }
    }

    public static String isValidCashier(String id, String pass) throws SQLException {
        if (cashier != null) {
            cashier = (Cashier) cashier.vallidateEmployee(id, pass, "cashier");
            if (cashier != null) {

                return cashier.getName() + "," + cashier.getEmployeeNumber();
            } else {
                return "not";
            }
        } else {
            return "not";
        }
    }


    public static String isValidDataOperator(String id, String pass) throws SQLException{
        // string is passed such that we can verify if even the string is valid or not
        // you can use isNumber function of UIHandler as well for validation
        // If operator is valid It should return the name,branchID of the operator comma separated,
        // if not then it should return "not
        dataEntryOperator=new DataEntryOperator();
        dataEntryOperator= (DataEntryOperator) dataEntryOperator.vallidateEmployee(id,pass,"dataEntryOperator");


        boolean result= DataBaseHandler.isValidDataOperator(id,pass);
        String name= DataBaseHandler.getEmployeeName(id);
        dataEntryOperator.setName(name);
        String branch= DataBaseHandler.getEmployeeBranch(id);
      
        dataEntryOperator.setBranchid(Integer.parseInt(branch));
        if(result){
            return name+","+branch;
        }
        else{
            return "not";
        }
    }

    public static ArrayList<String> getVendorsList(int branchID) {
        // I will provide the branchID and I should get all the vendors in comma separated string list
        //For now branch id is set to 1
        ArrayList<String> list =  DataBaseHandler.getVendorsList(branchID);
        return list;
    }

    public static ArrayList<String> updateVendorInfo(int id, String str) {
        // I will provide with the vendor id and a string str where data is stored comma separated
        // like (Name,City,Address,Products,Status) and we update vendor data in DB using vendor ID
        // then the updated VendorList is returned
        // below code is just for testing
        String[]values=str.split(",");
        DataBaseHandler.updateVendorInfo(id,values[0],values[1],values[2],values[4]);
        ArrayList<String> list=DataBaseHandler.getVendorsList(dataEntryOperator.getBranchid());
        return list;
    }

    public static ArrayList<String> addVendor(int branchId, String vendorName, String vendorAddress, String vendorCity) {
        // I will provide with the branch id and strings to add in vendors list
        // then the updated VendorList is returned
        // below code is just for testing
        String status="Inactive";
        VendorModel.insertVendor(vendorName,vendorCity,vendorAddress,status,branchId);
        return getVendorsList(branchId);

    }

    public static ArrayList<String> getVendorProducts(int Vid) {
        // I provide vendor ID and I get all its products in comma separate
        // list like (Category,Name,Original Price,Sale Price,Price Per Units)
        return DataBaseHandler.getVendorProducts(Vid);
    }

    public static ArrayList<String> addNewVendorProduct(int vID, String str) {
        // I will provide the Vendor ID and Product in string comma separated (Catagory,Name,Original Price, Sale Price, Price Per Unit)
        // the new product should be added against the vendor and should return the updated list of this vendor
        String[]values=str.split(",");
        String sample_manufaturer="Sample Factory";
        int branch= dataEntryOperator.getBranchid();
        DataBaseHandler.addOrUpdateProductAndPurchase(branch,values[1],values[0],sample_manufaturer,Float.parseFloat(values[2]), Integer.parseInt(values[3]),
                Float.parseFloat(values[4]),vID,DataBaseHandler.getVendorName(vID));

       ArrayList<String> list=DataBaseHandler.getVendorProducts(vID);
        return list;
    }

    public static ArrayList<String> updateVendorProductInfo(int vID, String str, String productName) {
        // here you get Vendor ID with its product name to update info of
        // in str we get update data with comma seperated (Catagory,Name,Original Price, Sales Price, Price Per Unit)
        // this function updates the product and return the update list of particular vendor products
            // here you get Vendor ID with its product name to update info of
            // in str we get update data with comma seperated (Catagory,Name,Original Price, Sales Price, Price Per Unit)
            // this function updates the product and return the update list of particular vendor products
        String[]values=str.split(",");
        int productid=DataBaseHandler.getProductidbyName(productName.trim());
        boolean result=DataBaseHandler.updateProductInfo(vID,productid,values[1],values[0],values[2], Integer.parseInt(values[3]),values[4]);
        if(!result) {
            return null;
        }
        else{
        ArrayList<String> list= DataBaseHandler.getVendorProducts(vID);
        return list;
        }


    }

    public static ArrayList<String> deleteVendorProduct(int id, String catagory, String name, String originalPrice, String salesPrice, String pricePerUnit) {
        // here the product of a vendor should be deleted
        // I provide Vendor ID, catagory, name and many other values so you can use any of these or all of these
        // for deletion, after that I should get the updated list of particular vendor products
        int prodid=DataBaseHandler.getProductidbyName(name);
        boolean result=DataBaseHandler.deleteProductByVendorId(id,prodid);
        if(!result){
            return null;
        }
        ArrayList<String> list = DataBaseHandler.getVendorsList(dataEntryOperator.getBranchid());
        return list;
    }

    public static ArrayList<String> getEmployeeInfo(int branchID){
        // based on branchID it should return employees of that branch
        ArrayList<String> list = new ArrayList<>();
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        return list;
    }

    public static ArrayList<String> addEmployeeInfo(int branchID ,String str) {
        //here I provide with the empID and branchID and updated String (name,salary,phoneNo,role)
        // it should return the updated employees list of the particular branch;

        ArrayList<String> list = new ArrayList<>();
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        return list;
    }

    public static ArrayList<String> updateEmployeeInfo(int empID, int branchID ,String str) {
        //here I provide with the empID and branchID and updated String (name,email,password,salary,phoneNo,role,status)
        // it should return the updated employees list of the particular branch list;

        ArrayList<String> list = new ArrayList<>();
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        list.add("000,Ali,000@gmail.com,Password_123,500,12345678901,Cashier,Operator");
        return list;
    }

    public static boolean isNumbers(String line) {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean changePasswordCashier(String newPassword) throws SQLException {
        return cashier.changePassword(newPassword);
    }

    public static boolean changePasswordDataEntryOperator(String newPassword) throws SQLException {
        return dataEntryOperator.changePassword(newPassword);
    }

    public static boolean changePasswordBranchManager(String newPassword) throws SQLException {
        return branchManager.changePassword(newPassword);
    }

    public static boolean changePasswordSuperAdmin(String newPassword) throws SQLException {
        return superAdmin.changePassword(newPassword);
    }
    public static boolean addEmployee(String name, String email,  int salary  ,int branchid, String role) {
        return branchManager.addEmployee(name, email, salary, branchid, role);
    }
    public static boolean checkInternetConnection(){
        return InternetConnection.isInternetConnected();
    }
    public static boolean updateEmployee(Employee employee) {
        return branchManager.updateEmployee(employee);
    }
}