package net.questcraft.joinapp;

import net.questcraft.ErrorClass;
import net.questcraft.notification.NotificationUtil;

import java.sql.SQLException;

public class ApplicationUtil {
    static ApplicationUtil instance;
    ApplicationDAO applicationInterface = new ApplicationsImplementer();
    NotificationUtil notificationUtil = NotificationUtil.getInstance();
    public void createApplication(Application application) throws ErrorClass, SQLException {
        applicationInterface.createApplication(application);
        notificationUtil.sendAppToDiscord(application);
    }
    public static synchronized ApplicationUtil getInstance() {
        if (instance == null) {
            instance = new ApplicationUtil();
        }
        return instance;
    }
    public int changeStatus(int status, String username) throws SQLException {
         return applicationInterface.changeStatus(status, username);
    }
}
