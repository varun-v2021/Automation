package com.web.app.automation.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Reporter;

import com.web.app.automation.config.Configuration;
import com.web.app.automation.utilities.PropertiesUtility;

public class Logger {

    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS| ");

    public static void write(String message, LogLevel level) {
        message = "[AUTOMATION] " + dateFormat.format(new Date()) + message;
        Reporter.log(message + "\t" + level + "<br/>");
        System.out.println(message);
        /*
         * if (Configuration.logToStandardOut && level.toInt() <=
         * LogLevel.getUserLogLevel()) { System.out.println(message); }
         */
    }

}
