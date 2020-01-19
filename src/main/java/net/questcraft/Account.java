package net.questcraft;

public class Account {
    private String username;
    private String password;
    private String inGameUser;
    private  String email;
    private boolean isAdmin;

    public String getUsername() {
        return username;
    }

    public Account(String username, String password, String inGameUser, String email) {
        this.username = username;
        this.password = password;
        this.inGameUser = inGameUser;
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInGameUser() {
        return inGameUser;
    }

    public void setInGameUser(String inGameUser) {
        this.inGameUser = inGameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
