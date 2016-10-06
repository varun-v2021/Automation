package com.web.app.automation.utilities;

import com.web.app.automation.config.Configuration;
import com.web.app.automation.config.Configuration.deviceType;
import com.web.app.automation.config.Configuration.platformType;

public class Utility {
    public static String[] parseURL(String str) {
        /*String URL = str.getRequestURI();
        String[] URLParts = URL.split("/");
        return URLParts;*/
        return null;
    }

    public static platformType getPlatformType(String str) {
        Configuration.platformType retVal = Configuration.platformType.NONE;
        if (str.equals("android")) {
            retVal = Configuration.platformType.ANDROID;
        } else if (str.equals("ios")) {
            retVal = Configuration.platformType.IOS;
        }
        return retVal;
    }

    public static deviceType getDeviceType(String str) {
        Configuration.deviceType retVal = Configuration.deviceType.NONE;
        if (str.equals("mobile")) {
            retVal = Configuration.deviceType.MOBILE;
        } else if (str.equals("tablet")) {
            retVal = Configuration.deviceType.TABLET;
        } else if (str.equals("browser")) {
            retVal = Configuration.deviceType.BROWSER;
        }
        return retVal;
    }

    public static String getTestsToExecute(String str) {
        return str;
    }
}
