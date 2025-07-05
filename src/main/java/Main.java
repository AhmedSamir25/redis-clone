import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int port = 6379;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            Socket clientSocket = serverSocket.accept();
            while (true) { // Loop to accept multiple connections

                byte[] input = new byte[1024];
                clientSocket.getInputStream().read(input);
                String inputString = new String(input).trim();
                System.out.println("Received: " + inputString);
                clientSocket.getOutputStream().write("+PONG\r\n".getBytes());
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }
}