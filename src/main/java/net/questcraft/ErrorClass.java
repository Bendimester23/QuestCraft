package net.questcraft;

public class ErrorClass {
    public ErrorClass(String message, int code) {
        this.message = message;
        this.errorCode = code;
    }
    String message;
    int errorCode;
    //SQLException: 1
    //AccountException: 2
}
