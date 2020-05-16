package net.questcraft;

import net.questcraft.account.Account;
import net.questcraft.account.AccountUtil;
import net.questcraft.errors.InternalError;
import net.questcraft.joinapp.Application;
import net.questcraft.joinapp.ApplicationUtil;
import net.questcraft.verifier.VerificationUtil;
import org.junit.jupiter.api.Test;

import javax.mail.SendFailedException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class VerificationTests {
    //All Verification working for 1.4 QuestCraft!!! :)
//    @Test
//    public void testEmailVerify() throws SendFailedException, InternalError, IOException, SQLException, InvocationTargetException, IllegalAccessException {
//        VerificationUtil util = VerificationUtil.getInstance();
//        MySQLUtil mySQLUtil = MySQLUtil.getInstance();
//        AccountUtil accountUtil = AccountUtil.getInstance();
//
//        Account account = accountUtil.getAccount("asdf");
//        Application application = mySQLUtil.getApp("2");
//        util.verifyEmail(account);
//    }

    @Test
    public void testDiscordVerify() throws SendFailedException, InternalError, IOException, SQLException, InvocationTargetException, IllegalAccessException {
        VerificationUtil util = VerificationUtil.getInstance();
        ApplicationUtil applicationUtil = ApplicationUtil.getInstance();
        AccountUtil accountUtil = AccountUtil.getInstance();
        Application application = applicationUtil.getApplication("2");
        Account account = accountUtil.getAccount("asdf");
        util.verifyDiscord(account);
    }
    @Test
    public void testMCVerify() throws IllegalAccessException, IOException, SQLException, SendFailedException, InternalError, InvocationTargetException {

        VerificationUtil util = VerificationUtil.getInstance();
        ApplicationUtil applicationUtil = ApplicationUtil.getInstance();
        AccountUtil accountUtil = AccountUtil.getInstance();
        //Account account = accountUtil.getAccount("asdf");
        Application application = applicationUtil.getApplication("7");
        util.verifyMinecraft(application);
    }
}
