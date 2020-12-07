package Homework_6.MyChatClient;

import Homework_6.Console.Console;

import java.io.IOException;

public class Main {

    public static MyChatClient client;

    public static void main(String[] args) {
        try {
            client = new MyChatClient("127.0.0.1", 8189);
            client.startClient();
        } catch (IOException e) {
            Console.printServerError("Client IO error was occuired" + e);
        }
    }
}
