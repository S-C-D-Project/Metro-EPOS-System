package Controllers;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

class BillTest {

    private Bill bill;

    @BeforeEach
    void setUp() {
        bill = new Bill();
    }

    @Test
    void calculateBill() {
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setSalePrice(100);
        product.setStockQuantity(2);
        product.setSalesTax(10);
        products.add(product);
        bill.setProductList(products);
        bill.setAdditionalCharges(20);
        bill.setDiscount(5);

        int totalBill = bill.calculateBill(false);
        assertEquals(225, totalBill, "Total bill calculation is incorrect.");
    }

    @Test
    void addProduct() throws SQLException {
        int productId = 1;
        int branchId = 1;
        int quantity = 5;
        boolean isVendor = false;

        bill.addProduct(productId, branchId, isVendor, quantity);

        assertFalse(bill.getProductList().isEmpty(), "Product list should not be empty after adding a product.");
    }

    @Test
    void addAdditionalAmount() {
        bill.addAdditionalAmount(50, false);
        assertEquals(50, bill.getAdditionalCharges(), "Additional charges were not set correctly.");
    }

    @Test
    void addDiscount() {
        bill.setTotalbill(200);
        bill.addDiscount(10, false);
        assertEquals(20, bill.getDiscount(), "Discount calculation is incorrect.");
    }

    @Test
    void saveBill() throws Exception {
        bill.setTotalbill(200);
        int cashAmount = 300;
        File savedBill = bill.saveBill(cashAmount);
        assertNotNull(savedBill, "Bill file should be saved successfully.");
    }
}
