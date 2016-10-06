package com.web.app.automation.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.web.app.automation.controller.CoreController;
import com.web.app.automation.interfaces.EventListener;
import com.web.app.automation.listeners.EventListenerImpl;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

@Test(suiteName = "testSuite", groups = {
        "test1" }, /*
                    * , dependsOnMethods = { "closeTourOnStartUpTest" },
                    * priority = 2
                    */alwaysRun = true)
public class LoginTest extends AbstractTestBase {

    EventListener eventCallback = new EventListenerImpl();

    public void loginTest() throws Exception {
        try {
            Logger.write("<------------ LoginTest  START  ------------>", LogLevel.INFO);
            // https://github.com/cbeust/testng/issues/313

            Thread.sleep(10000);
            WebElement element = driver.findElementById("com.TWCableTV:id/closeTourButton");
            element.click();
            Thread.sleep(2000);
            // login();
            Logger.write("Enter valid username and password into login fields", LogLevel.INFO);
            if (driver == null) {
                if (CoreController.appiumDriver != null) {
                    driver = CoreController.appiumDriver;
                }
            }
            driver.findElementById("username").click();
            // driver.findElement(By.id("username")).click();
            driver.getKeyboard().sendKeys("system5");
            driver.findElementById("com.TWCableTV:id/password").click();
            driver.getKeyboard().sendKeys("5ctgtest05");
            WebElement elem = driver.findElement((By) By.id("signinButton"));
            driver.tap(1, elem, 1);
            Thread.sleep(5000);
            driver.findElement(By.id("com.TWCableTV:id/agreeButton")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("android:id/button1")).click();
            Logger.write("<------------ LoginTest  END  ------------>", LogLevel.INFO);
            this.register(eventCallback);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void register(EventListener event) throws Exception {
        event.testComplete("LoginTest");
    }
}
