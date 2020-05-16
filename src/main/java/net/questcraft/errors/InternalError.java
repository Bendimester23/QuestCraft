package net.questcraft.errors;

public class InternalError extends Throwable {
    public InternalError(String message, int code) {
        this.message = message;
        this.errorCode = code;
    }
    String message;
    int errorCode;
    //SQLException: 1
    //AccountException: 2
    //Cant FInd uuid in list: 3
    //js Response is null: 4
    //incorrect password: 5
    //incorrect email Verification: 6
    //Email Failed to Send: 7
    //IOEXpetion(discord send failed): 8
    //Incorrect Server Pin: 9
    //InternalError(data not recognised): 13
    //Unfinished Feature: 14
    //NODE ERROR CODES:
    //10: couldnt find user in server
    //12 couldnt contact discord BOt: 12
    //incorrect verification code: 15
    //Unidentied Error: 11
    //no PRI keys: 16
    //not in testing mode: 17
    //No Annotation defining Key: 18
    public InternalError() {
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
