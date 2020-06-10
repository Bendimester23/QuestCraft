package net.questcraft.account;


import net.questcraft.smtcreator.TableData;
import net.questcraft.smtcreator.TableKey;

public class Account implements TableData {
    private String username;
    private String password;
    private String mcUser;
    private  String email;
    private String profilePic;
    private String emailVerifyCode;
    private String pendingMCUser;
    private String pendingEmail;

    public String getMcVerifyCode() {
        return mcVerifyCode;
    }

    public void setMcVerifyCode(String mcVerifyCode) {
        this.mcVerifyCode = mcVerifyCode;
    }

    private String mcVerifyCode;


    public Account(String username, String password, String mcUser, String email, String profilePic, String emailVerifyCode, String pendingMCUser, String pendingEmail, String mcVerifyCode) {
        this.username = username;
        this.password = password;
        this.mcUser = mcUser;
        this.email = email;
        this.profilePic = profilePic;
        this.emailVerifyCode = emailVerifyCode;
        this.pendingMCUser = pendingMCUser;
        this.pendingEmail = pendingEmail;
        this.mcVerifyCode = mcVerifyCode;
    }
    public Account(String username, String password, String mcUser, String email, String profilePic, String emailVerifyCode) {
        this.username = username;
        this.password = password;
        this.mcUser = mcUser;
        this.email = email;
        this.profilePic = profilePic;
        this.emailVerifyCode = emailVerifyCode;
    }
    public Account(String username, String password, String pendingEmail, String pendingMCUser) {
        this.username = username;
        this.password = password;
        this.pendingEmail = pendingEmail;
        this.pendingMCUser = pendingMCUser;
    }
    public String getPendingMCUser() {
        return pendingMCUser;
    }

    public void setPendingMCUser(String pendingMCUser) {
        this.pendingMCUser = pendingMCUser;
    }

    public String getPendingEmail() {
        return pendingEmail;
    }

    public void setPendingEmail(String pendingEmail) {
        this.pendingEmail = pendingEmail;
    }

    public String getUsername() {
        return username;
    }
    @TableKey(keys = {"username", "mcUser", "pendingMCUser"}, primKey = "username")
    public Account() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMcUser() {
        return mcUser;
    }

    public void setMcUser(String mcUser) {
        this.mcUser = mcUser;
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
