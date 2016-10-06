package com.web.app.automation.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class IOSController extends CoreController {

    private static IOSController controllerInstance = null;
    private IOSDriver<WebElement> driver;

    DesiredCapabilities capabilities = new DesiredCapabilities();

    @SuppressWarnings("rawtypes")
    private IOSController() {
        // TODO Auto-generated constructor stub
        try {
            initCapabilities();
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            appiumDriver = driver;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static IOSController getInstance() {
        if (controllerInstance == null)
            controllerInstance = new IOSController();
        return controllerInstance;
    }

    public IOSDriver<WebElement> getDriver() {
        return driver;
    }

    @Override
    public void initCapabilities() {
        // TODO Auto-generated method stub
        capabilities = null;
    }

}
