package Homework_6.MyChatClient;

import Homework_6.Console.Console;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MyChatTxSocket extends Thread {
    private String name;
    private DataOutputStream output;
    private Socket clSocket;

    MyChatTxSocket(Socket clSocket, String name) {
        super(name);
        this.name = name;
        this.clSocket = clSocket;
    }

    @Override
    public void run() {

        try {
            output = new DataOutputStream(this.clSocket.getOutputStream());
            Console.printServerInfo("Client Tx socket " + name + " opened ");

            while (!isInterrupted()) {
                if (Main.client.txBuffer.hasMsg()) {
                    output.writeUTF(Main.client.txBuffer.getNextMsg());
                }
            }

        } catch (IOException e) {
            Console.printServerError("Client Tx IO error was occuired" + e);
        } catch (Exception e) {
            Console.printServerError("Client Tx thread error was occuired");
            e.printStackTrace();
        }
    }

}
