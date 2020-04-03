package net.questcraft.notification;

import net.questcraft.ConfigReader;
import net.questcraft.ErrorClass;
import net.questcraft.joinapp.Application;

import javax.mail.SendFailedException;

public class NotificationUtil {
    static NotificationUtil instance;
    ConfigReader configReader = new ConfigReader();
    NotificationDAO notificationInterface = new NotificationImplementer();

    public void sendNotification(String recipiant, String message, String subject) throws SendFailedException {
        notificationInterface.sendNotification(recipiant, message, subject);
    }
    public void sendAppToDiscord(Application application) throws ErrorClass {

            notificationInterface.sendDiscordBotApplication(application);

    }
    public static synchronized NotificationUtil getInstance() {
        if (instance == null) {
            instance = new NotificationUtil();
        }
        return instance;
    }
}
