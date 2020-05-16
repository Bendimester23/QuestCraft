package net.questcraft;

import net.questcraft.account.Account;
import net.questcraft.account.AccountUtil;
import net.questcraft.errors.InternalError;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class AccountTesting {
    @Test
    public void testAccountGetAccount() throws SQLException, InvocationTargetException, IllegalAccessException {
        AccountUtil accountUtil = AccountUtil.getInstance();
        System.out.println(accountUtil.getAccount("withEmail"));
    }

    @Test
    public void update() throws SQLException, InvocationTargetException, IllegalAccessException, InternalError {
        AccountUtil accountUtil = AccountUtil.getInstance();
    }

    @Test
    public void testCreate() throws SQLException, InvocationTargetException, IllegalAccessException {
        AccountUtil accountUtil = AccountUtil.getInstance();
    }

    @Test
    public void delAccount() throws SQLException, InvocationTargetException, IllegalAccessException {
        AccountUtil accountUtil = AccountUtil.getInstance();
        accountUtil.deleteAccount("new TESTING");
    }
}
