package net.questcraft.account;

import javax.mail.SendFailedException;
import java.sql.SQLException;

public interface AccountDAO {
    Account getAccountByUser(String userName) throws SQLException;

    void createAccount(Account account) throws SQLException;

    boolean checkLogin(String password, String username);

    void changeUsername(String newUser, String oldUsername) throws SQLException;

    void addEmail(String email, String username, String code, String uuid) throws SQLException, SendFailedException;

    boolean changePassword(String oldP, String newP, String username);

    void addProfilePic(String URL, String username) throws SQLException;
}