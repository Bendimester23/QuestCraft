package net.questcraft.account;


import net.questcraft.mysqlcontacter.MySQLUtil;


import java.sql.*;

public class AccountDaoTestImplementer implements AccountDAO {
    MySQLUtil contact = MySQLUtil.getInstance();
    @Override
    public Account getAccount(String userName) throws SQLException {
        return contact.getAccount(userName);
    }

    @Override
    public void createAccount(Account account) throws SQLException {
        String update = "INSERT INTO testAccountData (username, password, mcUser, email) VALUES ('" + account.getUsername() + "', '" + account.getPassword() + "', '" + account.getInGameUser() + "', '" + account.getEmail() + "')";
        contact.updateSQL(update);
    }

    @Override
    public void updateAccount(Account account, String user) throws SQLException {
        String update = "UPDATE testAccountData SET username = '" + account.getUsername() + "', password = '" + account.getPassword() + "', mcUser = '" + account.getInGameUser() + "', email = '" + account.getEmail() + "', profilePic = '" + account.getProfilePic() + "', emailVerifyCode = '" + account.getEmailVerifyCode() + "', pendingMCUser = '" + account.getPendingMCUser() + "', pendingEmail = '" + account.getPendingEmail() + "' WHERE username = '" + user + "';";
        contact.updateSQL(update);
    }

    @Override
    public void deleteAccount(String user) throws SQLException {
        String smt = "Delete From testAccountData Where username = '" + user + "';";
        contact.updateSQL(smt);
    }

}
