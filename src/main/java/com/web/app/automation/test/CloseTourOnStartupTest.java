package com.web.app.automation.test;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.web.app.automation.config.Configuration;
import com.web.app.automation.controller.CoreController;
import com.web.app.automation.interfaces.ControllerService;
import com.web.app.automation.interfaces.EventListener;
import com.web.app.automation.listeners.EventListenerImpl;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;
import com.web.app.automation.services.ControllerServiceImpl;
import com.web.app.automation.utilities.PropertiesUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;

@Test(groups = { "test1" }, priority = 1)
public class CloseTourOnStartupTest extends AbstractTestBase {

    public void closeTourOnStartUpTest() throws Exception {
        try {
            Logger.write("<------------ CloseTourOnStartupTest  START  ------------>", LogLevel.INFO);
            // https://github.com/cbeust/testng/issues/313
            // ServerApp.startAppiumServer();

            EventListener eventCallback = new EventListenerImpl();
            // System.out.println("########## closeTourOnStartUpTest
            // CoreController.getController() called ######");
            // driver = CoreController.getController();
            // AppiumDriver<WebElement> driver = CoreController.getController();
            /*
             * ControllerService cService = new ControllerServiceImpl();
             * AppiumDriver<WebElement> driver = cService.getController();
             */
            if (driver == null) {
                if (CoreController.appiumDriver == null) {
                } else {
                    driver = CoreController.appiumDriver;
                }
            }
            Thread.sleep(10000);
            WebElement element = driver.findElementById("com.TWCableTV:id/closeTourButton");
            element.click();
            Thread.sleep(2000);
            Logger.write("<------------ CloseTourOnStartupTest  END  ------------>", LogLevel.INFO);
            this.register(eventCallback);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void register(EventListener event) {
        event.testComplete("closeTourOnStartUpTest");
    }
}
