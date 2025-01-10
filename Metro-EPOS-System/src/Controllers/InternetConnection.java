package Controllers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class InternetConnection {

    private static final String IP_ADDRESS = "8.8.8.8";
    private static final int PORT = 53;

    public static boolean isInternetConnected() {
        InternetConnection connection = new InternetConnection();
        return connection.checkConnection();
    }

    public boolean checkConnection() {
        try (Socket socket = new Socket()) {
            SocketAddress socketAddress = new InetSocketAddress(IP_ADDRESS, PORT);
            socket.connect(socketAddress, 3000);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
    public static boolean isInternetConnectedNot() {
        InternetConnection connection = new InternetConnection();
        return connection.failConnection();
    }
    public boolean failConnection() {
        try (Socket socket = new Socket()) {
            // Use a valid, but likely unused port number for failure testing
            SocketAddress socketAddress = new InetSocketAddress("123.123.123.123", 9999);  // Invalid IP or port
            socket.connect(socketAddress, 3000);  // Timeout set to 3 seconds
            return true;
        } catch (IOException unused) {
            return false;  // This will simulate a failed connection
        }
    }

}
