package com.web.app.automation.server;

import java.io.File;

import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServer implements Runnable {
	static String Appium_Node_Path = "/usr/local/bin/node"; // which node
	static String Appium_JS_Path = "/usr/local/bin/appium"; // which appium
	static AppiumDriverLocalService service;
	static String service_url;

	public static void start() throws Exception {

		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723)
				.usingDriverExecutable(new File(Appium_Node_Path)).withAppiumJS(new File(Appium_JS_Path)));
		service.start();
		Logger.write("Appium server started", LogLevel.INFO);
		Thread.sleep(25000);
		service_url = service.getUrl().toString();
	}

	public static void stop() {
		Logger.write("Appium server stopping", LogLevel.INFO);
		service.stop();
		Logger.write("Appium server stopped", LogLevel.INFO);
		Logger.write("Terminating service(s) in few moments ... ", LogLevel.INFO);
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			Logger.write("Appium server starting on port: 4723", LogLevel.INFO);
			start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
