package net.questcraft.verifier;

import net.questcraft.errors.InternalError;
import net.questcraft.smtcreator.TableData;

import javax.mail.SendFailedException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class VerificationUtil {
    Verifier verifier;
    private static VerificationUtil instance;

    public void verifyEmail(TableData data) throws InternalError, SendFailedException, IOException, SQLException, InvocationTargetException, IllegalAccessException {
        verifier = new VerifyEmailImplementer();
        verifier.verifyAccount(data);
    }

    public void handleEmail(String code, TableData data) throws InternalError, SQLException, InvocationTargetException, IllegalAccessException {
        verifier = new VerifyEmailImplementer();

        verifier.handleResponse(code, data);

    }

    public void verifyMinecraft(TableData data) throws InternalError, SendFailedException, IOException, SQLException, InvocationTargetException, IllegalAccessException {
        verifier = new VerifyMinecraftImplementer();
        verifier.verifyAccount(data);
    }

    public void handleMinecraft(String code, TableData data) throws InternalError, IllegalAccessException, SQLException, InvocationTargetException {
        verifier = new VerifyMinecraftImplementer();

            verifier.handleResponse(code, data);

    }

    public void verifyDiscord(TableData data) throws InternalError, SendFailedException, IOException, SQLException, InvocationTargetException, IllegalAccessException {
        verifier = new VerifyDiscordImplementer();
        verifier.verifyAccount(data);
    }

    public void handleDiscord(String code, TableData data) throws InternalError, IllegalAccessException, SQLException, InvocationTargetException {
        verifier = new VerifyDiscordImplementer();

            verifier.handleResponse(code, data);

    }

    public static synchronized VerificationUtil getInstance() {
        if (instance == null) {
            instance = new VerificationUtil();
        }
        return instance;
    }

}
