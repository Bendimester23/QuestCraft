package net.questcraft;

import javax.security.auth.login.AccountException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountSessions {
    AccountUtil accountUtil = AccountUtil.getInstance();
    static AccountSessions instance;
    private Map<String, String> uuidStorage= new HashMap<>();
    public String getNewUUID(String username) {
        String newUUID = UUID.randomUUID().toString();
        addUUIDToStorage(newUUID, username);
        return newUUID;
    }
    public Boolean checkUUID(String uuid) {

        if (uuidStorage.get(uuid) != null) {
            return true;
        }
        return false;
    }
    public Account getUserInfo(String uuid) throws SQLException, AccountException {
        if (checkUUID(uuid)) {

                return accountUtil.getAccountByUser(uuidStorage.get(uuid));


        } else {
            throw new AccountException();
        }
    }
    public void addUUIDToStorage(String uuId, String usernmae) {
        uuidStorage.put(uuId, usernmae);
    }
    public static AccountSessions getInstance() {
        if (instance == null) {
            instance = new AccountSessions();
        }
        return instance;
    }
}
