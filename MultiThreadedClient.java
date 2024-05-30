import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.*;
import java.io.*;

public class MultiThreadedClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(hostname, port)) {
            while (true) {
                boolean flag = false;
                System.out.println("Enter Your userName : ");
                String userName = System.console().readLine().toString();
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line;
                line = reader.readLine();
                System.out.println(line);
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                while (true) {
                    System.out.println("Enter a message to send : ");
                    String message = System.console().readLine().toString();
                    writer.println(userName + " : " + message);
                    if (message.equals("bye")) {
                        System.out.println("You Left the Chatroom!");
                        flag = true;
                        break;
                    }
                }
                if(flag==true) {
                    break;
                }

            }

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
