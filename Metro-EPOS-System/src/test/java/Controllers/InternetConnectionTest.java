package Controllers;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class InternetConnectionTest {

    @Test
    void testInternetConnected() {
        boolean result = InternetConnection.isInternetConnected();
        assertTrue(result, "The system should report that the internet is connected when available.");
    }

    @Test
    void testNoInternetConnection() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(53)) {
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress("0.0.0.0", 53)); // Block the port

            boolean result = InternetConnection.isInternetConnected();
            assertFalse(result, "The system should report no internet connection when the DNS server is unreachable.");
        } catch (IOException ignored) {
            fail("Failed to set up the test environment for simulating no internet connection.");
        }
    }

    @Test
    void testInvalidIpAddress() {
        String invalidIpAddress = "999.999.999.999";
        int port = 53;
        boolean result;
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(invalidIpAddress, port), 3000);
            result = true;
        } catch (IOException e) {
            result = false;
        }
        assertFalse(result, "Should return false for invalid IP address.");
    }
}
