import java.net.*;
import java.io.*;

public class MultiThreadedServer {
    public static void main(String[] args) {

        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                writer.println("Welcome to the chat server!");


                new ServerThread(socket, writer).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
