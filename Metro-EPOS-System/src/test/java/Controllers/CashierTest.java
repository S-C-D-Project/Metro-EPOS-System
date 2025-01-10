package Controllers;

import Models.DataBaseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CashierTest {

    private Cashier cashier;

    @BeforeEach
    void setUp() {
        // Create a Cashier instance with the constructor
        cashier = new Cashier("John Cashier", "password123", "johncashier@example.com",
                1, 1, 50000, "2024-01-01", "2024-12-31", true, false, "Cashier");
    }

    @Test
    void testConstructorWithAllFields() {
        // Test if the Cashier is correctly initialized
        assertNotNull(cashier);
        assertEquals("John Cashier", cashier.getName());
        assertEquals(null, cashier.getPassword());
        assertEquals("johncashier@example.com", cashier.getEmail());

        assertEquals(50000, cashier.getSalary());
        assertEquals("2024-01-01", cashier.getJoiningDate());
        assertEquals("2024-12-31", cashier.getLeavingDate());
        assertTrue(cashier.isActive());
        assertEquals("Cashier", cashier.getRole());
    }

    @Test
    void testConstructorWithPhoneNumber() {
        // Create an instance of Cashier with phone number
        Cashier cashierWithPhone = new Cashier("Jane Cashier", "password456", "janecashier@example.com",
                2, 1, 45000, true, "Cashier", "123-456-7890");

        // Test if the Cashier with phone number is correctly initialized
        assertNotNull(cashierWithPhone);
        assertEquals("Jane Cashier", cashierWithPhone.getName());
        assertEquals("123-456-7890", cashierWithPhone.getPhoneNumber());
    }

    @Test
    void testDefaultConstructor() {
        // Create a Cashier instance using the default constructor
        Cashier defaultCashier = new Cashier();

        assertNotNull(defaultCashier);
        assertNull(defaultCashier.getName());  // Assumes the parent class sets default values like null for name
    }

    @Test
    void testCreateNewBill() {
        // Test if the createNewBill method returns a non-null Bill object
        Bill newBill = cashier.createNewBill();
        assertNotNull(newBill);
    }
    @Test
    void testIsProductExist_ProductExists() throws SQLException {
        // Mock Product
        Product mockProduct = mock(Product.class);
        when(mockProduct.getStockQuantity()).thenReturn(10); // Assuming the product has stock

        // Mock the static DataBaseHandler.getInstance() and getProduct() method
        try (MockedStatic<DataBaseHandler> mockedStatic = mockStatic(DataBaseHandler.class)) {
            // Mock the getInstance() to return a mock DataBaseHandler instance
            mockedStatic.when(DataBaseHandler::getInstance).thenReturn(mock(DataBaseHandler.class));

            // Mock the getProduct() method to return the mock product
            when(DataBaseHandler.getInstance().getProduct(1, 1)).thenReturn(mockProduct);

            // Now, test the isProductExist method
            boolean result = cashier.isProductExist(1, 1, 5); // Checking for 5 quantity
            assertTrue(result); // Product exists and has enough stock
        }
    }

    @Test
    void testIsProductExist_ProductDoesNotExist() throws SQLException {
        // Mock the static method of DataBaseHandler using mockStatic
        try (MockedStatic<DataBaseHandler> mockedStatic = mockStatic(DataBaseHandler.class)) {
            // When getProduct is called, return null (product not found)
            mockedStatic.when(() -> DataBaseHandler.getInstance().getProduct(1, 1)).thenReturn(null);

            // Perform the test with the mocked method
            boolean result = cashier.isProductExist(1, 1, 5); // Checking for 5 quantity
            assertFalse(result); // Product does not exist
        }
    }

    @Test
    void testGetProductPriceById() throws SQLException {
        // Mock Product
        Product mockProduct = mock(Product.class);
        when(mockProduct.getSalePrice()).thenReturn((int) 100.0);  // Return a price of 100.0

        // Mock the static DataBaseHandler.getInstance() and getProduct() method
        try (MockedStatic<DataBaseHandler> mockedStatic = mockStatic(DataBaseHandler.class)) {
            mockedStatic.when(DataBaseHandler::getInstance).thenReturn(mock(DataBaseHandler.class));
            when(DataBaseHandler.getInstance().getProduct(1, 1)).thenReturn(mockProduct);

            double price = cashier.getProductPriceByid(1, 1);
            assertEquals(100.0, price, 0.01); // Verify the price returned
        }
    }

    @Test
    void testGetProductPrice() {
        // Assuming the product is already set in Cashier object
        Product mockProduct = mock(Product.class);
        cashier.comfirmProduct = mockProduct;
        when(mockProduct.getSalePrice()).thenReturn((int) 100.0);

        double price = cashier.getProductPrice();
        assertEquals(100.0, price, 0.01); // Verify the price returned
    }

    @Test
    void testGetProductName() {
        // Assuming the product is already set in Cashier object
        Product mockProduct = mock(Product.class);
        cashier.comfirmProduct = mockProduct;
        when(mockProduct.getProductName()).thenReturn("Test Product");

        String productName = cashier.getProductName();
        assertEquals("Test Product", productName); // Verify the product name
    }

//    @Test
//    void testSaveBill() throws Exception {
//        // Mock Bill and its methods
//        Bill mockBill = mock(Bill.class);
//        when(mockBill.saveBill(anyInt())).thenReturn(new File("test_bill.txt"));
//
//        ArrayList<String> productList = new ArrayList<>();
//        productList.add("1,Product1,2"); // Product data: id, name, quantity
//        File file = cashier.saveBill(productList, 100, 20, 10.0, 1, false);
//
//        assertNotNull(file);
//
//    }

//    @Test
//    void testDeleteTempBill() {
//        // Mock Printer and its deleteTempFiles method
//        File tempFile = mock(File.class);
//        Printer mockPrinter = mock(Printer.class);
//
//        cashier.deleteTempBill(tempFile);
//        verify(mockPrinter, times(1)).deleteTempFiles(tempFile); // Verify deleteTempFiles is called once
//    }
}
