package Homework_7.MyChatServer;

import Homework_7.Console.Console;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

            new MyChatServer(8888).startServer();

        } catch (IOException e) {
            Console.printServerError("Server IO error was occuired" + e);
        }
    }
}
