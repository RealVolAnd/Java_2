package Homework_7.MyChatClient1;

import Homework_7.Console.Console;

import java.io.IOException;

public class Main {

    public static Homework_7.MyChatClient1.MyChatClient client;

    public static void main(String[] args) {


        try {
            client = new Homework_7.MyChatClient1.MyChatClient("127.0.0.1", 8888);
            client.startClient();
        } catch (IOException e) {
            Console.printServerError("Client IO error was occuired" + e);
        }
    }
}
