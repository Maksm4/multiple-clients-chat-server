package clientApp;

import javax.swing.*;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client("localhost", 9999);
        Thread clientThread = new Thread(client);
        clientThread.start();
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(client);
            client.setMainFrame(mainFrame);
        });
    }
}
