package net.questcraft.joinapp;

import net.questcraft.ConfigReader;
import net.questcraft.ORLayerUtil;
import net.questcraft.ORSetup;
import net.questcraft.account.Account;
import net.questcraft.errors.InternalError;


import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class ApplicationsImplementer implements ApplicationDAO {
    ORLayerUtil contact;

    public ApplicationsImplementer() {
        ConfigReader configReader = new ConfigReader();
        contact = ORSetup.setConfiguration(configReader.readPropertiesFile("url"), configReader.readPropertiesFile("password"), configReader.readPropertiesFile("username"), "playerApplications");
    }

    @Override
    public Application getApplication(String userName) throws SQLException, InvocationTargetException, IllegalAccessException {
        return (Application) contact.getSQLData(new Application(), userName);
    }

    @Override
    public void createApplication(Application application) throws SQLException, InvocationTargetException, IllegalAccessException {
        contact.createSQL(application);
    }

    @Override
    public void updateApplication(Application application, String user) throws SQLException, InvocationTargetException, IllegalAccessException {
        contact.updateSQL(application, user);
    }

    @Override
    public void deleteApplication(String user) throws SQLException, InvocationTargetException, IllegalAccessException {
        contact.delSQL(new Account(), user);
    }
}
