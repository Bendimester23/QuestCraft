package net.questcraft.notification;

import net.questcraft.ConfigReader;
import net.questcraft.errors.InternalError;
import net.questcraft.joinapp.Application;

import javax.mail.SendFailedException;
import java.io.IOException;

public class NotificationUtil {
    static NotificationUtil instance;
    ConfigReader configReader = new ConfigReader();
    Notifier notificationInterface = new NotificationImplementer();

    public void sendNotification(String recipiant, String message, String subject) throws SendFailedException {
        notificationInterface.sendNotification(recipiant, message, subject);
    }
    public void sendAppToDiscord(Application application) throws InternalError {
            notificationInterface.sendDiscordBotApplication(application);
    }
    public void sendDiscordVerification(String username, String discriminator, String link) throws IOException, InternalError {
        notificationInterface.sendDiscordVerification(username, discriminator, link);
    }
    public static synchronized NotificationUtil getInstance() {
        if (instance == null) {
            instance = new NotificationUtil();
        }
        return instance;
    }
}
