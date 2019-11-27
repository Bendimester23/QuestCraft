package net.questcraft;


import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class Main {
    public static void main (String [] args) {
        staticFiles.location("/public");
        get("/hello", (request, response) -> "HamBurger Test");
    }
}
