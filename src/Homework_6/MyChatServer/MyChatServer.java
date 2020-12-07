package Homework_6.MyChatServer;

import Homework_6.Console.Console;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyChatServer {

    int serverPort;
    protected ServerSocket serverSocket;
    private Socket clientSocket;
    private MyChatRxSocket rxSocket;
    private MyChatTxSocket txSocket;
    MyChatBuffer rxBuffer;
    MyChatBuffer txBuffer;
    Scanner scan = new Scanner(System.in);


    MyChatServer(int serverPort) {
        this.serverPort = serverPort;
        this.rxBuffer = new MyChatBuffer();
        this.txBuffer = new MyChatBuffer();

    }


    public void startServer() throws IOException {

        openServerSocket();
        Console.printServerInfo("Server waiting for connection on port: " + this.serverPort + ".....");
        clientSocket = this.serverSocket.accept();// Waiting for clients......

        Console.printServerInfo("Client connected from: "+clientSocket.getInetAddress().toString()+":"+clientSocket.getPort());
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

    private void openServerSocket() {
        Console.printServerInfo("Opening Server socket...");
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open Server port " + this.serverPort, e);
        }
    }
}
