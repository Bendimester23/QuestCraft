package net.questcraft.mysqlcontacter.contacter;

import net.questcraft.mysqlcontacter.MySQLContacter;
import net.questcraft.account.Account;
import net.questcraft.joinapp.Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTestContacterImplementer implements MySQLContacterDAO {
    MySQLContacter contact;

    public MySQLTestContacterImplementer() {
        contact = new MySQLContacter();
    }

    public Account getAccount(String username) throws SQLException {
        String smt = "select * from testAccountData where username = '" + username + "'";
        ResultSet results = contact.querySQLDatabase(smt);
        results.next();


        Account accountSet = new Account(
                results.getString(1),
                results.getString(2),
                results.getString(4),
                results.getString(3),
                results.getString(5),
                results.getString(6),
                results.getString(7),
                results.getString(8)
        );
        return accountSet;
    }

    @Override
    public void updateSQL(String smt) throws SQLException {
        contact.updateSQLDatabase(smt);
    }

    @Override
    public ResultSet getData(String smt) throws SQLException {
        return contact.querySQLDatabase(smt);
    }

    @Override
    public Application getApp(String mcUser) throws SQLException {
        String smt = "select * from playerApplications where mcUsername = '" + mcUser + "'";
        ResultSet results = contact.querySQLDatabase(smt);
        results.next();
        Application applicationSet = new Application(
                results.getString(3),
                results.getString(1),
                results.getString(2),
                results.getString(4),
                results.getString(5),
                results.getInt(6)
        );
        return applicationSet;
    }

}


