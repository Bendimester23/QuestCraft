package net.questcraft.verifier;

import net.questcraft.WebError;

import javax.mail.SendFailedException;
import java.sql.SQLException;

public class VerificationUtil {
    Verifier verifier;
    private static VerificationUtil instance;

    public void verifyEmail(VerificationData data) throws WebError, SendFailedException {
        verifier = new VerifyEmailImplementer();
        verifier.verifyAccount(data);
    }

    public void handleEmail(String code, VerificationData data) throws WebError, SQLException {
        verifier = new VerifyEmailImplementer();

        verifier.handleResponse(code, data);

    }

    public void verifyMinecraft(VerificationData data) throws WebError, SendFailedException {
        verifier = new VerifyEmailImplementer();
        verifier.verifyAccount(data);
    }

    public void handleMinecraft(String code, VerificationData data) throws WebError {
        verifier = new VerifyEmailImplementer();
        try {
            verifier.handleResponse(code, data);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void verifyDiscord(VerificationData data) throws WebError, SendFailedException {
        verifier = new VerifyEmailImplementer();
        verifier.verifyAccount(data);
    }

    public void handleDiscord(String code, VerificationData data) throws WebError {
        verifier = new VerifyEmailImplementer();
        try {
            verifier.handleResponse(code, data);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized VerificationUtil getInstance() {
        if (instance == null) {
            instance = new VerificationUtil();
        }
        return instance;
    }

}
