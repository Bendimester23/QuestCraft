package net.questcraft.verifier;

import net.questcraft.WebError;

import javax.mail.SendFailedException;
import java.sql.SQLException;

public interface Verifier {
    void verifyAccount(VerificationData data) throws SendFailedException, WebError;
    void handleResponse(String code, VerificationData data) throws WebError, SQLException;

}
