package Models;

import Controllers.Bill;
import Controllers.Product;

import java.sql.Connection;
import java.sql.SQLException;

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

    public static double getSalesTax() {
        return TaxModel.getSalesTax(connection);
    }

    public static Product getProduct(int productId, int branchId) {

        return ProductModel.getProduct(productId, connection, branchId);
    }

    public static int saveBill(Bill bill) {
        return BillModel.saveBill(connection, bill.getCashAmount(), bill.getReturnAmount(), bill.getTotalbill(), bill.getAdditionalCharges(), bill.getSalesTaxAmount(), bill.getDiscount(), bill.getProductList());

    }

    public static void main(String[] args) throws SQLException {

    }
}
