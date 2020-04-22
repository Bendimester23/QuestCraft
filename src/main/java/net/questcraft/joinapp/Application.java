package net.questcraft.joinapp;

import net.questcraft.verifier.VerificationData;

public class Application implements VerificationData {
    String questions;
    String mcUsername;
    String discordUsername;
    String email;
    String questCraftAccount;
    String pendingMC;
    String pendingEmail;
    String pendingEmailCode;

    public Application(String questions, String mcUsername, String discordUsername, String email, String questCraftAccount, String pendingMC, String pendingEmail, String pendingEmailCode, String pendingDiscord, int status) {
        this.questions = questions;
        this.mcUsername = mcUsername;
        this.discordUsername = discordUsername;
        this.email = email;
        this.questCraftAccount = questCraftAccount;
        this.pendingMC = pendingMC;
        this.pendingEmail = pendingEmail;
        this.pendingEmailCode = pendingEmailCode;
        this.pendingDiscord = pendingDiscord;
        this.status = status;
    }

    String pendingDiscord;
    int status;

    public String getPendingMC() {
        return pendingMC;
    }

    public void setPendingMC(String pendingMC) {
        this.pendingMC = pendingMC;
    }

    public String getPendingEmail() {
        return pendingEmail;
    }

    public void setPendingEmail(String pendingEmail) {
        this.pendingEmail = pendingEmail;
    }

    public String getPendingEmailCode() {
        return pendingEmailCode;
    }

    public void setPendingEmailCode(String pendingEmailCode) {
        this.pendingEmailCode = pendingEmailCode;
    }

    public String getPendingDiscord() {
        return pendingDiscord;
    }

    public void setPendingDiscord(String pendingDiscord) {
        this.pendingDiscord = pendingDiscord;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        status = status;
    }


    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getMcUsername() {
        return mcUsername;
    }

    public void setMcUsername(String mcUsername) {
        this.mcUsername = mcUsername;
    }

    public String getDiscordUsername() {
        return discordUsername;
    }

    public void setDiscordUsername(String discordUsername) {
        this.discordUsername = discordUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestCraftAccount() {
        return questCraftAccount;
    }

    public void setQuestCraftAccount(String questCraftAccount) {
        this.questCraftAccount = questCraftAccount;
    }

    public Application(String questions, String mcUsername, String discordUsername, String email, String questCraftAccount, int status) {
        this.questions = questions;
        this.mcUsername = mcUsername;
        this.discordUsername = discordUsername;
        this.email = email;
        this.questCraftAccount = questCraftAccount;
        this.status = status;
    }


}
