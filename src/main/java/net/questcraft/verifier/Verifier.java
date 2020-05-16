package net.questcraft.verifier;

import net.questcraft.errors.InternalError;
import net.questcraft.smtcreator.TableData;

import javax.mail.SendFailedException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface Verifier {
    void verifyAccount(TableData data) throws SendFailedException, InternalError, IOException, SQLException, InvocationTargetException, IllegalAccessException;
    void handleResponse(String code, TableData data) throws InternalError, SQLException, InvocationTargetException, IllegalAccessException;

}
