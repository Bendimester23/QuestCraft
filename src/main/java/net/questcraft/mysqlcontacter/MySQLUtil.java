package net.questcraft.mysqlcontacter;

import net.questcraft.ConfigReader;
import net.questcraft.account.Account;
import net.questcraft.joinapp.Application;
import net.questcraft.mysqlcontacter.contacter.MySQLContacterDAO;
import net.questcraft.mysqlcontacter.contacter.MySQLTestContacterImplementer;
import sun.awt.ConstrainableGraphics;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUtil {
    static MySQLUtil instance;
    ConfigReader configReader;
    MySQLContacterDAO contact;

    public MySQLUtil() {
        configReader = new ConfigReader();
        contact = configReader.getTesting() ? new MySQLTestContacterImplementer() : new MySQLTestContacterImplementer();
    }

    public ResultSet querySQL(String smt) throws SQLException {
        return contact.getData(smt);
    }

    public void updateSQL(String smt) throws SQLException { contact.updateSQL(smt); }

    public Application getApp(String user) throws SQLException {
        return contact.getApp(user);
    }

    public Account getAccount(String user) throws SQLException { return contact.getAccount(user);}

    public static synchronized MySQLUtil getInstance() {
        if (instance == null) {
            instance = new MySQLUtil();
        }
        return instance;
    }
}
