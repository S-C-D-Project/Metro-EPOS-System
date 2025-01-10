package Controllers;

import Controllers.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductConstructorAndGetters() {
        // Create a new Product object using the constructor
        Product product = new Product(1, 101, "Laptop", "Electronics", "15 inch", 1000.00, 900, 50, 10.0);

        // Assert that the values set via the constructor are correctly retrieved
        assertEquals(1, product.getProductId());
        assertEquals(101, product.getBranchId());
        assertEquals("Laptop", product.getProductName());
        assertEquals("Electronics", product.getCategory());
        assertEquals("15 inch", product.getProductSize());
        assertEquals(1000.00, product.getOriginalPrice());
        assertEquals(900, product.getSalePrice());
        assertEquals(50, product.getStockQuantity());
        assertEquals(10.0, product.getSalesTax());
    }
    @Test
    public void testProductSetters() {
        // Create an empty Product object
        Product product = new Product();

        // Set values using setter methods
        product.setProductId(1);
        product.setBranchId(101);
        product.setProductName("Smartphone");
        product.setCategory("Electronics");
        product.setProductSize("6 inch");
        product.setOriginalPrice(500.00);
        product.setSalePrice(450);
        product.setStockQuantity(100);
        product.setSalesTax(5.0);
        product.setManufacturer("XYZ Corp");

        // Assert that the values set by setters are correctly retrieved
        assertEquals(1, product.getProductId());
        assertEquals(101, product.getBranchId());
        assertEquals("Smartphone", product.getProductName());
        assertEquals("Electronics", product.getCategory());
        assertEquals("6 inch", product.getProductSize());
        assertEquals(500.00, product.getOriginalPrice());
        assertEquals(450, product.getSalePrice());
        assertEquals(100, product.getStockQuantity());
        assertEquals(5.0, product.getSalesTax());
        assertEquals("XYZ Corp", product.getManufacturer());
    }
    @Test
    public void testToString() {
        // Create a new Product object
        Product product = new Product(1, 101, "Tablet", "Electronics", "10 inch", 300.00, 250, 200, 7.0);

        // Expected string representation
        String expected = "Product{productId=1, branchId=101, productName='Tablet', category='Electronics', productSize='10 inch', originalPrice=300.0, salePrice=250, stockQuantity=200, salesTax=7.0, manufacturer='null'}";

        // Assert that the toString method returns the correct string
        assertEquals(expected, product.toString());
    }
    @Test
    public void testNoArgConstructor() {
        // Create a Product using the no-argument constructor
        Product product = new Product();

        // Assert that the default values are null or 0
        assertEquals(0, product.getProductId());
        assertEquals(0, product.getBranchId());
        assertNull(product.getProductName());
        assertNull(product.getCategory());
        assertNull(product.getProductSize());
        assertEquals(0.0, product.getOriginalPrice());
        assertEquals(0, product.getSalePrice());
        assertEquals(0, product.getStockQuantity());
        assertEquals(0.0, product.getSalesTax());
        assertNull(product.getManufacturer());
    }
}
