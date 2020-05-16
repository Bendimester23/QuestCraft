package net.questcraft.account;

import net.questcraft.ConfigReader;
import net.questcraft.ORLayerUtil;
import net.questcraft.ORSetup;
import net.questcraft.errors.InternalError;


import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class AccountDAOImplementer implements AccountDAO {
    ORLayerUtil contact;
    public AccountDAOImplementer() {
        ConfigReader configReader = new ConfigReader();
        contact = ORSetup.setConfiguration(configReader.readPropertiesFile("url"), configReader.readPropertiesFile("password"), configReader.readPropertiesFile("username"), "accountData");
    }

    @Override
    public Account getAccount(String userName) throws SQLException, InvocationTargetException, IllegalAccessException {
        return (Account) contact.getSQLData(new Account(), userName);
    }

    @Override
    public void createAccount(Account account) throws SQLException, InvocationTargetException, IllegalAccessException {
        contact.createSQL(account);
    }

    @Override
    public void updateAccount(Account account, String user) throws SQLException, InvocationTargetException, IllegalAccessException, InternalError {
        contact.updateSQL(account, user);
    }

    @Override
    public void deleteAccount(String user) throws SQLException, InvocationTargetException, IllegalAccessException {
        contact.delSQL(new Account(), user);
    }

}
