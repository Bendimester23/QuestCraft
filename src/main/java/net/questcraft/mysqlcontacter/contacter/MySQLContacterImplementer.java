package net.questcraft.mysqlcontacter.contacter;

import net.questcraft.joinapp.Application;
import net.questcraft.mysqlcontacter.MySQLContacter;
import net.questcraft.account.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLContacterImplementer implements MySQLContacterDAO {
    MySQLContacter contact;

    public MySQLContacterImplementer() {
        contact = new MySQLContacter();
    }

    public Account getAccount(String username) throws SQLException {
        String smt = "select * from accountData where username = '" + username + "'";
        ResultSet results = contact.querySQLDatabase(smt);
        results.next();
        Account applicationSet = new Account(
                results.getString(1),
                results.getString(2),
                results.getString(3),
                results.getString(4),
                results.getString(5),
                results.getString(6),
                results.getString(7),
                results.getString(8)
        );
        return applicationSet;
    }

    @Override
    public void updateSQL(String smt) throws SQLException {
        contact.updateSQLDatabase(smt);
    }

    @Override
    public ResultSet getData(String smt) throws SQLException {
        return contact.querySQLDatabase(smt);
    }

//    public Application(String questions, String mcUsername, String discordUsername, String email, String questCraftAccount, String pendingMC, String pendingEmail, String pendingEmailCode, String pendingDiscord, int status) {
    @Override
    public Application getApp(String mcUser) throws SQLException {
        String smt = "select * from playerApplications where username = '" + mcUser + "'";
        ResultSet results = contact.querySQLDatabase(smt);
        results.next();
        Application applicationSet = new Application(
                results.getString(3),
                results.getString(1),
                results.getString(2),
                results.getString(4),
                results.getString(5),
                results.getString(10),
                results.getString(8),
                results.getString(7),
                results.getString(9),
                results.getInt(6)
        );
        return applicationSet;
    }


}
