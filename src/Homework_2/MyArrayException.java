package Homework_2;

public abstract class MyArrayException extends Exception{


    public MyArrayException(String s) {
        super(s);
    }

    public MyArrayException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
