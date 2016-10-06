package com.web.app.automation.log;

import com.web.app.automation.config.Configuration;
import com.web.app.automation.utilities.PropertiesUtility;

public class LogLevel {
    protected final int value;
    public static LogLevel CRITICAL = new LogLevel(0);
    public static LogLevel ERROR = new LogLevel(1);
    public static LogLevel WARN = new LogLevel(2);
    public static LogLevel INFO = new LogLevel(3);
    public static LogLevel DEBUG = new LogLevel(4);

    LogLevel(int value) {
        this.value = value;
    }

    public static LogLevel getUserLogLevel() {
        String usrlevel = PropertiesUtility.getProperty(Configuration.USER_LOG_LEVEL);
        LogLevel retVal;
        switch (usrlevel) {
        case "DEBUG":
            retVal = DEBUG;
            break;
        case "INFO":
            retVal = INFO;
            break;
        case "WARN":
            retVal = WARN;
            break;
        case "ERROR":
            retVal = ERROR;
            break;
        case "CRITICAL":
            retVal = CRITICAL;
            break;
        default:
            retVal = INFO;
        }
        return retVal;
    }

    public int toInt() {
        return value;
    }
}
