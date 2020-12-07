package Homework_6.MyChatClient;

import Homework_6.Console.Console;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyChatClient {
    private Socket clientSocket;
    private String serverIP;
    private int serverPort;
    private MyChatRxSocket rxSocket;
    private MyChatTxSocket txSocket;
    MyChatBuffer rxBuffer;
    MyChatBuffer txBuffer;
    Scanner scan = new Scanner(System.in);

    MyChatClient(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.rxBuffer = new MyChatBuffer();
        this.txBuffer = new MyChatBuffer();
    }

    public void startClient() throws IOException {

        connectToServerSocket();

        Console.printServerInfo("Connection OK!");

        rxSocket = new MyChatRxSocket(clientSocket, "MyChat_RX_Socket");
        rxSocket.start();

        txSocket = new MyChatTxSocket(clientSocket, "MyChat_TX_Socket");
        txSocket.start();

        Runnable run1 = () -> {
            while (true) {
                txBuffer.putMsg(scan.nextLine());
            }
        };
        Thread thr1 = new Thread(run1);
        thr1.start();

        while (true) {
            if (rxBuffer.hasMsg()) {
                Console.printlnText(rxBuffer.getNextMsg());
            }
        }
    }

    private void connectToServerSocket() {
        Console.printServerInfo("Opening connection to Server: "+this.serverIP+":"+this.serverPort);
        try {
            clientSocket = new Socket(this.serverIP, this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open Client port " + this.serverPort, e);
        }
    }
}