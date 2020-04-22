package net.questcraft.mysqlcontacter;

import net.questcraft.ConfigReader;

import java.sql.*;

public class MySQLContacter {
    ConfigReader configReader = new ConfigReader();
    public ResultSet querySQLDatabase(String query) throws SQLException {
        String url = configReader.readPropertiesFile("url");
        String user = configReader.readPropertiesFile("username");
        String password = configReader.readPropertiesFile("password");
        Connection myConn;
        myConn = DriverManager.getConnection(url, user, password);
        Statement myStmt = myConn.createStatement();
        ResultSet results = myStmt.executeQuery(query);
        return results;
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
}
