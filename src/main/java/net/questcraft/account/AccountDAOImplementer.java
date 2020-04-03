package net.questcraft.account;

import net.questcraft.ConfigReader;
import net.questcraft.notification.NotificationMessages;
import net.questcraft.notification.NotificationUtil;

import javax.mail.SendFailedException;
import java.sql.*;
import java.util.UUID;

public class AccountDAOImplementer implements AccountDAO {
    NotificationUtil notificationUtil = NotificationUtil.getInstance();
    ConfigReader configReader = new ConfigReader();
    NotificationMessages notificationMessages = new NotificationMessages();
    public Account querySQLDatabase(String query) throws SQLException {
        String url = configReader.readPropertiesFile("url");
        String user = configReader.readPropertiesFile("username");
        String password = configReader.readPropertiesFile("password");
        Connection myConn;


        myConn = DriverManager.getConnection(url, user, password);
        Statement myStmt = myConn.createStatement();
        ResultSet results = myStmt.executeQuery(query);
        results.next();
        Account accountSet = new Account(
                results.getString(1),
                results.getString(2),
                results.getString(3),
                results.getString(4),
                results.getString(5),
                results.getString(6)

        );
        return accountSet;


    }

    public void updateSQLDatabase(String update) throws SQLException {
        String url = configReader.readPropertiesFile("url");
        String user = configReader.readPropertiesFile("username");
        String password = configReader.readPropertiesFile("password");
        Connection myConn;
        myConn = DriverManager.getConnection(url, user, password);
        Statement myStmt = myConn.createStatement();
        myStmt.execute(update);
    }


    @Override
    public Account getAccountByUser(String userName) throws SQLException {
        String query = "select * from webAccountInfo where username = '" + userName + "'";
        return querySQLDatabase(query);
    }

    @Override
    public void createAccount(Account account) throws SQLException {
        String update = "INSERT INTO webAccountInfo (username, password, mcUser, email) VALUES ('" + account.getUsername() + "', '" + account.getPassword() + "', '" + account.getInGameUser() + "', '" + account.getEmail() + "')";
        updateSQLDatabase(update);
    }

    @Override
    public boolean checkLogin(String user, String password) {
        String query = "select * from webAccountInfo where username = '" + user + "' And password = '" + password + "'";
        try {
            if (querySQLDatabase(query) != null) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return false;
    }

    @Override
    public void addProfilePic(String URL, String username) throws SQLException {
        String update = "UPDATE webAccountInfo SET profilePic = '" + URL + "' WHERE username = '" + username + "'";
        updateSQLDatabase(update);
    }
    @Override
    public void addEmail(String email, String username, String code, String uuid) throws SQLException, SendFailedException {
        if (!code.equalsIgnoreCase("")) {
            String dbQuery = "SELECT * from webAccountInfo where username = '" + username + "'";

            Account userQuery = querySQLDatabase(dbQuery);
            if (userQuery.getEmailVerifyCode().contains(code)) {
                String userEmail = userQuery.getEmailVerifyCode().substring(0, userQuery.getEmailVerifyCode().indexOf("~"));
                String dbUpdate = "UPDATE webAccountInfo SET email = '" + userEmail + "' WHERE username = '" + username + "'";
                updateSQLDatabase(dbUpdate);
                String dbRemoveVerifyUpdate = "UPDATE webAccountInfo SET emailVerifyCode = '' WHERE username = '" + username + "'";
                updateSQLDatabase(dbRemoveVerifyUpdate);
            } else {
                String playerCode = UUID.randomUUID().toString();
                String verifyLink = "questcraft.net/verifyEmail?emailVerification=" + playerCode + "&UUID=" + uuid;
                System.out.println(verifyLink);
                notificationUtil.sendNotification(email, notificationMessages.getEmailVerificationM(username, verifyLink), "Email Account Confirmation");
                String dbUpdate = "UPDATE webAccountInfo SET emailVerifyCode = '" + (email + "~" + playerCode) + "' WHERE username = '" + username + "'";
                updateSQLDatabase(dbUpdate);
            }
        } else {
            String playerCode = UUID.randomUUID().toString();
            String verifyLink = "questcraft.net/verifyEmail?emailVerification=" + playerCode + "&UUID=" + uuid;
            System.out.println(verifyLink);
            notificationUtil.sendNotification(email, notificationMessages.getEmailVerificationM(username, verifyLink), "Email Account Confirmation");
            String dbUpdate = "UPDATE webAccountInfo SET emailVerifyCode = '" + (email + "~" + playerCode) + "' WHERE username = '" + username + "'";
            updateSQLDatabase(dbUpdate);

        }

    }

    @Override
    public boolean changePassword(String oldP, String newP, String username) {
        String pDBQuery = "select * from webAccountInfo where username = '" + username + "'";
        try {
            String oldPassword = querySQLDatabase(pDBQuery).getPassword();
            if (oldP.equalsIgnoreCase(oldPassword)) {
                String dbUpdate = "UPDATE webAccountInfo SET password = '" + newP + "' WHERE username = '" + username + "'";
                updateSQLDatabase(dbUpdate);
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return false;
    }

    @Override
    public void changeUsername(String newUser, String username) throws SQLException {
        String dbUpdate = "UPDATE webAccountInfo SET username = '" + newUser + "' WHERE username = '" + username + "'";
        updateSQLDatabase(dbUpdate);

    }
}
