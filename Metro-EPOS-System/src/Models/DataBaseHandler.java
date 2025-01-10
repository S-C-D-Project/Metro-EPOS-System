package Models;

import Controllers.Bill;
import Controllers.Branch;
import Controllers.Product;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseHandler {
    private static DataBaseHandler instance = null;
    private static Connection connection;

    private DataBaseHandler() throws SQLException {
        connection = DataBaseConnection.getConnection();
    }

    public static DataBaseHandler getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseHandler();
        }
        return instance;
    }
    public static int getTotalProfit(int branchid,String type) {
    return BranchModel.getTotalProfit(branchid,type);
    }
        public static Branch getBranch(int id) {
        return BranchModel.getBranchById(id, connection);
    }

    public static Object getEmployee(String id, String password, String choice) {
        return EmployeeModel.validateEmployee(id, password, connection, choice);
    }

    public static double getSalesTax() {
        return TaxModel.getSalesTax(connection);
    }

    public static Product getProduct(int productId, int branchId) {
        return ProductModel.getProduct(productId, connection, branchId);
    }
    public static int getTotalStockQuantity(int branchId, String type) {
    return BranchModel.getTotalStockQuantity(branchId,type);
    }
    public static ArrayList<String> getVendorPurchaseData() {
    return VendorModel.getVendorPurchaseData();
    }
    public static boolean updateVendorData(int vid, String[] data) {
    return VendorModel.updateVendorData(vid,data);
    }
        public static ArrayList<String> getProductsByBranchId(int branchId){
    return BranchModel.getProductsByBranchId(branchId);
    }
        public static int saveBill(Bill bill) throws SQLException {
        return BillModel.saveBill(connection, bill.getCashAmount(), bill.getReturnAmount(),
                bill.getTotalbill(), bill.getAdditionalCharges(), bill.getSalesTaxAmount(),
                bill.getDiscount(), bill.getProductList());
    }
    public static int getTotalSales(int branchId, String type) {
    return BranchModel.getTotalSales(branchId,type);
    }

        public static void DecreaseProductQuantity(int pid, int qty) {
        ProductModel.DecreaseProductQuantity(pid, qty, connection);
    }

    public static int addEmployee(String name, String email, int salary, int branchid, String role) {
        return EmployeeModel.addEmployee(name, salary, branchid, role, connection);
    }

    public static ArrayList<String> getProductStockStatus(int branchid) {
        return ProductModel.getProductStockStatus(branchid);
    }

    public static ArrayList<String> Bills() {
        return ProfitModel.Bills();
    }

    public static boolean updateEmployee(int employeeId, String name, String email,String password, int branch, int salary, boolean active,  String role,String phoneNumber) {
        return EmployeeModel.updateEmployee(employeeId, name, email,password, branch, salary, active, role, phoneNumber,connection);
    }
    public static boolean insertProduct(int productid,int branchId, String productName, String category, String manufacturer,
                                        double originalPrice, int salePrice,
                                        int stockQuantity, String productSize, double salestax,double pricePerUnit) {
        return ProductModel.insertProduct(productid,branchId,productName,category,manufacturer,originalPrice,salePrice,pricePerUnit,stockQuantity,productSize,salestax);
    }
    public static void insertEmployeeMigration(int empid,String name, String password, String email, int branchId, double salary,
                                               String joiningDate, String leavingDate, boolean isActive,
                                               boolean firstTime, String role) {
         EmployeeModel.insertEmployeeMigration(empid,name,password,email,branchId,salary,joiningDate,leavingDate,isActive,firstTime,role);
    }
    public static void insertBillProductMigrationForeign(int billId, int productId, int price, int quantity) throws InterruptedException {
    BillModel.insertBillProductMigrationForeign(billId,productId,price,quantity);
    }
    public static void insertGraph(String monthlyid,String profitDate, int profit) {
    ProfitModel.insertGraph(monthlyid,profitDate,profit);
    }
    public static void insertPurchaseForeign(int purchaseid,int vendorId, String vendorName, int productId) {
    PurchaseModel.insertPurchaseForeign(purchaseid,vendorId,vendorName,productId);
    }
        public static void insertMonthlyProfit(String monthlyId, String time, int profit) {
    ProfitModel.insertMonthlyProfit(monthlyId,time,profit);
    }
        public static void insertBillMigration(int billid,int cashAmount, int returnAmount, int totalBill, int additionalCharges,
                                           double discount, String billDate, int salesTaxAmount) {
    BillModel.insertBillMigration(billid,cashAmount,returnAmount,totalBill,additionalCharges,discount,billDate,salesTaxAmount);
    }
        public int insertProductData(int branchId, String productName, String category,
                                 double originalPrice, int salePrice, double pricePerUnit, String productSize,
                                 int stockQuantity, String manufacturer) throws SQLException {

        if (ProductModel.productExists(productName, branchId, connection)) {
            System.out.println("Error: Product with name '" + productName + "' already exists in branch " + branchId);
            return -1;
        }

        String storedProcCall = "{CALL InsertProductData(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        int productId = -1;

        // Use existing connection, no need to open a new one
        double salesTax = getSalesTax();
        double temporary = salesTax;
        salesTax = salesTax / 100 * originalPrice;
        salePrice += (int) salesTax;

        try (CallableStatement callableStatement = connection.prepareCall(storedProcCall)) {
            callableStatement.setInt(1, branchId);         // BranchID (INT)
            callableStatement.setString(2, productName);   // ProductName (NVARCHAR)
            callableStatement.setString(3, category);      // Category (NVARCHAR)
            callableStatement.setDouble(4, originalPrice); // OriginalPrice (FLOAT)
            callableStatement.setInt(5, salePrice);        // SalePrice (INT)
            callableStatement.setDouble(6, pricePerUnit);  // PricePerUnit (FLOAT)
            callableStatement.setString(7, productSize);   // ProductSize (NVARCHAR)
            callableStatement.setInt(8, stockQuantity);    // StockQuantity (INT)
            callableStatement.setDouble(9, temporary);     // SalesTax (DOUBLE)
            callableStatement.setString(10, manufacturer); // Manufacturer (NVARCHAR)
            callableStatement.registerOutParameter(11, java.sql.Types.INTEGER);  // Output ProductID

            callableStatement.executeUpdate();
            productId = callableStatement.getInt(11);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productId;
    }


    public static boolean changePassword(String newPassword, int id) {
        return EmployeeModel.changePassword(newPassword, id, connection);
    }

    public static boolean isValidDataOperator(String id, String pass) {
        return EmployeeModel.isValidDataOperator(id, pass);
    }

    public static String getEmployeeName(String id) {
        return EmployeeModel.getEmployeeName(id);
    }

    public static ArrayList<String> getVendorsList(int branchid) {
        return VendorModel.getVendorsList(branchid);
    }

    public static String getEmployeeBranch(String id) {
        return EmployeeModel.getEmployeeBranch(id);
    }
    public static ArrayList<String> getAllProductsLocal() {
    return ProductModel.getAllProductsLocal();
    }
        public static boolean updateVendorInfo(int id, String newName, String newCity, String newAddress, String status) {
        return VendorModel.updateVendorInfo(id, newName, newCity, newAddress, status);
    }

    public static boolean insertVendor(int vendorid,String vendorName, String city, String address, String status, int branchId) {
        return VendorModel.insertVendor(vendorid,vendorName, city, address, status, branchId);
    }

    public static int addOrUpdateProductAndPurchase(int branchId, String productName, String category, String manufacturer, float originalPrice, int salePrice, float pricePerUnit, int vendorId, String vendorName, String size, int stocks) {
        return PurchaseModel.addOrUpdateProductAndPurchase(branchId, productName, category, manufacturer, originalPrice, salePrice, pricePerUnit, vendorId, vendorName, size, stocks);
    }

    public static String getVendorName(int vendorId) {
        return VendorModel.getVendorName(vendorId);
    }

    public static ArrayList<String> getVendorProducts(int vendorId) {
        return VendorModel.getVendorProducts(vendorId);
    }

    public static boolean updateProductInfo(int vendorId, int productId, String productName,
                                            String category, String originalPrice,
                                            int salePrice, String pricePerUnit, int Stocks, String Man, String size) {
        return VendorModel.updateProductInfo(vendorId, productId, productName, category, originalPrice, salePrice, pricePerUnit, Stocks, Man, size);
    }

    public static int getProductidbyName(String PrdouctName) {
        return ProductModel.getProductidbyName(PrdouctName);
    }

    public static boolean deleteProductByVendorId(int vendorId, int prodcutid) {
        return VendorModel.deleteProductByVendorId(vendorId, prodcutid);
    }

    public static ArrayList<Integer> getProfitData(String period) {
        return ProfitModel.getProfitData(period);
    }

    public static ArrayList<Integer> getProfitDataForTimeSlot(String startDate, String endDate) {
        return ProfitModel.getProfitDataForTimeSlot(startDate, endDate);
    }

    public static ArrayList<Integer> getMonthlyProfitData(String startDate, String endDate) {
        return ProfitModel.getMonthlyProfitData(startDate, endDate);
    }

    public static ArrayList<String> getProfitAndTimeForToday() {
        return ProfitModel.getProfitAndTimeForToday();
    }

    public static ArrayList<String> getProfitAndTimeForSpecificDay(String date) {
        return ProfitModel.getProfitAndTimeForSpecificDay(date);
    }

    public static ArrayList<String> getEmployeesByBranch(int branchID) {
        return EmployeeModel.getEmployeesByBranch(connection, branchID);
    }
    public static int  addBranch(String name,String address, String city) throws SQLException {
return BranchModel.addBranch(connection,name,address,city);
    }
    public static String getAllBranchIds() throws SQLException {
        return BranchModel.getAllBranchIds(connection);
    }
    public static String[] getAllBranches() throws SQLException {return BranchModel.getAllBranches(connection);}
    public static void updateBranch(int branchID,String name,String city,String address,int numberOfEmployees,Boolean status) throws SQLException {
        BranchModel.updateBranch(branchID, name, city, address, numberOfEmployees, status,connection);

    }
    public static ArrayList<String> getEmployees(){
return EmployeeModel.getEmployees(connection);
    }
    public static ArrayList<String> getAllEmployees(){
        return EmployeeModel.getAllEmployees(connection);
    }

}

