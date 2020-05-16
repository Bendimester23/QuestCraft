package net.questcraft.account;

import javax.security.auth.login.AccountException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountSessions {
     static AccountUtil accountUtil;
    static AccountSessions instance;
    private static Map<String, String> uuidStorage= new HashMap<>();
    public String getNewUUID(String username) {
        String newUUID = UUID.randomUUID().toString();
        addUUIDToStorage(newUUID, username);
        return newUUID;
    }
    public Boolean checkUUID(String uuid) {
        System.out.println("checking UUID: " + uuid);
        if (uuidStorage.get(uuid) != null) {
            System.out.println("Found UUid in storage");
            return true;
        }
        return false;
    }
    public String getRandomUUID() {
        return UUID.randomUUID().toString();
    }
    public Account getUserInfo(String uuid) throws SQLException, AccountException, InvocationTargetException, IllegalAccessException {
        System.out.println("getUserInfo Called");
        if (checkUUID(uuid)) {
            System.out.println("found UUid and getting info");
            return accountUtil.getAccount(uuidStorage.get(uuid));


        } else {
            System.out.println("couldnt find UUId, threw accont EX");
            throw new AccountException();
        }
    }
    public void changeUserFromUUID(String newUser, String uuid) {
        uuidStorage.put(uuid, newUser);
    }
    public void addUUIDToStorage(String uuId, String usernmae) {
        uuidStorage.put(uuId, usernmae);
    }
    public static synchronized AccountSessions getInstance() {
        if (instance == null) {
            instance = new AccountSessions();
            accountUtil = AccountUtil.getInstance();
        }
        return instance;
    }
}
