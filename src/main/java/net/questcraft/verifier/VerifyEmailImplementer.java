package net.questcraft.verifier;

import net.questcraft.WebError;
import net.questcraft.account.Account;
import net.questcraft.account.AccountUtil;
import net.questcraft.joinapp.Application;
import net.questcraft.joinapp.ApplicationUtil;
import net.questcraft.notification.NotificationMessages;
import net.questcraft.notification.NotificationUtil;

import javax.mail.SendFailedException;
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
    public void verifyAccount(VerificationData data) throws SendFailedException, WebError {
        if (data instanceof Account) {
            try {
                String playerCode = UUID.randomUUID().toString();
                Account account = (Account) data;
                String username = account.getUsername();
                String email = account.getPendingEmail();
                String verifyLink = "http://questcraft.net/verifyEmail?emailVerification=" + playerCode + "&user=" + username;
                System.out.println(verifyLink);

                notificationUtil.sendNotification(email, notificationMessages.getEmailVerificationM(username, verifyLink), "Email contacter Confirmation");
                account.setEmailVerifyCode(playerCode);
                account.setPendingEmail(email);
                accountUtil.updateAccount(account, account.getUsername());
            } catch (SQLException ex) {
                throw new WebError(ex.getMessage(), 1);
            }
        } else if (data instanceof Application) {
            try {
                String playerCode = UUID.randomUUID().toString();
                Application application = (Application) data;
                String username = application.getMcUsername();
                String email = application.getPendingEmail();
                String verifyLink = "http://questcraft.net/verifyEmail?emailVerification=" + playerCode + "&user=" + username;
                System.out.println(verifyLink);

                notificationUtil.sendNotification(email, notificationMessages.getEmailVerificationM(username, verifyLink), "Email contacter Confirmation");
                application.setPendingEmailCode(playerCode);
                application.setPendingEmail(email);
                applicationUtil.updateApplication(application, username);
            } catch (SQLException ex) {
                throw new WebError(ex.getMessage(), 1);
            }
        } else {
            throw new WebError("Verification Data not of a recognized sort", 13);
        }
    }

    @Override
    public void handleResponse(String code, VerificationData data) throws WebError, SQLException {
        if (data instanceof Account) {

            Account account = (Account) data;
            String username = account.getUsername();
            String email = account.getPendingEmail();
            Account emailCode = accountUtil.getAccount(username);
            if (code.equals(emailCode.getEmailVerifyCode())) {
                account.setEmail(email);
                account.setEmailVerifyCode(null);
                account.setPendingEmail(null);
                accountUtil.updateAccount(account, username);
            }

        } else if (data instanceof Application) {

            Application application = (Application) data;
            String username = application.getMcUsername();
            String email = application.getPendingEmail();
            Account emailCode = accountUtil.getAccount(username);
            if (code.equals(emailCode.getEmailVerifyCode())) {
                application.setEmail(email);
                application.setPendingEmailCode(null);
                application.setPendingEmail(null);
                applicationUtil.updateApplication(application, username);
            }

        } else {
            throw new WebError("Verification Data not of a recognized sort", 13);
        }
    }
}
