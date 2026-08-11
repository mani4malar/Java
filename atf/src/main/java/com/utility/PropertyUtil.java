package com.utility;
import com.constants.Env;
public abstract class PropertyUtil {

    public static String getProperty(Env env, String key) {
        String value = null;
        String propertiesFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\config\\" + env + ".properties";
        java.util.Properties properties = new java.util.Properties();
        try (java.io.FileInputStream fis = new java.io.FileInputStream(propertiesFilePath)) {
            properties.load(fis);
            value = properties.getProperty(key).toUpperCase();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getProperty(String key) {
       return getProperty(Env.QA, key);
    }
   
}
