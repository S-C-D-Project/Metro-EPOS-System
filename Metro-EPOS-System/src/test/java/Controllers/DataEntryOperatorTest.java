package Controllers;

import Controllers.DataEntryOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataEntryOperatorTest {

    private DataEntryOperator dataEntryOperator;

    @BeforeEach
    void setUp() {
        // Create a DataEntryOperator instance with the constructor
        dataEntryOperator = new DataEntryOperator("John Doe", "password123", "john@example.com",
                1, 1, 50000, "2024-01-01", "2024-12-31", true, false, "DataEntryOperator");
    }

    @Test
    void testConstructorWithAllFields() {
        // Test if the DataEntryOperator is correctly initialized
        assertNotNull(dataEntryOperator);
        assertEquals("John Doe", dataEntryOperator.getName());
        assertEquals(null, dataEntryOperator.getPassword());
        assertEquals("john@example.com", dataEntryOperator.getEmail());

        assertEquals(50000, dataEntryOperator.getSalary());
        assertEquals("2024-01-01", dataEntryOperator.getJoiningDate());
        assertEquals("2024-12-31", dataEntryOperator.getLeavingDate());
        assertTrue(dataEntryOperator.isActive());
        assertEquals("DataEntryOperator", dataEntryOperator.getRole());
    }

    @Test
    void testConstructorWithOptionalPhoneNumber() {
        // Create an instance with the constructor that includes a phone number
        DataEntryOperator operatorWithPhone = new DataEntryOperator("Jane Doe", "password456",
                "jane@example.com", 2, 1, 45000, true, "DataEntryOperator", "123-456-7890");

        // Test if the DataEntryOperator with phone number is correctly initialized
        assertNotNull(operatorWithPhone);
        assertEquals("Jane Doe", operatorWithPhone.getName());
        assertEquals("123-456-7890", operatorWithPhone.getPhoneNumber());
    }

    @Test
    void testDefaultConstructor() {
        // Create a DataEntryOperator instance using the default constructor
        DataEntryOperator defaultOperator = new DataEntryOperator();

        assertNotNull(defaultOperator);

        assertNull(defaultOperator.getName());  // Assumes the parent class sets default values like null for name
    }
}
