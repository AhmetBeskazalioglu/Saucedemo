package com.saucedemo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties;

    static {
        try {
            // okumak istediğimiz dosyanın path i
            String path="configuration.properties";
            // java ile dosya okumak için bu class kullanılır
            FileInputStream input=new FileInputStream(path);
            // properties objesi oluşturuluyor
            properties=new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String  get(String keyName) {
        return properties.getProperty(keyName);
    }
}
