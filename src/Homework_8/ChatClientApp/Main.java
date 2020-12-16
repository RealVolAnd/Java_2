package Homework_8.ChatClientApp;

import Homework_8.ChatClientApp.ChatController.ChatClientController;

public class Main {
    public static void main(String[] args) {
        new ChatClientController("127.0.0.1",8888);
    }
}
