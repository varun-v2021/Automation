package com.web.app.automation.config;

import com.web.app.automation.utilities.PropertiesUtility;

public class Configuration {

    public static String APP_DIR = "/Users/VVenkatesh/Documents/workspace/AutomationService/apps";
    public static String ANDROID_PROP_FILENAME = "/Users/VVenkatesh/Documents/workspace/AutomationService/properties/android.properties";
    public static String IOS_PROP_FILENAME = "/Users/VVenkatesh/Documents/workspace/AutomationService/properties/ios.properties";
    PropertiesUtility propUtilObj = new PropertiesUtility();

    public static String APP_NAME = "appName";
    public static String ANDROID_APP_ACTIVITY = "appUtility";
    public static String ANDROID_APP_PACKAGE = "appPackage";
    public static String ANDROID_DEVICE_NAME = "deviceName"; // "YT910YVNUK"
    public static String ANDROID_PLATFORM_NAME = "platformName";
    public static String ANDROID_PLATFORM_VERSION = "platformVersion";
    public static final boolean logToStandardOut = true;
    public static String USER_LOG_LEVEL = "logLevel";

    public enum platformType {
        NONE, ANDROID, IOS
    };

    public enum deviceType {
        NONE, MOBILE, TABLET, BROWSER
    };

}
