package Models;

import Controllers.Bill;
import Controllers.Product;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseHandler {
    private static DataBaseHandler instance = null;
    private static Connection connection;


    private DataBaseHandler() throws SQLException {
        connection = Models.InternetConnection.getConnection();
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

    public static Product getProduct(int productId,int branchId) {

        return ProductModel.getProduct(productId,connection,branchId);
    }
public static boolean saveBill(Bill bill){

}
    public static void main(String[] args) throws SQLException {

    }
}
