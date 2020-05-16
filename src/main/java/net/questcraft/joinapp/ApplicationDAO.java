package net.questcraft.joinapp;

import net.questcraft.errors.InternalError;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface ApplicationDAO {
    Application getApplication(String userName) throws SQLException, InvocationTargetException, IllegalAccessException;

    void createApplication(Application application) throws SQLException, InvocationTargetException, IllegalAccessException;

    void updateApplication(Application application, String user) throws SQLException, InvocationTargetException, IllegalAccessException;

    void deleteApplication(String user) throws SQLException, InvocationTargetException, IllegalAccessException;
}
