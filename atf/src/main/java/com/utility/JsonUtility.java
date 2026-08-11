package com.utility;

import java.io.File;
import java.io.FileReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.pojo.Config;
import com.pojo.Environment;

public class JsonUtility {

    public static Environment readJson(Env env) {
        Environment qaEnvironment = null;
        Gson gson = new Gson();
        File jsonFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\config\\config.json");
          try (FileReader reader = new FileReader(jsonFile)) {
            Config config = gson.fromJson(reader, Config.class);
             qaEnvironment = config.getEnvironment().get(env.name());

              if (qaEnvironment == null) {
            throw new RuntimeException("Environment " + env + " not found.");
        }
            System.out.println("QA Environment URL: " + qaEnvironment.getUrl());
             return qaEnvironment;
            
          
        } catch (java.io.IOException e) {
            e.printStackTrace();
             throw new RuntimeException("Failed to read config.json", e);
        }
       
    }

     public static void main(String[] args) {
            //String url = ge;
            System.out.println("URL from JSON: " + readJson(Env.QA).getMaxRetryCount());
        }
}
