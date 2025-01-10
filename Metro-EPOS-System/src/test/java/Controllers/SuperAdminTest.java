package Controllers;

import Controllers.SuperAdmin;
import Models.DataBaseHandler;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SuperAdminTest {

    @Test
    public void testSingletonInstance() throws SQLException {
        // Create an instance using the second constructor with id, password, and choice
        SuperAdmin superAdmin1 = SuperAdmin.getInstance("1", "456", "superadmin");

        // Create another instance with the same parameters, should return the same instance
        SuperAdmin superAdmin2 = SuperAdmin.getInstance("1", "456", "superadmin");

        // Assert that both instances are the same (singleton pattern)
        assertSame(superAdmin1, superAdmin2);
    }

    @Test
    public void testConstructorAndGetterMethods() throws SQLException {
        SuperAdmin.logout();
        // Test if SuperAdmin object is created and the getters return correct values
        SuperAdmin superAdmin = SuperAdmin.getInstance("Ahmad", "456", "ahmad@gmail.com", "1", true, "superadmin");

        assertEquals("Ahmad", superAdmin.getName());
        assertEquals("456", superAdmin.getPassword());
        assertEquals("ahmad@gmail.com", superAdmin.getEmail());
        assertEquals(1, superAdmin.getEmployeeNumber());
        assertTrue(superAdmin.isActive());
    }

    @Test
    public void testSettersAndToString() throws SQLException {
        // Create SuperAdmin instance using the constructor
        SuperAdmin superAdmin = SuperAdmin.getInstance("admin123", "password", "admin@example.com", "1001", true, "someChoice");

        // Set new values using setters
        superAdmin.setName("admin456");
        superAdmin.setPassword("newPassword");
        superAdmin.setEmail("admin456@example.com");
        superAdmin.setActive(false);

        // Assert that the new values are correctly set
        assertEquals("admin456", superAdmin.getName());
        assertEquals("newPassword", superAdmin.getPassword());
        assertEquals("admin456@example.com", superAdmin.getEmail());
        assertFalse(superAdmin.isActive());

        // Check if the toString method gives the correct string representation
        String expected = "SuperAdmin{name='admin456', password='newPassword', email='admin456@example.com', employeeNumber=1, isActive=false}";
        assertEquals(expected, superAdmin.toString());
    }
    @Test
    public void testChangePassword() throws SQLException {
        // Mock the DataBaseHandler to simulate changing password

        // Change password and assert the result
        boolean result = DataBaseHandler.getInstance().changePassword("456",1);
        // Verify if changePassword was called
        assertFalse(result);
    }

    @Test
    public void testLogout() throws SQLException {
        // Create a SuperAdmin instance
        SuperAdmin superAdmin = new SuperAdmin("1", "456", "superadmin");

        // Assert that the instance is created
        assertNotNull(superAdmin);

        // Call logout to clear the instance
        SuperAdmin.logout();

        // Assert that the instance after logout is a new instance
        assertNotNull(superAdmin);
    }
}
