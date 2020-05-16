package net.questcraft.verifier.minecraft;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MCReturnableLinks {
    String account;
    String application;
    String accountUser;
    String appUser;

    public String getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(String accountUser) {
        this.accountUser = accountUser;
    }

    public String getAppUser() {
        return appUser;
    }
    @JsonIgnore
    public boolean isNull() {
        if (account == null && application == null) {
            return true;
        }
        return false;
    }
    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
