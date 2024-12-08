package Controllers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public  class InternetConnection {

    public static boolean isInternetConnected() {
        String ipAddress = "8.8.8.8";
        int port = 53;

        try (Socket socket = new Socket()) {
            SocketAddress socketAddress = new InetSocketAddress(ipAddress, port);
            socket.connect(socketAddress, 3000);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static void main(String[] args) {
        if (isInternetConnected()) {
            System.out.println("Internet is available.");
        } else {
            System.out.println("No internet connection.");
        }
    }

}
