package net.questcraft;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.questcraft.account.Account;
import net.questcraft.account.AccountSessions;
import net.questcraft.account.AccountUtil;
import net.questcraft.joinapp.Application;
import net.questcraft.joinapp.ApplicationUtil;
import net.questcraft.verifier.VerificationUtil;

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
        VerificationUtil verificationUtil = VerificationUtil.getInstance();
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
                //    public Account(String username, String password, String inGameUser, String email, String profilePic, String emailVerifyCode, String pendingMCUser, String pendingEmail) {
                Account account = new Account(username, password, null, null, null, null, mcUser, email);
                String uuid = accountSessions.getNewUUID(username);
                System.out.println("trying to make account");
                accountUtil.createAccount(account);
                System.out.println("created account");

                System.out.println("returning uuid");
                return objectMapper.writeValueAsString(uuid);
            } catch (SQLException ex) {
                System.out.println("caught SQL exeption, Exeption: " + ex.getMessage());
                return objectMapper.writeValueAsString(new ErrorClass("DataBase Malfunction, Please try again later", 1));
//            } catch (SendFailedException ex) {
//                return objectMapper.writeValueAsString(accountSessions.getNewUUID(username));
            }
        });
        get("/changePassword", (request, response) -> {
            Account account = accountSessions.getUserInfo(request.queryParams("UUID"));
            String oldPassword = accountUtil.hashPassword(request.queryParams("oldP"));
            String newPassword = accountUtil.hashPassword(request.queryParams("newP"));
            if (account.getPassword().equals(oldPassword)) {
                account.setPassword(newPassword);
                accountUtil.updateAccount(account, account.getUsername());
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
                application = new Application(request.queryParams("questions"), request.queryParams("mcUser"), request.queryParams("discordUser"), request.queryParams("email"), null, 0);
            }
            try {
                applicationUtil.createApplication(application);

            } catch (SQLException ex) {
                return objectMapper.writeValueAsString(new ErrorClass(ex.getMessage(), 1));
//            } catch (WebError ex) {
//                System.out.println("IOEX " + ex.getMessage());
//
//                return objectMapper.writeValueAsString(new ErrorClass(ex.getMessage(), 10));

            }
            return objectMapper.writeValueAsString("OK");

        });
        get("/modifyStatus", (request, response) -> {
            String requestedPin = request.queryParams("pin");
            if (requestedPin.equalsIgnoreCase(configReader.readPropertiesFile("statusModifyPin"))) {
               try {
                   String mcUsername = request.queryParams("mcUsername");
                   Application application = applicationUtil.getApplication(mcUsername);
                   int newStatus = Integer.parseInt(request.queryParams("status")) + application.getStatus();
                   application.setStatus(newStatus);
                   applicationUtil.updateApplication(application, mcUsername);
                   return objectMapper.writeValueAsString(newStatus);
                } catch (SQLException ex) {
                    return objectMapper.writeValueAsString(new ErrorClass(ex.getMessage(), 1));
                }
            } else {
                return objectMapper.writeValueAsString(new ErrorClass("Incorrect Server Verification Pin", 9));
            }
        });
        get("/getApplicationData", (request, response) -> {
            String mcUser = request.queryParams("username");
            try {
                return objectMapper.writeValueAsString(applicationUtil.getApplication(mcUser));
            } catch (SQLException e) {

                ErrorClass error = new ErrorClass(e.getMessage(), 1);
                System.out.println(error.toString());
                return objectMapper.writeValueAsString(error);
            }
        });
        get("/linkMCAccount", (request, response) -> {
            return "OK";
        });
        get("/changeUsername", (request, response) -> {
            Account account = accountSessions.getUserInfo(request.queryParams("UUID"));
            try {
                String oldUser = account.getUsername();
                String newUser = request.queryParams("newUser");
                account.setUsername(newUser);
                accountUtil.updateAccount(account, oldUser);
                accountSessions.changeUserFromUUID(newUser, request.queryParams("UUID"));
                return objectMapper.writeValueAsString("OK");
            } catch (SQLException ex) {
                return objectMapper.writeValueAsString(new ErrorClass("DataBase Malfunction, Please try again later", 1));
            }


        });
        get("/changeProfilePic", (request, response) -> {
            try {
                Account account = accountSessions.getUserInfo(request.queryParams("UUID"));
                String url = request.queryParams("URL");
                account.setProfilePic(url);
                accountUtil.updateAccount(account, account.getUsername());
                return objectMapper.writeValueAsString("OK");
            } catch (SQLException ex) {
                return objectMapper.writeValueAsString(new ErrorClass("DataBase Malfunction, Please try again later", 1));
            }

        });
        get("/verifyEmail", (request, response) -> {
            String user = request.queryParams("user");
            Account account = accountUtil.getAccount(user);
            String code = request.queryParams("emailVerification");
            try {
                verificationUtil.handleEmail(code, account);
                return objectMapper.writeValueAsString("OK");
            } catch (SQLException ex) {
                return objectMapper.writeValueAsString(new ErrorClass("Internal DataBase Error, Please Contact Administration", 1));
            } catch (WebError ex) {
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
                    return objectMapper.writeValueAsString(new ErrorClass("Could not Find contacter UUID in Lists, Please Try Again", 3));
                }
            } catch (SQLException e) {
                System.out.println("Caught SQL exception, EX:" + e.getMessage());
                return objectMapper.writeValueAsString(new ErrorClass("DataBase malfunction, Please Try Again Later", 1));
            } catch (AccountException e) {
                System.out.println("Got AccountEx Ex: " + e.getMessage());
                return objectMapper.writeValueAsString(new ErrorClass("Could not Find contacter UUID in Lists, Please Try Again", 2));
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
