package com.djm.basic.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    //This method reads data from a properties file
    public static String getPropertyValue(String key) {
        String value = null;
        Properties props = new Properties();
        FileInputStream fis = null;
        String filePath = System.getProperty("user.dir") + "/src/main/java/com/djm/basic/resources/url.properties";
        try {
            fis = new FileInputStream(filePath);
            props.load(fis);
            value = props.getProperty(key);
            System.out.println("Retrieved property value for key '" + key + "': " + value);
        } catch (IOException e) {
            System.err.println("Error reading from properties file: " + e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Error closing input stream: " + e.getMessage());
                }
            }
        }
        return value;
    }
     //This method reads data from a properties file
     public static String getDataValue(String key) {
        String value = null;
        Properties props = new Properties();
        FileInputStream fis = null;
        String filePath = System.getProperty("user.dir") + "/src/main/java/com/djm/basic/resources/credentials.properties";
        try {
            fis = new FileInputStream(filePath);
            props.load(fis);
            value = props.getProperty(key);
            System.out.println("Retrieved property value for key '" + key + "': " + value);
        } catch (IOException e) {
            System.err.println("Error reading from properties file: " + e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Error closing input stream: " + e.getMessage());
                }
            }
        }
        return value;
    }
}
