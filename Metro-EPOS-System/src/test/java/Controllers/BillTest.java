
package Controllers;

import Controllers.Bill;
import Controllers.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillTest {

    @Test
    public void testConstructorAndGetters() {
        // Create a list of products (assuming Product class is already defined)
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 1, "Product A", "Category A", "Large", 100.0, 120, 50, 10.0));
        products.add(new Product(2, 1, "Product B", "Category B", "Medium", 50.0, 60, 100, 5.0));

        // Create Bill object using constructor
        Bill bill = new Bill(products, 200, 0, 0, 10, 10);

        // Verify the constructor initializes the bill's properties correctly
        assertEquals(200, bill.getCashAmount());
        assertEquals(0, bill.getReturnAmount());
        assertEquals(0, bill.getTotalbill());
        assertEquals(10, bill.getAdditionalCharges());
        assertEquals(10, bill.getDiscount());
        assertEquals(2, bill.getProductList().size());  // Verify product list size
    }
    @Test
    public void testCalculateBill() {
        // Create products
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 1, "Product A", "Category A", "Large", 100.0, 120, 50, 10.0));
        products.add(new Product(2, 1, "Product B", "Category B", "Medium", 50.0, 60, 100, 5.0));

        // Create Bill object
        Bill bill = new Bill(products, 200, 0, 0, 10, 0);

        // Calculate the bill
        int total = bill.calculateBill(false);  // Assuming `isVendor` is false
        assertEquals(12010, total);  // Expected total bill after applying price calculations
    }
    @Test
    public void testAddProduct() throws SQLException {
        // Create an empty Bill
        Bill bill = new Bill();

        // Add products to the bill (assuming DataBaseHandler is working properly)
        bill.addProduct(1, 1, false, 5);

        // Verify the product list size and updated bill
        assertEquals(1, bill.getProductList().size());
        assertEquals(1, bill.getProductList().get(0).getProductId());  // Verify product ID
        assertEquals(5, bill.getProductList().get(0).getStockQuantity());  // Verify quantity

        // Verify the total bill is calculated
        assertNotEquals(0, bill.getTotalbill());
    }
    @Test
    public void testAddAdditionalCharges() throws SQLException {
        // Create an empty Bill
        Bill bill = new Bill();

        // Add products and calculate bill
        bill.addProduct(1, 1, false, 2);
        bill.calculateBill(false);

        // Add additional charges
        bill.addAdditionalAmount(20, false);  // Assume `isVendor` is false

        // Verify the total bill is updated with the additional charges
        assertEquals(3360, bill.getTotalbill());  // Adjust based on actual price
    }
    @Test
    public void testAddDiscount() throws SQLException {
        // Create an empty Bill
        Bill bill = new Bill();

        // Add products and calculate bill
        bill.addProduct(1, 1, false, 2);
        bill.calculateBill(false);

        // Add a 10% discount
        bill.addDiscount(10, false);  // Assume `isVendor` is false

        // Verify the total bill after applying the discount
        assertEquals(3006, bill.getTotalbill());  // Adjust based on actual price and discount
    }



}
