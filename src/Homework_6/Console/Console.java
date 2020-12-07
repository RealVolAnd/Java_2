package Homework_6.Console;

public  class Console {
    private static final String BOLD = "\033[1m";
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    synchronized public static void printHeader(String header) {
        System.out.println("\n" + BOLD + header + RESET);    }

    synchronized public static void printText(String text) {
        System.out.print(text);    }

    synchronized public static void printlnText(String text) {
        System.out.println(text);    }

    synchronized public static void printServerError(String text) {
        System.out.println(BOLD+RED+text+RESET);
    }
    synchronized public static void printServerInfo(String text) {
        System.out.println(BOLD+GREEN+text+RESET);
    }
}