package net.questcraft.joinapp;

import java.sql.SQLException;

public interface ApplicationDAO {
    void createApplication(Application application) throws SQLException;
    Application getApplication(String username) throws SQLException;
    int changeStatus (int status, String mcUser) throws SQLException;
}
