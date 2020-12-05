package Homework_6.MyChatServer;

import Homework_6.Console.Console;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

class MyChatRxSocket extends Thread {
    private Socket clSocket;
    private DataInputStream input;
    private String name;


    MyChatRxSocket(Socket cl_Socket, String name) {
        super(name);
        this.name = name;
        this.clSocket = cl_Socket;
    }

    @Override
    public void run() {

        try {
            input = new DataInputStream(this.clSocket.getInputStream());

            Console.printServerInfo("Server Rx socket " + name + " opened");

            while (!isInterrupted()) {
                while (input.available() > 0) {
                    Main.server.rxBuffer.putMsg("Client said >> " + input.readUTF());
                }
            }
        } catch (IOException e) {
            Console.printServerError("Server Rx IO error was occuired" + e);
        } catch (Exception e) {
            Console.printServerError("Server Rx thread error was occuired");
            e.printStackTrace();
        }
    }
}
