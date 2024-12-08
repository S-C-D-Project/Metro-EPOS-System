package Controllers;

import Models.DataBaseHandler;
import Models.ProductModel;
import Models.VendorModel;
import Models.PurchaseModel;
import java.sql.SQLException;

public class DataEntryOperator extends Employee {

    public DataEntryOperator( String name,String password,String email,int employeeID,int branchId, int salary, String joiningDate, String leavingDate, boolean isActive,boolean firstTime, String role) {

        super(name,password,email, employeeID, branchId,salary,joiningDate,leavingDate,isActive,firstTime,role);
    }
    public DataEntryOperator() {
        super();
    }

}
