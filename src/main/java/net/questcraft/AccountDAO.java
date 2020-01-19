package net.questcraft;

import java.sql.SQLException;

public interface AccountDAO {
     public Account getAccountByUser(String userName) throws SQLException;
     public void createAccount(Account account) throws SQLException;
     public boolean checkLogin(String password, String username);
}