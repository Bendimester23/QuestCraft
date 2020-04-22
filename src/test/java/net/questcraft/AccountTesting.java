package net.questcraft;

import net.questcraft.account.Account;
import net.questcraft.account.AccountUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class AccountTesting {
    @Test
    public void testAccountGetAccount() throws SQLException {
        AccountUtil accountUtil = AccountUtil.getInstance();
        System.out.println(accountUtil.getAccount("withEmail"));
    }

    //    public Account(String username, String password, String inGameUser, String email, String profilePic, String emailVerifyCode, String pendingMCUser, String pendingEmail) {
    @Test
    public void update() throws SQLException {
        AccountUtil accountUtil = AccountUtil.getInstance();
        accountUtil.updateAccount(new Account("withEmail", "Something REALLYCOOl", "WithoutEmail", "chestoo", "dogo", "NULL", "NULL", "NULL"), "withEmail");
    }

    @Test
    public void testCreate() throws SQLException {
        AccountUtil accountUtil = AccountUtil.getInstance();
        accountUtil.createAccount(new Account("new TESTING", "Something REALLYCOOl", "WithoutEmail", "chestoo", "dogo", "NULL", "NULL", "NULL"));
    }

    @Test
    public void delAccount() throws SQLException {
        AccountUtil accountUtil = AccountUtil.getInstance();
        accountUtil.deleteAccount("new TESTING");
    }
}
