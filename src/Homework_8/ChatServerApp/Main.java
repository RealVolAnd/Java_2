package Homework_8.ChatServerApp;


import Homework_8.Tools.Console;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new MyChatServer(8888).startServer();
    }
}
