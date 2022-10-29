package com.slack.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonUtils {

    public static String readProp(String key){

        File propFile=new File("src/test/resources/application.properties");
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream(propFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       return  properties.getProperty(key);
    }


}
