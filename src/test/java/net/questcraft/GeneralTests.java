package net.questcraft;

import org.junit.jupiter.api.BeforeAll;
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
}
