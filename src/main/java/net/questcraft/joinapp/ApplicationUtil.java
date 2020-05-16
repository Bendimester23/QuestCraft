package net.questcraft.joinapp;

import net.questcraft.errors.InternalError;
import net.questcraft.notification.NotificationUtil;
import net.questcraft.verifier.VerificationUtil;

import javax.mail.SendFailedException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ApplicationUtil {
    static ApplicationUtil instance;
    ApplicationDAO applicationDAO;
    VerificationUtil verificationUtil;
    NotificationUtil notificationUtil;
    public ApplicationUtil() {
        applicationDAO = new ApplicationsImplementer();
        verificationUtil = VerificationUtil.getInstance();
        notificationUtil = NotificationUtil.getInstance();
    }

    public void createApplication(Application application) throws SQLException, InvocationTargetException, IllegalAccessException, SendFailedException, IOException, InternalError {
        System.out.println("creating app in UTIL");
        applicationDAO.createApplication(application);
        Application app = this.getApplication(application.getPendingMCUser());
        if (application.getPendingMCUser() != null) {
            verificationUtil.verifyMinecraft(app);
        }
        if (application.getPendingDiscordUser() != null) {
            verificationUtil.verifyDiscord(app);
        }
        if (application.getPendingEmail() != null) {
            verificationUtil.verifyEmail(app);
        }
        notificationUtil.sendAppToDiscord(app);
    }

    public void deleteApp(String user) throws SQLException, InvocationTargetException, IllegalAccessException {
        applicationDAO.deleteApplication(user);
    }
    public void updateApplication(Application account, String user) throws SQLException, InvocationTargetException, IllegalAccessException, InternalError {
        applicationDAO.updateApplication(account, user);
    }
    public Application getApplication(String user) throws SQLException, InvocationTargetException, IllegalAccessException {
        return applicationDAO.getApplication(user);
    }
    public static synchronized ApplicationUtil getInstance() {
        if (instance == null) {
            instance = new ApplicationUtil();
        }
        return instance;
    }

}
