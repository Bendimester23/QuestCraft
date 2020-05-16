package net.questcraft.account;

import net.questcraft.ConfigReader;
import net.questcraft.errors.InternalError;
import net.questcraft.verifier.VerificationUtil;

import javax.mail.SendFailedException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

public class AccountUtil {
    static AccountUtil instance;
    ConfigReader configReader;
    AccountDAO accountDAO;
    VerificationUtil verificationUtil;

    public AccountUtil() {
        verificationUtil = VerificationUtil.getInstance();
        configReader = new ConfigReader();
        accountDAO = configReader.getTesting() ? new AccountDaoTestImplementer() : new AccountDAOImplementer();

    }


    public static synchronized AccountUtil getInstance() {
        if (instance == null) {
            instance = new AccountUtil();
        }
        return instance;
    }

    public void createAccount(Account account) throws SQLException, InvocationTargetException, IllegalAccessException, SendFailedException, IOException, InternalError {
        System.out.println("creating account in UTIL");
        accountDAO.createAccount(account);
        if (account.getPendingEmail() != null) {
            verificationUtil.verifyEmail(account);
        }
        if (account.getPendingMCUser() != null) {
            verificationUtil.verifyMinecraft(account);
        }
    }

    public void deleteAccount(String user) throws SQLException, InvocationTargetException, IllegalAccessException {
        accountDAO.deleteAccount(user);
    }
    public void updateAccount(Account account, String user) throws SQLException, InvocationTargetException, IllegalAccessException, InternalError {
        accountDAO.updateAccount(account, user);
    }
    public Account getAccount(String user) throws SQLException, InvocationTargetException, IllegalAccessException {
        System.out.println("rtying to get account be user Username: " + user);
        return accountDAO.getAccount(user);
    }

    public boolean verifyAccount(String user, String password) throws SQLException, InvocationTargetException, IllegalAccessException {
        System.out.println("verifying account. The URL is: " + configReader.readPropertiesFile("url"));
        Account SQLAccount = accountDAO.getAccount(user);
        return SQLAccount.getPassword().equals(password);
    }

    public String hashPassword(String pw) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pw.getBytes());
        byte[] digest = md.digest();
        return Base64.getEncoder().encodeToString(digest);
    }
}
