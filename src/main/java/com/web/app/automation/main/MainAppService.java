package com.web.app.automation.main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.web.app.automation.controller.CoreController;
import com.web.app.automation.interfaces.ControllerService;
import com.web.app.automation.interfaces.EventListener;
import com.web.app.automation.listeners.EventListenerImpl;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;
import com.web.app.automation.server.AppiumServer;
import com.web.app.automation.services.ControllerServiceImpl;
import com.web.app.automation.testng.TestNGHandler;
import com.web.app.automation.utilities.Utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

//http://localhost:8080/automator/rest/start/android/mobile/all
@Path("/start")
public class MainAppService {

	private static void dumpVars(Map<String, ?> m) {
		List<String> keys = new ArrayList<String>(m.keySet());
		Collections.sort(keys);
		for (String k : keys) {
			System.out.println(k + " : " + m.get(k));
		}
	}

	@GET
	@Path("/{platform}/{device}/{suite}")
	public Response startServer(@PathParam("platform") String platform, @PathParam("device") String device,
			@PathParam("suite") String suite) {

		String output = "Automation service [Jersey] - received: platform = " + platform + " device = " + device
				+ " suite = " + suite;
		try {
			Logger.write("========================================== ", LogLevel.INFO);
			Logger.write("Initiating session   ", LogLevel.INFO);
			Logger.write("========================================== ", LogLevel.INFO);
			Logger.write("\t Environment variables \t", LogLevel.INFO);
			Logger.write("\t" + System.getenv() + "\t", LogLevel.INFO);
			// dumpVars(System.getenv());

			CoreController.setDevice(Utility.getDeviceType(device));
			CoreController.setPlatform(Utility.getPlatformType(platform));
			CoreController.setTestSuite(Utility.getTestsToExecute(suite));

			TestNGHandler testObj = new TestNGHandler();
			Thread testNGThread = new Thread(testObj);
			testNGThread.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/stop")
	public Response stopServer(@PathParam("param") String msg) {
		String output = "Automation service [Jersey] : " + msg;
		return Response.status(200).entity(output).build();
	}
}
