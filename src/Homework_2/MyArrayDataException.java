package Homework_2;

public class MyArrayDataException  extends MyArrayException{


    public MyArrayDataException(String s) {
        super(s);
    }

    public MyArrayDataException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
