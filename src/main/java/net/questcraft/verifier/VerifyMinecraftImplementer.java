package net.questcraft.verifier;

import net.questcraft.ConfigReader;
import net.questcraft.account.AccountUtil;
import net.questcraft.joinapp.ApplicationUtil;
import net.questcraft.errors.InternalError;
import net.questcraft.account.Account;
import net.questcraft.joinapp.Application;
import net.questcraft.smtcreator.TableData;

import javax.mail.SendFailedException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.UUID;

public class VerifyMinecraftImplementer implements Verifier {
    ConfigReader configReader;
    ApplicationUtil applicationUtil;
    AccountUtil accountUtil;
    public VerifyMinecraftImplementer() {
        configReader = new ConfigReader();
        applicationUtil = ApplicationUtil.getInstance();
        accountUtil = AccountUtil.getInstance();
    }
    @Override
    public void verifyAccount(TableData data) throws SendFailedException, InternalError, IOException, SQLException, InvocationTargetException, IllegalAccessException {
        if (data instanceof Account) {
            String playerCode = UUID.randomUUID().toString();
            Account account = (Account) data;
            account.setMcVerifyCode(playerCode);
            accountUtil.updateAccount(account, account.getUsername());
        } else if(data instanceof Application) {
            String playerCode = UUID.randomUUID().toString();
            Application application = (Application) data;
            application.setMinecraftVerifyCode(playerCode);
            applicationUtil.updateApplication(application, application.getId().toString());
        } else {
            throw new InternalError("Verification Data not of a recognized sort", 13);
        }
    }

    @Override
    public void handleResponse(String code, TableData data) throws InternalError, SQLException, InvocationTargetException, IllegalAccessException {
        if (data instanceof Account) {
            Account account = (Account) data;
            if (code.equals(account.getMcVerifyCode())) {
                account.setMcUser(account.getPendingMCUser());
                account.setPendingMCUser("NULL");
                account.setMcVerifyCode("NULL");
                accountUtil.updateAccount(account, account.getUsername());
            }
        } else if(data instanceof Application) {
            Application application = (Application) data;
            if (code.equals(application.getMinecraftVerifyCode())) {
                application.setMcUsername(application.getPendingMCUser());
                application.setPendingMCUser("NULL");
                application.setMinecraftVerifyCode("NULL");
                applicationUtil.updateApplication(application, application.getId().toString());
            } else {
                throw new InternalError("Incorrect Verification Code", 15);
            }
        } else {
            throw new InternalError("Verification Data not of a recognized sort", 13);
        }
    }
}
