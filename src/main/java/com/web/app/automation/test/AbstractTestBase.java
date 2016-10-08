package com.web.app.automation.test;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.web.app.automation.controller.CoreController;
import com.web.app.automation.interfaces.ControllerService;
import com.web.app.automation.interfaces.VideoService.serviceType;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;
import com.web.app.automation.server.AppiumServer;
import com.web.app.automation.utilities.VideoWorkerThread;

import io.appium.java_client.AppiumDriver;

public abstract class AbstractTestBase {

	ControllerService cService;
	AppiumDriver<WebElement> driver;
	static Object lockObj = new Object();

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

			// Startup video services
			VideoWorkerThread recThread = new VideoWorkerThread(serviceType.RECORDING, lockObj);
			recThread.start();
			VideoWorkerThread strThread = new VideoWorkerThread(serviceType.STREAMING, lockObj);
			strThread.start();

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
			synchronized (lockObj) {
				lockObj.notifyAll();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
