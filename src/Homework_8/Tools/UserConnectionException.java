package Homework_8.Tools;

public class UserConnectionException extends RuntimeException {
    public UserConnectionException(String msg) {
        super(msg);
    }
    public UserConnectionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
