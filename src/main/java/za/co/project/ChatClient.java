package za.co.project;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("🔗 Connected to server");

        new Thread(new Reader(socket)).start();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = userInput.readLine()) != null) {
            out.println(input);
        }
    }

    private static class Reader implements Runnable {
        private Socket socket;

        public Reader(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("📩 " + message);
                }
            } catch (IOException e) {
                System.out.println("❌ Reader error: " + e.getMessage());
            }
        }
    }
}

