package Homework_2;

public class MyArraySizeException extends MyArrayException{

    public MyArraySizeException(String s) {
        super(s);
    }

    public MyArraySizeException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
