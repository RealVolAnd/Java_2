package Homework_7.MyChatClient2;

import Homework_7.Console.Console;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MyChatRxSocket extends Thread {
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

            Console.printServerInfo("Client Rx socket " + name + " opened");

            while (!isInterrupted()) {
                if (input.available() > 0) {
                    Homework_7.MyChatClient2.Main.client.rxBuffer.putMsg("Server said >> " + input.readUTF());
                }
            }
        } catch (IOException e) {
            Console.printServerError("Client Rx IO error was occuired" + e);
        } catch (Exception e) {
            Console.printServerError("Client Rx thread error was occuired");
            e.printStackTrace();
        }
    }


}
