package net.questcraft.notification;

import net.questcraft.ErrorClass;
import net.questcraft.joinapp.Application;

import javax.mail.SendFailedException;

public interface NotificationDAO {
    void sendNotification(String recipiant, String message, String subject) throws SendFailedException;
    void sendDiscordBotApplication(Application application) throws ErrorClass;
}
