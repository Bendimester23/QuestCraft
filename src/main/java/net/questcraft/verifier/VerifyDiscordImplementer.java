package net.questcraft.verifier;

import net.questcraft.ConfigReader;
import net.questcraft.errors.InternalError;
import net.questcraft.account.Account;
import net.questcraft.joinapp.Application;
import net.questcraft.joinapp.ApplicationUtil;
import net.questcraft.notification.NotificationUtil;
import net.questcraft.smtcreator.TableData;

import javax.mail.SendFailedException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.UUID;

public class VerifyDiscordImplementer implements Verifier {
    NotificationUtil notificationUtil;
    ConfigReader configReader;
    ApplicationUtil applicationUtil;
     public VerifyDiscordImplementer() {
         notificationUtil = NotificationUtil.getInstance();
         configReader = new ConfigReader();
         applicationUtil = ApplicationUtil.getInstance();
     }

    @Override
    public void verifyAccount(TableData data) throws SendFailedException, InternalError, IOException, SQLException, InvocationTargetException, IllegalAccessException {
        if (data instanceof Account) {
            throw new InternalError("Unfinished Feature", 14);
        } else if(data instanceof Application) {
            String playerCode = UUID.randomUUID().toString();

            Application application = (Application) data;
            String pendingDiscord = application.getPendingDiscordUser();
            String user = pendingDiscord.substring(0, pendingDiscord.indexOf("#"));
            String discriminator = pendingDiscord.substring(pendingDiscord.indexOf("#") + 1);
            String link = configReader.getTesting() ? "http://localhost:4567/verifyDiscord?type=application&user=" + application.getId() + "&discordVerification=" + playerCode : "http://questcraft.net/verifyDiscord?type=application&user=" + pendingDiscord + "&discordVerification=" + playerCode;
            application.setDiscordVerifyCode(playerCode);
            applicationUtil.updateApplication(application, application.getId().toString());
            notificationUtil.sendDiscordVerification(user, discriminator, link);
        } else {
            throw new InternalError("Verification Data not of a recognized sort", 13);
        }
    }

    @Override
    public void handleResponse(String code, TableData data) throws InternalError, SQLException, InvocationTargetException, IllegalAccessException {
        if (data instanceof Account) {
            throw new InternalError("Unfinished Feature", 14);
        } else if(data instanceof Application) {
            Application application = (Application) data;
            if (code.equals(application.getDiscordVerifyCode())) {
                application.setDiscordUsername(application.getPendingDiscordUser());
                application.setPendingDiscordUser("NULL");
                application.setDiscordVerifyCode("NULL");
                applicationUtil.updateApplication(application, application.getId().toString());
            } else {
                throw new InternalError("Incorrect Verification Code", 15);
            }
        } else {
            throw new InternalError("Verification Data not of a recognized sort", 13);
        }
    }
}
