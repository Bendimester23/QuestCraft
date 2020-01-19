package net.questcraft;

import java.sql.*;

public class AccountDAOImplementer implements AccountDAO {
    ConfigReader configReader = new ConfigReader();
    public Account querySQLDatabase(String query) throws SQLException {
        String url = configReader.readPropertiesFile("url");
        String user = configReader.readPropertiesFile("username");
        String password = configReader.readPropertiesFile("password");
        Connection myConn;


            myConn = DriverManager.getConnection(url, user, password);
            Statement myStmt = myConn.createStatement();
            ResultSet results = myStmt.executeQuery(query);
            Account accountSet = new Account(
               results.getString(1),
                results.getString(2),
                results.getString(3),
                results.getString(4)
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
        String query = "select * from webAccountInfo where username = '" + user + "' And password = '" + password + "'" ;
        try {
            if (querySQLDatabase(query) != null) {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }
}
