
package Controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class InternetConnectionTest {

    @Test
    void testIsInternetConnected_Success() {
        // Mock the InternetConnection instance to mock the non-static method checkConnection
        InternetConnection mockConnection = Mockito.mock(InternetConnection.class);

        // Simulate a successful connection by returning true from checkConnection
        Mockito.when(mockConnection.checkConnection()).thenReturn(true);

        // We now call the static method, which internally calls the instance method
        boolean isConnected = InternetConnection.isInternetConnected();

        // Assert that the static method returns true
        assertTrue(isConnected);  // Should return true as we're simulating a successful connection
    }

    @Test
    void testIsInternetConnected_Failure() {
        // Mock the InternetConnection instance to mock the non-static method failConnection
        InternetConnection mockConnection = Mockito.mock(InternetConnection.class);

        // Simulate a failed connection by returning false from failConnection
        Mockito.when(mockConnection.failConnection()).thenReturn(false);

        // We now call the static method, which internally calls the instance method
        boolean isConnected = InternetConnection.isInternetConnectedNot();

        // Assert that the static method returns false
        assertFalse(isConnected);  // Should return false as we're simulating a failure in connection
    }
}
