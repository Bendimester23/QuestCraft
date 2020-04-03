package net.questcraft.account;

import net.questcraft.ConfigReader;

import javax.mail.SendFailedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

public class AccountUtil {
    static AccountUtil instance;
    ConfigReader configReader = new ConfigReader();
    AccountDAO accountDAO = configReader.getTesting() ? new AccountDaoTestImplementer() : new AccountDAOImplementer();


    public static synchronized AccountUtil getInstance() {
        if (instance == null) {
            instance = new AccountUtil();
        }
        return instance;
    }

    public void createAccount(String username, String password, String email, String inGameUser, String uuid) throws SQLException, SendFailedException {
        Account account = new Account(username, password, inGameUser, null, null, null);
        System.out.println("creating account in UTIL");
        accountDAO.createAccount(account);
        if (!email.equalsIgnoreCase("")) {
            addEmail(email, username, "", uuid);
        }
    }

    public Account getAccountByUser(String user) throws SQLException {
        System.out.println("rtying to get account be user Username: " + user);
        return accountDAO.getAccountByUser(user);
    }

    public boolean verifyAccount(String username, String code) {
        System.out.println("verifying account. The URL is: " + configReader.readPropertiesFile("url"));
        if (accountDAO.checkLogin(username, code)) {
            System.out.println("found user and password in database. User: " + username + " Passowrd: " + code);
            return true;
        } else {
            System.out.println("couldnt find user and password in database. user: " + username + " passowrd: " + code);
            return false;
        }
    }

    public void changeUsername(String newUser, String oldUsername) throws SQLException {
        accountDAO.changeUsername(newUser, oldUsername);
    }

    public void addEmail(String email, String username, String code, String uuid) throws SQLException, SendFailedException {
        accountDAO.addEmail(email, username, code, uuid);
    }

    public boolean changePassword(String oldP, String newP, String username) {
        try {
            return accountDAO.changePassword(hashPassword(oldP), hashPassword(newP), username);
        } catch (NoSuchAlgorithmException ex) {
        }
        return false;
    }

    public void addProfilePic(String URL, String username) throws SQLException {
        accountDAO.addProfilePic(URL, username);
    }

    public String hashPassword(String pw) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pw.getBytes());
        byte[] digest = md.digest();
        return Base64.getEncoder().encodeToString(digest);
    }
}
