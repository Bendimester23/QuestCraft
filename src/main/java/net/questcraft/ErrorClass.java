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
    //Cant FInd uuid in list: 3

    public ErrorClass() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
