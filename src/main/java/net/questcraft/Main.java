package net.questcraft;


import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.SQLException;

import static spark.Spark.*;

public class Main {

    public static void main (String [] args) {
        AccountSessions accountSessions = AccountSessions.getInstance();
        AccountUtil accountUtil = AccountUtil.getInstance();
        staticFiles.location("/public");
        get("/hello", (request, response) -> "HamBurger Test");
        get("/signup", (request, response) -> {
            System.out.println("made a account");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            String email = request.queryParams("email");
            String mcUser = request.queryParams("mcUser");
            accountUtil.createAccount(username, password, email, mcUser);

            return "OK";
        });
        get("/logIn", (request, response) -> {
            String username = request.queryParams("username");
            String password = request.queryParams("password");

            if (accountUtil.verifyAccount(username, password)) {
                String uuid = accountSessions.getNewUUID(username);
                return uuid;
            }
            return false;
        });
        get("/getInfo", (request, response) -> {
            String uuid = request.queryParams("UUID");
            if (accountSessions.getUserInfo(uuid) != null) {
                return accountSessions.getUserInfo(uuid);
            } else {
                return new ErrorClass("Account DataBase Malfunction, Please Try Again", 2);
            }
        });
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
