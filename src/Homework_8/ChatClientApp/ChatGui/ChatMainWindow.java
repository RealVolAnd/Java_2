package Homework_8.ChatClientApp.ChatGui;

import Homework_8.ChatClientApp.PutGetMsg;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ChatMainWindow extends JFrame implements PutGetMsg {
    private final String title="MyChat 2.0.0 - ";
    JTextArea sendArea;
    JButton sendButton;
    JTextArea chatArea;
    JMenuItem ConnectItem;
    JMenuItem DisconnectItem;
    ArrayList<String> sendBuffer;

    public ChatMainWindow() {
        initConsole();
    }

    private void initConsole() {

        sendBuffer=new ArrayList<>();

        setTitle(title + "( No connection to Chat Server )");
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
        ConnectItem = new JMenuItem("Connect");
        ConnectItem.setFont(font);
        ConnectItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearChat();
                clearSend();
                putMsgInTheSendBuffer("--tryconnect-");
            }
        });
        fileMenu.add(ConnectItem);

        DisconnectItem = new JMenuItem("Disonnect");
        DisconnectItem.setEnabled(false);
        DisconnectItem .setFont(font);
        DisconnectItem .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearChat();
                clearSend();
                putMsgInTheSendBuffer("-exit");
            }
        });
        fileMenu.add(DisconnectItem);

       fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                putMsgInTheSendBuffer("-exit");
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

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


        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setFont(font);

        JMenuItem listItem = new JMenuItem("List active users");
        listItem.setFont(font);
        listItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                putMsgInTheSendBuffer("-list");
            }
        });
        toolsMenu.add(listItem);

        JMenuItem timeItem = new JMenuItem("Get Server DateTime");
        timeItem.setFont(font);
        timeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                putMsgInTheSendBuffer("-time");
            }
        });
        toolsMenu.add(timeItem);

        JMenuItem whoamiItem = new JMenuItem("Whoami?");
        whoamiItem.setFont(font);
        whoamiItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                putMsgInTheSendBuffer("-name");
            }
        });
        toolsMenu.add(whoamiItem);


        JMenu helpMenu = new JMenu("Help");
        helpMenu.setFont(font);

        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.setFont(font);
        helpItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                putMsgInTheSendBuffer("-help");
            }
        });
        helpMenu.add(helpItem);


        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setFont(font);
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ChatAboutDialogWindow(ChatMainWindow.this);
            }
        });
        helpMenu.add(aboutItem);




        menuBar.add(fileMenu);
        menuBar.add(chatMenu);
        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);



//-------------------------------------------- Chat Text Area   --------------------------------------------

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new FlowLayout());

        Font chatFont = new Font("Verdana", Font.PLAIN, 18);
        chatArea = new JTextArea(24,46);
        chatArea.setText("");
        chatArea.setFont(chatFont);
        chatArea.setEditable(false);
        chatArea.setCaretPosition(0);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        DefaultCaret caret = (DefaultCaret)chatArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

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
        sendArea.setEnabled(false);


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
                putMsgInTheSendBuffer(sendArea.getText());
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
                String tempstr="";
                if(sendArea.getText().length()>0){
                    sendButton.setBackground(Color.GREEN);
                    sendButton.setEnabled(true);
                } else {
                    sendButton.setBackground(Color.RED);
                    sendButton.setEnabled(false);
                }
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    tempstr=sendArea.getText();
                    tempstr=tempstr.substring(0,tempstr.length()-1);
                    chatArea.append("Me >> "+tempstr+"\n");
                    putMsgInTheSendBuffer(tempstr);
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

    private void putMsgInTheSendBuffer(String msg){
        sendBuffer.add(msg);
    }

    @Override
    public void putMsg(String message) {
        if(message.startsWith("--serverconnectionerror-")){
            setTitle(title + "( Error: Can't connect to the Chat Server. Please restart the App )");
            sendArea.setEnabled(false);
            ConnectItem.setEnabled(true);
            DisconnectItem.setEnabled(false);
        } else if(message.startsWith("--serverconnectionok-")){
            setTitle(title + "( Connented to Chat Server )");
            sendArea.setEnabled(true);
            ConnectItem.setEnabled(false);
            DisconnectItem.setEnabled(true);
        } else if(message.startsWith("--exit-")){
            setTitle(title + "( Disconnected )");
            sendArea.setEnabled(false);
            ConnectItem.setEnabled(true);
            DisconnectItem.setEnabled(false);
        } else{
            chatArea.append(message);
        }
    }

    @Override
    synchronized public String getNextMsg() {
        if(sendBuffer.size()>0) {
            String str=sendBuffer.get(0);
            sendBuffer.remove(0);
            return str;
        }
        return null;
    }

}
