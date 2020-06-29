package net.questcraft;


import java.io.*;

import java.util.Properties;
import java.util.Set;

public class ConfigReader {
    public String readPropertiesFile(String property) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        try {
            System.out.println("Trying");
            InputStream input = new FileInputStream( System.getProperty("user.dir") + "/config.properties");
            System.out.println("Got InputStream");
            Properties prop = new Properties();
            System.out.println("Got Prop");
            prop.load(input);
            Set<Object> objects = prop.keySet();
            for (Object key: objects) {

                String keyStr = (String) key;
                System.out.println("KeyStr is: " + keyStr);
                String value = prop.getProperty(keyStr);
                System.out.println("Value is: " + value);
                if (keyStr.equalsIgnoreCase(property)) {
                    return value;
                }
            }
        }catch (IOException ex) {
            System.out.println("Got IOExeption ex: " + ex.getMessage());
        }
        return null;
    }
    public boolean getTesting() {
        try {
            InputStream input = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
            Properties prop = new Properties();
            prop.load(input);
            boolean testing = prop.getProperty("testing").equalsIgnoreCase("true");
            System.out.println("TESTING IS " + testing + " AGAIN HERE IS TESTING <---------------------------------");
            return testing;

        }catch (IOException ex) {
            System.out.println("Got IOExeption ex: " + ex.getMessage());
        }catch (NullPointerException e) {
            System.out.println("Null pointer. "+e.getMessage());
        }
        return false;
    }
}
