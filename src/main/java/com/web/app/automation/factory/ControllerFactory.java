package com.web.app.automation.factory;

import org.openqa.selenium.WebElement;
import com.web.app.automation.config.Configuration;
import com.web.app.automation.controller.AndroidController;
import com.web.app.automation.controller.CoreController;
import com.web.app.automation.controller.IOSController;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;

import io.appium.java_client.AppiumDriver;

public class ControllerFactory {
    private AppiumDriver<WebElement> driver;

    public AppiumDriver<WebElement> getController() {
        try {
            if (CoreController.getPlatform() == Configuration.platformType.ANDROID) {
                Logger.write("obtaining android controller instance from factory", LogLevel.INFO);
                AndroidController aController = AndroidController.getInstance();
                driver = aController.getDriver();
            } else if (CoreController.getPlatform() == Configuration.platformType.IOS) {
                Logger.write("obtaining ios controller instance from factory", LogLevel.INFO);
                IOSController iController = IOSController.getInstance();
                driver = iController.getDriver();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return driver;
    }
}
