package net.questcraft;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;

public class AccountUtil {
    static AccountUtil instance;
    ConfigReader configReader = new ConfigReader();
    AccountDAO accountDAO = configReader.getTesting()? new AccountDaoTestImplementer(): new AccountDAOImplementer();
    {
        System.out.println(configReader.getTesting());
    }
    public static AccountUtil getInstance() {
        if (instance == null) {
            instance = new AccountUtil();
        }
        return instance;
    }
    public void createAccount(String username, String password, String email, String inGameUser) throws SQLException {
        Account account = new Account(username, password, email, inGameUser);
        System.out.println("creating account in UTIL");

            accountDAO.createAccount(account);

    }
    public Account getAccountByUser(String user) throws SQLException {
        System.out.println("rtying to get account be user Username: " + user);
        return accountDAO.getAccountByUser(user);
    }
    public void changePassword(String username, String newPassword) {

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
    public String hashPassword(String pw) throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pw.getBytes());
        byte[] digest = md.digest();
        return Base64.getEncoder().encodeToString(digest);
    }
}
