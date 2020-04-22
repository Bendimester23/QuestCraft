package net.questcraft.notification;

import net.questcraft.WebError;
import net.questcraft.joinapp.Application;

import javax.mail.SendFailedException;

public interface NotificationDAO {
    void sendNotification(String recipiant, String message, String subject) throws SendFailedException;
    void sendDiscordBotApplication(Application application) throws WebError;
}
