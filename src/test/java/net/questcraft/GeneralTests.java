package net.questcraft;

import net.questcraft.account.AccountSessions;
import net.questcraft.account.AccountUtil;
import net.questcraft.notification.NotificationMessages;
import net.questcraft.notification.NotificationUtil;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.AccountException;
import java.sql.SQLException;


public class GeneralTests {


    @Test
    public void getProp() {
        ConfigReader config = new ConfigReader();
        System.out.println(config.readPropertiesFile("testing"));
    }
    @Test
    public void checkLogin() {
        AccountUtil accountUtil = new AccountUtil();
        System.out.println(accountUtil.verifyAccount("john", "john55"));
        ConfigReader config = new ConfigReader();
        System.out.println(config.getTesting());
    }
    @Test
    public void createAccount() {
        AccountUtil accountUtil = AccountUtil.getInstance();
        //accountUtil.createAccount("Gwefnery", "SnarkyPuupy4ever", "", "");
    }
    @Test
    public void UUIDTest() throws AccountException, SQLException {
        AccountSessions accountSessions = AccountSessions.getInstance();
        String uuid = accountSessions.getNewUUID("john");
        System.out.println(uuid);
        System.out.println(accountSessions.getUserInfo(uuid));
    }
    @Test
    public void sendEmail () {
        NotificationMessages notificationMessages = new NotificationMessages();
        NotificationUtil notificationUtil = NotificationUtil.getInstance();
        //notificationUtil.sendNotification("whughes98144@gmail.com", notificationMessages.getEmailVerificationM("Will", "questcraft.net") , "Email Recovery");
    }
    @Test
    public void getMessage () {
        NotificationMessages notificationMessages = new NotificationMessages();
        System.out.println(notificationMessages.getEmailVerificationM("RYan Wood", "www.amazon.com"));
    }
//    @Test
//    public void addEmail() {
//        AccountUtil accountUtil = AccountUtil.getInstance();
//        try {
//            //accountUtil.addEmail("durganmcbroom@gmail.com", "john", null, "");
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    }
    @Test
    public void testSubString() {
        String code = "durganmcbroom@gmail.com~4998_4DIinfi#(-D9u4";
        String userEmail = code.substring(0, code.indexOf("~"));
        System.out.println(userEmail);
    }
}
