package clientApp;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {



    private JPanel displayMessages;
    private JPanel writeMessages;
    private JTextArea text;
    private JTextArea  textLog;
    private Client client;

    public MainFrame(Client client) {
        createMainFrame();
        createPanels();
        createWritableArea();
        CreateDisplayMessages();
        this.client =  client;
        this.setVisible(true);
        this.pack();
    }

    public void createMainFrame() {
//        this.setIconImage();
        this.setPreferredSize(new Dimension(600, 600));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void createPanels() {
        this.displayMessages = new JPanel();
        this.writeMessages = new JPanel();
        this.displayMessages.setPreferredSize(new Dimension(600, 450));
        this.displayMessages.setVisible(true);
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        borderLayout.setVgap(10);
        this.displayMessages.setLayout(new BorderLayout());
        this.writeMessages.setLayout(borderLayout);
        this.writeMessages.setPreferredSize(new Dimension(600, 100));
        this.add(writeMessages, BorderLayout.SOUTH);
        this.add(displayMessages, BorderLayout.NORTH);
    }

    public void createWritableArea() {
        this.text = new JTextArea("enter your nickname");
        this.text.setPreferredSize(new Dimension(500, 50));
        this.text.setLineWrap(true);
        this.text.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(this.text);
        this.writeMessages.add(scroll, BorderLayout.NORTH);
        JButton send = new JButton("SEND");
        send.addActionListener(e -> {
            String message = this.text.getText();
            if (!message.isEmpty()) {
                this.client.sendMessage(message);
                this.text.setText("");
            }
        });
        send.setPreferredSize(new Dimension(80, 30));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(send);
        writeMessages.add(buttonPanel, BorderLayout.SOUTH);

    }

    public void CreateDisplayMessages() {
            textLog = new JTextArea();
            textLog.setEditable(false);
            textLog.setLineWrap(true);
            textLog.setPreferredSize(new Dimension(550, 400));
            JScrollPane scroll = new JScrollPane(textLog);
            displayMessages.add(scroll);
    }

    public void showMessage(String message){
        this.textLog.append(message + " \n");
    }


}
