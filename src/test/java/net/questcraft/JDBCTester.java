package net.questcraft;

import net.questcraft.account.Account;
import net.questcraft.account.AccountUtil;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBCTester {
    @Test
    public void connectionTest() {
        String userName = "jeff";
        String query = "select * from secondTest where accountName = '" + userName + "'";
        String url = "jdbc:mysql://192.168.0.48:3306/test";
        String user = "root";
        String password = "fish11";
        Connection myConn;

        try {
            myConn = DriverManager.getConnection(url, user, password);
            Statement myStmt = myConn.createStatement();
            ResultSet results = myStmt.executeQuery(query);
            while (results.next()) {
                System.out.println(results.getString(3));
            }

    } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void updateSql() throws SQLException {
        String update = "INSERT INTO secondTest (accountName, inGamePlayer, email, joinDate)" +
                "VALUES ('jeff', 'jeff001', 'jeff@jeff.app', '2019-12-13')";
        String url = "jdbc:mysql://192.168.0.48:3306/test";
        String user = "root";
        String password = "fish11";
        Connection myConn;
        myConn = DriverManager.getConnection(url, user, password);
        Statement myStmt = myConn.createStatement();
        myStmt.execute(update);
    }

    public Account querySQLDatabase(String query) throws SQLException {
        String url = "jdbc:mysql://192.168.0.48:3306/test";
        String user = "root";
        String password = "fish11";
        Connection myConn;


        myConn = DriverManager.getConnection(url, user, password);
        Statement myStmt = myConn.createStatement();
        ResultSet results = myStmt.executeQuery(query);
        Account accountSet = new Account(
                 results.getString(1),
                results.getString(2),
                results.getString(4),
                results.getString(3),
                results.getString(5),
                results.getString(6)
        );
        return accountSet;


    }
    @Test
    public void selectByUser() {
        String userName = "jeff";
        String query = "select * from secondTest where accountName = '" + userName + "'";
        try {
            System.out.println(querySQLDatabase(query).getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testChangeables() {
        AccountUtil accountUtil = AccountUtil.getInstance();
        accountUtil.changePassword("john11", "john55", "john");
    }
}
