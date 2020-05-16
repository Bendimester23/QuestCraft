package net.questcraft.verifier;

import net.questcraft.errors.InternalError;
import net.questcraft.account.Account;
import net.questcraft.account.AccountUtil;
import net.questcraft.joinapp.Application;
import net.questcraft.joinapp.ApplicationUtil;
import net.questcraft.notification.NotificationMessages;
import net.questcraft.notification.NotificationUtil;
import net.questcraft.smtcreator.TableData;

import javax.mail.SendFailedException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.UUID;

public class VerifyEmailImplementer implements Verifier {
    NotificationUtil notificationUtil;
    NotificationMessages notificationMessages;
    AccountUtil accountUtil;
    ApplicationUtil applicationUtil;

    public VerifyEmailImplementer() {
        notificationUtil = NotificationUtil.getInstance();
        notificationMessages = new NotificationMessages();
        accountUtil = AccountUtil.getInstance();
        applicationUtil = ApplicationUtil.getInstance();
    }

    @Override
    public void verifyAccount(TableData data) throws SendFailedException, InternalError, InvocationTargetException, IllegalAccessException {
        if (data instanceof Account) {
            try {
                String playerCode = UUID.randomUUID().toString();
                Account account = (Account) data;
                String username = account.getUsername();
                String email = account.getPendingEmail();
                String verifyLink = "http://questcraft.net/verifyEmail?emailVerification=" + playerCode + "&user=" + username + "&type=account";
                System.out.println(verifyLink);

                notificationUtil.sendNotification(email, notificationMessages.getEmailVerificationM(username, verifyLink), "Email contacter Confirmation");

                account.setEmailVerifyCode(playerCode);
                accountUtil.updateAccount(account, account.getUsername());
            } catch (SQLException ex) {
                throw new InternalError(ex.getMessage(), 1);
            }
        } else if (data instanceof Application) {
            try {
                String playerCode = UUID.randomUUID().toString();
                Application application = (Application) data;
                String id = application.getId().toString();
                String email = application.getPendingEmail();
                String verifyLink = "http://questcraft.net/verifyEmail?emailVerification=" + playerCode + "&user=" + id + "&type=application";
                System.out.println(verifyLink);

                notificationUtil.sendNotification(email, notificationMessages.getEmailVerificationM(application.getMcUsername(), verifyLink), "Email contacter Confirmation");
                application.setEmailVerifyCode(playerCode);
                applicationUtil.updateApplication(application, id);
            } catch (SQLException ex) {
                throw new InternalError(ex.getMessage(), 1);
            }
        } else {
            throw new InternalError("Verification Data not of a recognized sort", 13);
        }
    }

    @Override
    public void handleResponse(String code, TableData data) throws InternalError, SQLException, InvocationTargetException, IllegalAccessException {
        if (data instanceof Account) {

            Account account = (Account) data;
            String username = account.getUsername();
            String email = account.getPendingEmail();
            if (code.equals(account.getEmailVerifyCode())) {
                account.setEmail(email);
                account.setEmailVerifyCode(null);
                account.setPendingEmail(null);
                accountUtil.updateAccount(account, username);
            } else { throw new InternalError("Incorrect Verification", 15); }

        } else if (data instanceof Application) {

            Application application = (Application) data;
            String id = application.getId().toString();
            String email = application.getPendingEmail();
            if (code.equals(application.getEmailVerifyCode())) {
                application.setEmail(email);
                application.setEmailVerifyCode(null);
                application.setPendingEmail(null);
                applicationUtil.updateApplication(application, id);
            } else { throw new InternalError("Incorrect Verification", 15); }

        } else {
            throw new InternalError("Verification Data not of a recognized sort", 13);
        }
    }
}
