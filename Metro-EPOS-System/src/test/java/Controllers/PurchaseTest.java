package Controllers;

import Controllers.Purchase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PurchaseTest {

    @Test
    public void testConstructorAndGetters() {
        // Initialize test data
        int purchaseID = 1;
        String purchaseDate = "2024-12-09";
        int purchaseAmount = 500;
        Date date = new Date();
        int vendorId = 101;

        // Create Purchase object
        Purchase purchase = new Purchase(purchaseID, purchaseDate, purchaseAmount, date, vendorId);

        // Verify the constructor initializes the fields correctly
        assertEquals(purchaseID, purchase.getPurchaseID());
        assertEquals(purchaseDate, purchase.getPurchaseDate());
        assertEquals(purchaseAmount, purchase.getPurchaseAmount());
        assertEquals(date, purchase.getDate());
        assertEquals(vendorId, purchase.getVendorId());
    }
    @Test
    public void testSettersAndGetters() {
        // Create a Purchase object with initial values
        Purchase purchase = new Purchase(1, "2024-12-09", 500, new Date(), 101);

        // Update the fields using setter methods
        purchase.setPurchaseID(2);
        purchase.setPurchaseDate("2024-12-10");
        purchase.setPurchaseAmount(600);
        purchase.setDate(new Date(2024, 12, 10));  // Change the date to a specific value
        purchase.setVendorId(102);

        // Verify that the setter methods update the fields correctly
        assertEquals(2, purchase.getPurchaseID());
        assertEquals("2024-12-10", purchase.getPurchaseDate());
        assertEquals(600, purchase.getPurchaseAmount());
        assertEquals(new Date(2024, 12, 10), purchase.getDate());
        assertEquals(102, purchase.getVendorId());
    }
    @Test
    public void testVendorId() {
        // Create a Purchase object
        Purchase purchase = new Purchase(1, "2024-12-09", 500, new Date(), 101);

        // Verify that the vendor ID is correctly set
        assertEquals(101, purchase.getVendorId());

        // Change the vendor ID using setter
        purchase.setVendorId(202);

        // Verify that the vendor ID is updated
        assertEquals(202, purchase.getVendorId());
    }
    @Test
    public void testDateField() {
        // Create a Purchase object with a specific date
        Date initialDate = new Date(2024, 12, 9);
        Purchase purchase = new Purchase(1, "2024-12-09", 500, initialDate, 101);

        // Verify the initial date
        assertEquals(initialDate, purchase.getDate());

        // Update the date using setter method
        Date newDate = new Date(2024, 12, 10);
        purchase.setDate(newDate);

        // Verify the date is updated
        assertEquals(newDate, purchase.getDate());
    }

    @Test
    public void testPurchaseAmount() {
        // Create a Purchase object
        Purchase purchase = new Purchase(1, "2024-12-09", 500, new Date(), 101);

        // Verify the initial purchase amount
        assertEquals(500, purchase.getPurchaseAmount());

        // Update the purchase amount using setter method
        purchase.setPurchaseAmount(700);

        // Verify the updated purchase amount
        assertEquals(700, purchase.getPurchaseAmount());
    }
    @Test
    public void testPurchaseDateFormat() {
        // Create a Purchase object
        Purchase purchase = new Purchase(1, "2024-12-09", 500, new Date(), 101);

        // Verify the initial purchase date
        assertEquals("2024-12-09", purchase.getPurchaseDate());

        // Update the purchase date using setter method
        purchase.setPurchaseDate("2024-12-10");

        // Verify the updated purchase date
        assertEquals("2024-12-10", purchase.getPurchaseDate());
    }
}
