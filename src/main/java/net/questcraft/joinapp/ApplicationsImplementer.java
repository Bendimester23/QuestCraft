package net.questcraft.joinapp;

import net.questcraft.ConfigReader;
import net.questcraft.notification.NotificationMessages;
import net.questcraft.notification.NotificationUtil;

import java.sql.*;

public class ApplicationsImplementer implements ApplicationDAO {
    NotificationUtil notificationUtil = NotificationUtil.getInstance();
    ConfigReader configReader = new ConfigReader();
    NotificationMessages notificationMessages = new NotificationMessages();
    public Application querySQLDatabase(String query) throws SQLException {
        String url = configReader.readPropertiesFile("url");
        String user = configReader.readPropertiesFile("username");
        String password = configReader.readPropertiesFile("password");
        Connection myConn;


        myConn = DriverManager.getConnection(url, user, password);
        Statement myStmt = myConn.createStatement();
        ResultSet results = myStmt.executeQuery(query);
        results.next();
        Application applicationSet = new Application (
                results.getString(3),
                results.getString(1),
                results.getString(2),
                results.getString(4),
                results.getString(5),
                results.getInt(6)

        );
        return applicationSet;


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
    public void createApplication(Application application) throws SQLException {
        String smt = "Insert Into playerApplications (mcUsername, discordUsername, questions, email, questCraftAccount, status) Values ('" + application.getMcUsername() + "', '" + application.getDiscordUsername() + "', '" + application.getQuestions() + "', '" + application.getEmail() + "', '" + application.getQuestCraftAccount() + "', 0)";
        updateSQLDatabase(smt);

    }

    @Override
    public Application getApplication(String username) throws SQLException {
        return null;
    }

    @Override
    public int changeStatus(int status, String user) throws SQLException {
        String getCurrentStatusSMT = "SELECT * FROM playerApplications WHERE mcUsername = '" + user + "'";
        int currentStatus = querySQLDatabase(getCurrentStatusSMT).getStatus();
        int newStatus = currentStatus + status;
        String smt = "UPDATE playerApplications SET status = '" + newStatus + "' WHERE mcUsername = '" + user + "'";
        updateSQLDatabase(smt);
        return newStatus;
    }
}
