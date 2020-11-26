package Homework_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("circle");
        words.add("circle");
        words.add("cube");
        words.add("triangle");
        words.add("rectangle");
        words.add("cube");
        words.add("triangle");
        words.add("cube");
        words.add("square");
        words.add("rectangle");
        words.add("triangle");
        words.add("square");
        words.add("cube");
        words.add("cube");
        words.add("square");

        testWords(words);

    }

    private static void testWords(ArrayList<String> words) {
        HashMap<String, Integer> wordsList = new HashMap<>();

        for (String s : words) {
            if (wordsList.get(s) == null) {
                wordsList.put(s, 1);
            } else {
                int value = wordsList.get(s);
                wordsList.put(s, value + 1);
            }
        }


        for (Map.Entry entry : wordsList.entrySet()) {
            System.out.println(entry.getKey() + " = "
                    + entry.getValue());
        }
    }
}
