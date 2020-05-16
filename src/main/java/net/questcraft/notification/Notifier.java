package net.questcraft.notification;

import net.questcraft.errors.InternalError;
import net.questcraft.joinapp.Application;

import javax.mail.SendFailedException;
import java.io.IOException;

public interface Notifier {
    void sendNotification(String recipiant, String message, String subject) throws SendFailedException;
    void sendDiscordBotApplication(Application application) throws InternalError;
    void sendDiscordVerification(String username, String discriminator, String link) throws IOException, InternalError;
}
