package com.web.app.automation.services;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;

import com.web.app.automation.factory.ControllerFactory;
import com.web.app.automation.interfaces.ControllerService;

import io.appium.java_client.AppiumDriver;

public class ControllerServiceImpl implements ControllerService {

    @SuppressWarnings("unchecked")
    public AppiumDriver<WebElement> getController() {
        // TODO Auto-generated method stub
        AppiumDriver driver = null;
        try {
            ControllerFactory fObj = new ControllerFactory();
            driver = fObj.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

}
