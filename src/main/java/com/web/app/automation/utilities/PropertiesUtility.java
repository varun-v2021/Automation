package com.web.app.automation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import com.web.app.automation.config.Configuration;

public class PropertiesUtility extends Utility {

    static Properties prop = new Properties();
    static InputStream input = null;

    public static String getProperty(String key) {
        try {
            input = new FileInputStream(Configuration.ANDROID_PROP_FILENAME);
            prop.load(input);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return prop.getProperty(key);
    }
}
