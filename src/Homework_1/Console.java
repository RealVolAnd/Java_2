package Homework_1;

/*
Вывод на экран
 */
public class Console {
    public static final String BOLD = "\033[1m";
    public static final String RESET = "\u001B[0m";


    public static void printHeader(String header) {
        System.out.println("\n" + BOLD + header + RESET);
    }

    public static void printText(String text) {
        System.out.println(text);
    }
}
