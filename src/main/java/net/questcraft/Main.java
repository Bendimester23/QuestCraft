package net.questcraft;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.questcraft.account.Account;
import net.questcraft.account.AccountSessions;
import net.questcraft.account.AccountUtil;
import net.questcraft.joinapp.Application;
import net.questcraft.joinapp.ApplicationUtil;

import javax.mail.SendFailedException;
import javax.security.auth.login.AccountException;
import java.sql.SQLException;

import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        ApplicationUtil applicationUtil = ApplicationUtil.getInstance();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(WRAP_ROOT_VALUE, true);
        AccountSessions accountSessions = AccountSessions.getInstance();
        AccountUtil accountUtil = AccountUtil.getInstance();
        ConfigReader configReader = new ConfigReader();
        staticFiles.location("/public");
        get("/signup", (request, response) -> {
            System.out.println("requested signup");
            System.out.println("made a account");
            String username = request.queryParams("username");
            String password = accountUtil.hashPassword(request.queryParams("password"));
            System.out.println("the hashed password is: " + password);
            String email = request.queryParams("email");
            String mcUser = request.queryParams("mcUser");
            try {
                String uuid = accountSessions.getNewUUID(username);
                System.out.println("trying to make account");
                accountUtil.createAccount(username, password, email, mcUser, uuid);
                System.out.println("created account");

                System.out.println("returning uuid");
                return objectMapper.writeValueAsString(uuid);
            } catch (SQLException ex) {
                System.out.println("caught SQL exeption, Exeption: " + ex.getMessage());
                return objectMapper.writeValueAsString(new ErrorClass("DataBase Malfunction, Please try again later", 1));
            } catch (SendFailedException ex) {
                return objectMapper.writeValueAsString(accountSessions.getNewUUID(username));
            }
        });
        get("/changePassword", (request, response) -> {
            String username = accountSessions.getUserInfo(request.queryParams("UUID")).getUsername();
            if (accountUtil.changePassword(request.queryParams("oldP"),request.queryParams("newP"), username)) {
                return objectMapper.writeValueAsString("OK");
            } else {
                return objectMapper.writeValueAsString(new ErrorClass("Incorrect password", 5));
            }

        });
        get("/createApplication", (request, response) -> {
            Application application;
            try {
                String username = accountSessions.getUserInfo(request.queryParams("UUID")).getUsername();
                application = new Application(request.queryParams("questions"), request.queryParams("mcUser"), request.queryParams("discordUser"), request.queryParams("email"), username, 0);

            } catch (AccountException ex) {
                application = new Application(request.queryParams("questions"), request.queryParams("mcUser"),request.queryParams("discordUser"), request.queryParams("email"),  null, 0);
            }
            try {
                applicationUtil.createApplication(application);

            } catch (SQLException ex) {
                return objectMapper.writeValueAsString(new ErrorClass(ex.getMessage(), 1));
            } catch (ErrorClass ex) {
                System.out.println("IOEX " + ex.getMessage());

                    return objectMapper.writeValueAsString(new ErrorClass(ex.getMessage(), 10));

            }
            return objectMapper.writeValueAsString("OK");

        });
        get("/modifyStatus", (request, response) -> {
            String requestedPin = request.queryParams("pin");
            if (requestedPin.equalsIgnoreCase(configReader.readPropertiesFile("statusModifyPin"))) {
                try {
                    return objectMapper.writeValueAsString(applicationUtil.changeStatus(Integer.parseInt(request.queryParams("status")), request.queryParams("mcUsername")));
                } catch (SQLException ex) {
                    return objectMapper.writeValueAsString(new ErrorClass(ex.getMessage(), 1));
                }
            } else {
                return objectMapper.writeValueAsString(new ErrorClass("Incorrect Server Verification Pin", 9));
            }
        });
        get("/getApplicationData", (request, response) -> {
            return "OK";
        });
        get("/linkMCAccount", (request, response) -> {
            return "OK";
        });
        get("/changeUsername", (request, response) -> {
            String username = accountSessions.getUserInfo(request.queryParams("UUID")).getUsername();
            try {
                accountUtil.changeUsername(request.queryParams("newUser"), username);
                accountSessions.changeUserFromUUID(request.queryParams("newUser"), request.queryParams("UUID"));
                return objectMapper.writeValueAsString("OK");
            } catch (SQLException ex) {
                return objectMapper.writeValueAsString(new ErrorClass("DataBase Malfunction, Please try again later", 1));
            }
        });
        get("/changeProfilePic", (request, response) -> {
            try {
                String username = accountSessions.getUserInfo(request.queryParams("UUID")).getUsername();
                accountUtil.addProfilePic(request.queryParams("URL"), username);
                return objectMapper.writeValueAsString("OK");
            } catch (SQLException ex) {
                return objectMapper.writeValueAsString(new ErrorClass("DataBase Malfunction, Please try again later", 1));
            }
        });
        get("/verifyEmail", (request, response) -> {
            String uuid = request.queryParams("UUID");
            System.out.println("the uuid is " + uuid);
            Account userInfo = accountSessions.getUserInfo(uuid);
            System.out.println(userInfo.getUsername() +  " And the uuid is: " + uuid);
            String username = userInfo.getUsername();
            try {
                accountUtil.addEmail(request.queryParams("email"), username, request.queryParams("emailVerification"), request.queryParams("UUID"));
                if (!request.queryParams("emailVerification").equalsIgnoreCase("")) {
                    response.redirect("/verify.html");
                }
                return objectMapper.writeValueAsString("OK");
            } catch (SQLException ex) {
                return objectMapper.writeValueAsString(new ErrorClass("Internal DataBase Error, Please Contact Administration", 1));
            } catch(SendFailedException ex) {
                return objectMapper.writeValueAsString(new ErrorClass("Email Failed to Send", 7));
            }


        });
        get("/logIn", (request, response) -> {
            System.out.println("tryed to login");
            String username = request.queryParams("username");
            String password = accountUtil.hashPassword(request.queryParams("password"));
            System.out.println("got params");
            if (accountUtil.verifyAccount(username, password)) {
                System.out.println("found user and password in database User: " + username + " Password: " + password);
                String uuid = accountSessions.getNewUUID(username);
                System.out.println("returned username");
                return objectMapper.writeValueAsString(uuid);
            } else {
                System.out.println("couldnt find user with password in databases");
                System.out.println("sending errorclass");
                ErrorClass errorClass = new ErrorClass("Could not Verify User and Password", 3);
                System.out.println(objectMapper.writeValueAsString(errorClass));
                return objectMapper.writeValueAsString(errorClass);
            }
        });
        get("/getInfo", (request, response) -> {
            System.out.println("get info");
            String uuid = request.queryParams("UUID");

            try {
                System.out.println("in try statement");
                if (accountSessions.checkUUID(uuid)) {
                    System.out.println("checking UUID");
                    return objectMapper.writeValueAsString(accountSessions.getUserInfo(uuid));
                } else {
                    System.out.println("UUid wasnt in the list");
                    return objectMapper.writeValueAsString(new ErrorClass("Could not Find Account UUID in Lists, Please Try Again", 3));
                }
            } catch (SQLException e) {
                System.out.println("Caught SQL exception, EX:" + e.getMessage());
                return objectMapper.writeValueAsString(new ErrorClass("DataBase malfunction, Please Try Again Later", 1));
            } catch (AccountException e) {
                System.out.println("Got AccountEx Ex: " + e.getMessage());
                return objectMapper.writeValueAsString(new ErrorClass("Could not Find Account UUID in Lists, Please Try Again", 2));
            }

        });
        get("/verify", (request, response) -> accountSessions.checkUUID(request.queryParams("UUID")));


        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return objectMapper.writeValueAsString("OK");
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }

}
