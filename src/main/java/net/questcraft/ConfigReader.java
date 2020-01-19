package net.questcraft;


import java.io.*;

import java.util.Properties;
import java.util.Set;

public class ConfigReader {
    public String readPropertiesFile(String property) {
        try {
            InputStream input = new FileInputStream(".\\config.properties");
            Properties prop = new Properties();
            prop.load(input);
            Set<Object> objects = prop.keySet();
            for (Object key: objects) {
                String keyStr = (String) key;
                String value = prop.getProperty(keyStr);
                if (keyStr.equalsIgnoreCase(property)) {
                    return value;
                }
            }
        }catch (IOException ex) {

        }
        return null;
    }
    public boolean getTesting() {
        try {
            InputStream input = new FileInputStream("C:\\Users\\Durgan\\IdeaProjects\\QuestCraft\\config.properties");
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("testing").equalsIgnoreCase("true");

        }catch (IOException ex) {
        }
        return false;
    }
}
