package Homework_6.MyChatServer;

import Homework_6.Console.Console;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MyChatTxSocket extends Thread {
    private String name;
    private DataOutputStream output;
    private Socket clSocket ;

    MyChatTxSocket(Socket clSocket, String name) {
        super(name);
        this.name = name;
        this.clSocket = clSocket;
    }

    @Override
    public void run() {

        try {
            output = new DataOutputStream(this.clSocket.getOutputStream());
            Console.printServerInfo("Server Tx socket " + name + " opened ");

            while (!isInterrupted()) {
                if (Main.server.txBuffer.hasMsg()) {
                    output.writeUTF(Main.server.txBuffer.getNextMsg());
                }
            }

        } catch (IOException e) {
            Console.printServerError("Server Tx IO error was occuired" + e);
        } catch (Exception e) {
            Console.printServerError("Server Tx thread error was occuired");
            e.printStackTrace();
        }
    }
}