import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    private Socket socket;
    private PrintWriter writer;

    public ServerThread(Socket socket,PrintWriter writer) {
        this.socket = socket;
        this.writer = writer;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String text;

            do {
                text = reader.readLine();
                System.out.println(text);

            } while (!text.endsWith("bye"));
            int temp = text.indexOf(":");
            temp = temp - 1;
            String user = text.substring(0, temp);
            System.out.println(user + " " + "left the chat!!");

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}