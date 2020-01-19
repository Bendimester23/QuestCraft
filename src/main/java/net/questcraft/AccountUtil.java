package net.questcraft;

import java.sql.SQLException;

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
    public void createAccount(String username, String password, String email, String inGameUser) {
        Account account = new Account(username, password, inGameUser, email);

        try {
            accountDAO.createAccount(account);
        } catch (SQLException ex) {

        }
    }
    public Account getAccountByUser(String user) throws SQLException {
        return accountDAO.getAccountByUser(user);
    }
    public void changePassword(String current, String newPassword) {

    }
    public boolean verifyAccount(String username, String code) {
        if (accountDAO.checkLogin(username, code)) {
            return true;
        } else {
            return false;
        }
    }
}
