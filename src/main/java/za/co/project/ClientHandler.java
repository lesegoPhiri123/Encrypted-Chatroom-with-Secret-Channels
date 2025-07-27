package za.co.project;

import java.net.Socket;

//A connected user
import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static Vector<ClientHandler> clients = new Vector<>();

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        clients.add(this);
    }

    public void run() {
        try {
            String encryptedMsg;
            while ((encryptedMsg = in.readLine()) != null) {
                String msg = CryptoUtils.decrypt(encryptedMsg);
                System.out.println("Decrypted from client: " + msg);

                broadcast(CryptoUtils.encrypt("Client " + socket.getPort() + ": " + msg));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clients.remove(this);
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }

    private void broadcast(String encryptedMessage) {
        for (ClientHandler client : clients) {
            client.out.println(encryptedMessage);
        }
    }
}

