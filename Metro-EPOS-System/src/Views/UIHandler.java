package Views;

import Controllers.*;

import java.io.File;
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
    public static boolean isProductExist(int pID)
    {

        return cashier.isProductExist(pID,cashier.getBranch().getCode());
    }

    public static String getProductPrice( int qty)
    {
        // I will provide qty -> quantity and pID -> Product ID and I should get the price of product with datatype double
        // Here I assume 100 is the price I get for one product pID (later converted to string after multiply)

        return String.valueOf(qty * cashier.getProductPrice());
    }



    public static String getProductName(int pID)
    {

        return cashier.getProductName();
    }

    public static File showBillImage(ArrayList<String> list, int cashAmount, int additionalAmount, double discount, int branchid, boolean isVendor) throws Exception {
    return cashier.saveBill(list,cashAmount,additionalAmount,discount,branchid,isVendor);
    }
}