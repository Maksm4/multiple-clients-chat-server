package serverApp;


import javax.swing.*;

public class MainServer {

    public static void main(String[] args) {
        ChatServer server = new ChatServer(9999);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }
}
