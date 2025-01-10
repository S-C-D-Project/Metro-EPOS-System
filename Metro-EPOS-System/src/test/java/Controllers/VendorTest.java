package Controllers;

import Controllers.Product;
import Controllers.Vendor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class VendorTest {

    @Test
    public void testConstructorAndGetters() {
        // Create a list of products (assuming Product class is already defined)
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 1, "Product A", "Category A", "Large", 100.0, 120, 50, 10.0));
        products.add(new Product(2, 1, "Product B", "Category B", "Medium", 50.0, 60, 100, 5.0));

        // Create Vendor object using constructor
        Vendor vendor = new Vendor(1, "Vendor 1", "vendor1@example.com", products);

        // Verify the constructor initializes the vendor's properties correctly
        assertEquals(1, vendor.getVendorId());
        assertEquals("Vendor 1", vendor.getName());
        assertEquals("vendor1@example.com", vendor.getContactInfo());
        assertEquals(2, vendor.getProductList().size());  // Verify that the product list is correctly initialized
    }
    @Test
    public void testSetters() {
        // Create a Vendor object with initial values
        Vendor vendor = new Vendor(1, "Vendor 1", "vendor1@example.com", new ArrayList<>());

        // Update properties using setters
        vendor.setVendorId(2);
        vendor.setName("Vendor 2");
        vendor.setContactInfo("vendor2@example.com");

        // Verify that the setter methods updated the values correctly
        assertEquals(2, vendor.getVendorId());
        assertEquals("Vendor 2", vendor.getName());
        assertEquals("vendor2@example.com", vendor.getContactInfo());
    }
    @Test
    public void testNullProductList() {
        // Create Vendor with null productList
        Vendor vendor = new Vendor(1, "Vendor 1", "vendor1@example.com", null);

        // Verify that the product list is not null and is empty
        assertNotNull(vendor.getProductList());
        assertTrue(vendor.getProductList().isEmpty());
    }
}
