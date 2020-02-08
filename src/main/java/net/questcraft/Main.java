package net.questcraft;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.security.auth.login.AccountException;
import java.sql.SQLException;

import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;
import static spark.Spark.*;

public class Main {

    public static void main (String [] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(WRAP_ROOT_VALUE, true);
        AccountSessions accountSessions = AccountSessions.getInstance();
        AccountUtil accountUtil = AccountUtil.getInstance();
        staticFiles.location("/public");
        get("/hello", (request, response) -> "HamBurger Test");
        get("/signup", (request, response) -> {
            System.out.println("requested signup");
            System.out.println("made a account");
            String username = request.queryParams("username");

            String password = accountUtil.hashPassword(request.queryParams("password"));
            System.out.println("the hashed password is: " + password);
            String email = request.queryParams("email");
            String mcUser = request.queryParams("mcUser");
            try {
                System.out.println("trying to make account");
                accountUtil.createAccount(username, password, mcUser, email);
                System.out.println("created account");
                String uuid = accountSessions.getNewUUID(username);
                System.out.println("returning uuid");
                return objectMapper.writeValueAsString(uuid);
            } catch (SQLException ex) {
                System.out.println("caught SQL exeption, Exeption: " + ex.getMessage());
                return objectMapper.writeValueAsString(new ErrorClass("DataBase Malfunction, Please try again later", 1));
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
            System.out.println(" get info");
            String uuid = request.queryParams("UUID");

                try {
                    System.out.println("in try statement");
                    if (accountSessions.checkUUID(uuid)) {
                        System.out.println("checking UUID");
                        return objectMapper.writeValueAsString(accountSessions.getUserInfo(uuid));
                    } else {
                        System.out.println("UUid wasnt in the list");
                        return objectMapper.writeValueAsString(new ErrorClass( "Could not Find Account UUID in Lists, Please Try Again",3));
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

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }

}
