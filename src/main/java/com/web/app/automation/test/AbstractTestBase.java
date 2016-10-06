package com.web.app.automation.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.web.app.automation.config.Configuration;
import com.web.app.automation.controller.AndroidController;
import com.web.app.automation.controller.CoreController;
import com.web.app.automation.interfaces.ControllerService;
import com.web.app.automation.interfaces.VideoService.serviceType;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;
import com.web.app.automation.server.AppiumServer;
import com.web.app.automation.services.ControllerServiceImpl;
import com.web.app.automation.services.VideoServiceImpl;
import com.web.app.automation.utilities.PropertiesUtility;
import com.web.app.automation.utilities.VideoWorkerThread;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;

public abstract class AbstractTestBase {

    ControllerService cService;
    AppiumDriver<WebElement> driver;

    @BeforeSuite
    public void startServer() {
        try {
            Logger.write("Starting appium server process", LogLevel.INFO);
            AppiumServer appium = new AppiumServer();
            Thread appiumServerThread = new Thread(appium);
            appiumServerThread.start();
            appiumServerThread.join();
            driver = CoreController.getController();
            Logger.write("Spawning thread to record test execution", LogLevel.INFO);
            VideoWorkerThread recThread = new VideoWorkerThread(serviceType.RECORDING);
            recThread.start();

            /*
             * VideoWorkerThread strThread = new
             * VideoWorkerThread(serviceType.STREAMING); strThread.start();
             */

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void stopServer() {
        try {
            driver.quit();
            AppiumServer.stop();
            // AndroidController.getInstance().getProcessHandle().waitFor(1,
            // TimeUnit.MINUTES);
            // AndroidController.getInstance().getProcessHandle().destroy();
            // VideoServiceImpl.getInstance().stopVideoRecorder();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void testMethod() {
        System.out.println(">>>>> Abstract class test method");
    }
}
