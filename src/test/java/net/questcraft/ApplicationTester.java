package net.questcraft;

import net.questcraft.joinapp.Application;
import net.questcraft.joinapp.ApplicationUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ApplicationTester {
    @Test
    public void getApp() throws SQLException {
        ApplicationUtil applicationUtil = ApplicationUtil.getInstance();
        System.out.println(applicationUtil.getApplication("R0BL0C0W"));
    }

    //    public Application(String questions, String mcUsername, String discordUsername, String email, String questCraftAccount, String pendingMC, String pendingEmail, String pendingEmailCode, String pendingDiscord, int status) {
    @Test
    public void createApp() throws SQLException {
        ApplicationUtil applicationUtil = ApplicationUtil.getInstance();
        applicationUtil.createApplication(new Application("SOmething reAlly CooL", "nothing", "something", "randoms", "ok", 1));
    }

    @Test
    public void updateAPp() throws SQLException {
        ApplicationUtil applicationUtil = ApplicationUtil.getInstance();
        Application application = new Application("SOmething reAlly CooL", "Changed", "something", "randoms", "ok", 1);
        applicationUtil.updateApplication(application, "nothing");
    }

    @Test
    public void deleteApp() throws SQLException {
        ApplicationUtil applicationUtil = ApplicationUtil.getInstance();
        applicationUtil.deleteApp("Changed");
    }
}
