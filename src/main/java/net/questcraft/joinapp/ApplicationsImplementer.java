package net.questcraft.joinapp;

import net.questcraft.ConfigReader;
import net.questcraft.mysqlcontacter.MySQLUtil;
import net.questcraft.notification.NotificationMessages;
import net.questcraft.notification.NotificationUtil;

import java.sql.*;

public class ApplicationsImplementer implements ApplicationDAO {

    MySQLUtil contact = MySQLUtil.getInstance();
    @Override
    public Application getApplication(String userName) throws SQLException {
        return contact.getApp(userName);
    }
    @Override
    public void createApplication(Application application) throws SQLException {
        String smt = "Insert Into playerApplications (mcUsername, discordUsername, questions, email, questCraftAccount, status) Values ('" + application.getMcUsername() + "', '" + application.getDiscordUsername() + "', '" + application.getQuestions() + "', '" + application.getEmail() + "', '" + application.getQuestCraftAccount() + "', 0)";
        contact.updateSQL(smt);
    }

    @Override
    public void updateApplication(Application application, String user) throws SQLException {
        String update = "UPDATE playerApplications SET mcUsername = '" + application.getMcUsername() + "', discordUsername = '" + application.getDiscordUsername() + "', questions = '" + application.getQuestions() + "', email = '" + application.getEmail() + "', questCraftAccount = '" + application.getQuestCraftAccount() + "', emailVerifyCode = '" + application.getPendingEmailCode() + "', pendingMCUser = '" + application.getPendingMC() + "', pendingEmail = '" + application.getPendingEmail() + "' WHERE mcUsername = '" + user + "';";
        contact.updateSQL(update);
    }

    @Override
    public void deleteApplication(String user) throws SQLException {
        String smt = "Delete FROM playerApplications Where mcUsername = '" + user + "';";
        contact.updateSQL(smt);
    }
}
