package net.questcraft.account;

public class Account {
    private String username;
    private String password;
    private String inGameUser;
    private  String email;
    private String profilePic;
    private String emailVerifyCode;

    public Account(String username, String password, String inGameUser, String email, String profilePic, String emailVerifyCode) {
        this.username = username;
        this.password = password;
        this.inGameUser = inGameUser;
        this.email = email;
        this.profilePic = profilePic;
        this.emailVerifyCode = emailVerifyCode;
    }

    public String getUsername() {
        return username;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmailVerifyCode() {
        return emailVerifyCode;
    }

    public void setEmailVerifyCode(String emailVerifyCode) {
        this.emailVerifyCode = emailVerifyCode;
    }
}
