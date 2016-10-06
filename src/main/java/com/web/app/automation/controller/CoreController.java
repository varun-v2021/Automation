package com.web.app.automation.controller;

import org.openqa.selenium.WebElement;

import com.web.app.automation.config.Configuration;
import com.web.app.automation.interfaces.ControllerService;
import com.web.app.automation.services.ControllerServiceImpl;

import io.appium.java_client.AppiumDriver;

public abstract class CoreController {

    public static AppiumDriver<WebElement> appiumDriver;

    public abstract void initCapabilities();

    private static Configuration.deviceType gDevice;
    private static Configuration.platformType gPlatform;
    private static String gTestSuite;

    public static Configuration.deviceType getDevice() {
        return gDevice;
    }

    public static void setDevice(Configuration.deviceType device) {
        gDevice = device;
    }

    public static Configuration.platformType getPlatform() {
        return gPlatform;
    }

    public static void setPlatform(Configuration.platformType platform) {
        gPlatform = platform;
    }

    public static String getTestSuite() {
        return gTestSuite;
    }

    public static void setTestSuite(String testSuite) {
        gTestSuite = testSuite;
    }

    public static AppiumDriver<WebElement> getController() {
        ControllerService cService = new ControllerServiceImpl();
        AppiumDriver<WebElement> driver = cService.getController();
        return driver;
    }

}
