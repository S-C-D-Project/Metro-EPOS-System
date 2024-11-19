package Views;

import Controllers.*;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class UIHandler
{
    private static  Cashier cashier=null;
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
    public static boolean isProductExist(int pID) throws SQLException {

        return cashier.isProductExist(pID,cashier.getBranch().getCode());
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

    public static File showBillImage(ArrayList<String> list, int cashAmount, int additionalAmount, double discount, int branchid, boolean isVendor) throws Exception {
    return cashier.saveBill(list,cashAmount,additionalAmount,discount,branchid,isVendor);
    }
    public  static void deleteTempBill(File file){
        cashier.deleteTempBill(file);

    }
}