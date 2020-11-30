package Homework_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame {
    JTextArea sendArea;
    JButton sendButton;
    JTextArea chatArea;

    public MainWindow(){
        setTitle("MyChat 1.0.0 Beta");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 800, 800);
        setResizable(false);
        Font font = new Font("Verdana", Font.PLAIN, 18);



//--------------------------------------------  MENU   --------------------------------------------

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);

        /*
        other menu items here
        */

        JMenuItem AboutItem = new JMenuItem("About");
        AboutItem.setFont(font);
        AboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AboutDialogWindow(MainWindow.this);
            }
        });
        fileMenu.add(AboutItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu chatMenu = new JMenu("Chat");
        chatMenu.setFont(font);

        JMenuItem ClearItem = new JMenuItem("Clear");
        ClearItem.setFont(font);
        ClearItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearChat();
            }
        });
        chatMenu.add(ClearItem);

        menuBar.add(fileMenu);
        menuBar.add(chatMenu);
        setJMenuBar(menuBar);



//-------------------------------------------- Chat Text Area   --------------------------------------------

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());

        Font chatFont = new Font("Verdana", Font.PLAIN, 18);
        chatArea = new JTextArea(10, 20);
        chatArea.setText("");
        chatArea.setFont(chatFont);
        chatArea.setEditable(false);
        chatArea.setCaretPosition(0);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPanel = new JScrollPane(chatArea);
        chatPanel.add(scrollPanel, BorderLayout.CENTER);
        add(chatPanel,BorderLayout.CENTER);

//-------------------------------------------- Send Message Text Area   --------------------------------------------

            JPanel sendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JPanel sendTextPanel = new JPanel();

            sendTextPanel.setLayout(new BorderLayout());
            sendTextPanel.setPreferredSize(new Dimension(getWidth()-150, 80));

            Font sendFont = new Font("Verdana", Font.PLAIN, 22);

            sendArea = new JTextArea();
            sendArea.setFont(sendFont);
            sendArea.setLineWrap(true);
            sendArea.setWrapStyleWord(true);
            sendArea.setToolTipText("Type you message here");


        JScrollPane sendTextScrollPanel = new JScrollPane(sendArea);
        sendTextPanel.add(sendTextScrollPanel, BorderLayout.CENTER);
        sendPanel.add(sendTextPanel);

//-------------------------------------------- Send Message Button  --------------------------------------------

                sendButton = new JButton("Send");
                sendButton.setFont(sendFont);
                sendButton.setPreferredSize(new Dimension(130, 80));
                sendButton.setBackground(Color.RED);
                sendButton.setForeground(Color.WHITE);
                sendButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        chatArea.append("Me >> "+sendArea.getText()+"\n");
                        clearSend();
                    }
                });
                sendPanel.add(sendButton);
            sendArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(sendArea.getText().length()>0){
                    sendButton.setBackground(Color.GREEN);
                    sendButton.setEnabled(true);
                } else {
                    sendButton.setBackground(Color.RED);
                    sendButton.setEnabled(false);
                }
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    chatArea.append("Me >> "+sendArea.getText()+"\n");
                    clearSend();
                }
            }
        });

        add(sendPanel, BorderLayout.SOUTH);


        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void clearChat(){
        chatArea.setText("");
        sendArea.setText(null);
        sendButton.setBackground(Color.RED);
        sendButton.setEnabled(false);
    }
    private void clearSend(){
        sendArea.setText(null);
        sendButton.setBackground(Color.RED);
        sendButton.setEnabled(false);
    }
}
