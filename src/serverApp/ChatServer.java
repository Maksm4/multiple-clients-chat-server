package serverApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer implements Runnable {


    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private int port;

    public ChatServer(int port) {
        this.connections = new ArrayList<>();
        this.port = port;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            while (true) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                handler.start();
            }


        } catch (IOException e) {
            //TODO: handle
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showToAll(String sender, String message) {
        for (ConnectionHandler con : connections) {
            con.out.println(sender + ": " + message);
        }
    }

    class ConnectionHandler extends Thread {
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;

        private String nickname;

        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);


                String wholeMessage;
                nickname = in.readLine();

                while ((wholeMessage = in.readLine()) != null) {
                    showToAll(nickname, wholeMessage);
                }


            } catch (IOException e) {
                //TODO: handle
            } finally {
                try {
                    in.close();
                    out.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}