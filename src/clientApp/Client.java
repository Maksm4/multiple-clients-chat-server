package clientApp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private MainFrame mainFrame;


    public Client(String serverAddress, int port) {
        try {
            this.socket = new Socket(serverAddress, port);
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new PrintWriter(this.socket.getOutputStream(), true);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMessage(String message){
        writer.println(message);
    }


    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {

                if (reader.ready()) {
                    String receivedMessage = reader.readLine();
                    mainFrame.showMessage(receivedMessage);
                }


                if (System.in.available() > 0) {
                    String messageToSend = scanner.nextLine();
                    if (messageToSend != null && !messageToSend.isEmpty()) {
                        sendMessage(messageToSend);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
