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

}
