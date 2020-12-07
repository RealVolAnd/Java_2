package Homework_6.MyChatServer;

import Homework_6.Console.Console;

import java.io.IOException;

public class Main {
    public static MyChatServer server;

    public static void main(String[] args) {
        try {

            server = new MyChatServer(8189);
            server.startServer();
        } catch (IOException e) {
            Console.printServerError("Server IO error was occuired" + e);
        }
    }
}
