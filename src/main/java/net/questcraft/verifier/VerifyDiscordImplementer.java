package net.questcraft.verifier;

import net.questcraft.WebError;

import javax.mail.SendFailedException;
import java.sql.SQLException;

public class VerifyDiscordImplementer implements Verifier {

    @Override
    public void verifyAccount(VerificationData data) throws SendFailedException, WebError {

    }

    @Override
    public void handleResponse(String code, VerificationData data) throws WebError, SQLException {

    }
}
