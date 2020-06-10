package net.questcraft.account;

import net.questcraft.errors.InternalError;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface AccountDAO {
    Account getAccount(String userName) throws SQLException, InvocationTargetException, IllegalAccessException;

    void createAccount(Account account) throws SQLException, InvocationTargetException, IllegalAccessException;

    void updateAccount(Account account, String user) throws SQLException, InvocationTargetException, IllegalAccessException, InternalError;

    void deleteAccount(String user) throws SQLException, InvocationTargetException, IllegalAccessException;

//    boolean checkLogin(String password, String username);
//
//    void changeUsername(String newUser, String oldUsername) throws SQLException;
//
//    void addEmail(String email, String username) throws SQLException;
//
//    boolean changePassword(String oldP, String newP, String username);
//
//    void addProfilePic(String URL, String username) throws SQLException;
//
//    void addPendingEmail(String user, String value) throws SQLException;

    
}