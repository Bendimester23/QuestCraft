package net.questcraft.joinapp;

public class Application {
    String questions;
    String mcUsername;
    String discordUsername;
    String email;
    String questCraftAccount;
    int status;

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
