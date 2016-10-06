package com.web.app.automation.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.web.app.automation.config.Configuration;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;
import com.web.app.automation.services.VideoServiceImpl;
import com.web.app.automation.utilities.PropertiesUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidController extends CoreController {

    private static AndroidController controllerInstance = null;
    private static AndroidDriver driver;
    private static Process proc;

    DesiredCapabilities capabilities = new DesiredCapabilities();

    @SuppressWarnings("rawtypes")
    private AndroidController() {
        // TODO Auto-generated constructor stub
    }

    public static AndroidController getInstance() {
        if (controllerInstance == null) {
            controllerInstance = new AndroidController();
        }
        return controllerInstance;
    }

    public Process getProcessHandle() {
        return proc;
    }

    public AndroidDriver<WebElement> getDriver() throws Exception {
        initDriver();
        // initVideoRecording();
        ((StartsActivity) driver).startActivity(PropertiesUtility.getProperty(Configuration.ANDROID_APP_PACKAGE),
                PropertiesUtility.getProperty(Configuration.ANDROID_APP_ACTIVITY));
        return driver;
    }

    public void initDriver() {
        Logger.write("Initializing android driver ", LogLevel.INFO);
        try {
            initCapabilities();
            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        appiumDriver = driver;
    }

    @Override
    public void initCapabilities() {
        Logger.write("Initializing driver capabilities", LogLevel.INFO);
        File appDir = new File(Configuration.APP_DIR);
        File app = new File(appDir, PropertiesUtility.getProperty(Configuration.APP_NAME));

        // mandatory capabilities
        capabilities.setCapability("platformVersion",
                PropertiesUtility.getProperty(Configuration.ANDROID_PLATFORM_VERSION));
        capabilities.setCapability("platformName", PropertiesUtility.getProperty(Configuration.ANDROID_PLATFORM_NAME));
        capabilities.setCapability("appActivity", PropertiesUtility.getProperty(Configuration.ANDROID_APP_ACTIVITY));
        capabilities.setCapability("appPackage", PropertiesUtility.getProperty(Configuration.ANDROID_APP_PACKAGE));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
                PropertiesUtility.getProperty(Configuration.ANDROID_DEVICE_NAME));
        // capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
        // "YT910YVNUK"); //03157df341542728 //5.0.2 //6.0.1
        // capabilities.setCapability("device", "D5803");
        // other caps
        capabilities.setCapability("app", app.getAbsolutePath());
    }
}
