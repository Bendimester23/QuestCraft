package net.questcraft.joinapp;

import net.questcraft.WebError;
import net.questcraft.notification.NotificationUtil;

import java.sql.SQLException;

public class ApplicationUtil {
    static ApplicationUtil instance;
    ApplicationDAO applicationDAO = new ApplicationsImplementer();
    NotificationUtil notificationUtil = NotificationUtil.getInstance();
    public void createApplication(Application account) throws SQLException {
        System.out.println("creating account in UTIL");
        applicationDAO.createApplication(account);

    }

    public void deleteApp(String user) throws SQLException {
        applicationDAO.deleteApplication(user);
    }
    public void updateApplication(Application account, String user) throws SQLException {
        applicationDAO.updateApplication(account, user);
    }
    public Application getApplication(String user) throws SQLException {
        return applicationDAO.getApplication(user);
    }
    public static synchronized ApplicationUtil getInstance() {
        if (instance == null) {
            instance = new ApplicationUtil();
        }
        return instance;
    }

}
