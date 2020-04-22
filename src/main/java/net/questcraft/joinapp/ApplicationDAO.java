package net.questcraft.joinapp;

import java.sql.SQLException;

public interface ApplicationDAO {
    Application getApplication(String userName) throws SQLException;

    void createApplication(Application application) throws SQLException;

    void updateApplication(Application application, String user) throws SQLException;

    void deleteApplication(String user) throws SQLException;
}
