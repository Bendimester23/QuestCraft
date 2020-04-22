package net.questcraft.mysqlcontacter.contacter;

import net.questcraft.account.Account;
import net.questcraft.joinapp.Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MySQLContacterDAO {
    Account getAccount(String userName) throws SQLException;
    void updateSQL(String smt) throws SQLException;
    ResultSet getData(String smt) throws SQLException;
    Application getApp(String userName) throws SQLException;
}
