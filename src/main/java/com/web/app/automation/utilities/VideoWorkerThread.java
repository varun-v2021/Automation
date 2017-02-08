package com.web.app.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.xsavikx.androidscreencast.Main;
import com.web.app.automation.config.Configuration;
import com.web.app.automation.controller.AndroidController;
import com.web.app.automation.interfaces.VideoService;
import com.web.app.automation.interfaces.VideoService.serviceType;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;

import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.RemoteFile;

public class VideoWorkerThread extends Thread {
	private VideoService.serviceType type;
	private Object lockObj;
	public static boolean recTerm = false;
	public static boolean strTerm = false;

	public VideoWorkerThread(VideoService.serviceType sType, Object obj) {
		// TODO Auto-generated constructor stub
		type = sType;
		lockObj = obj;
	}

	public void run() {
		try {
			synchronized (lockObj) {
				if (type == serviceType.RECORDING) {
					/*
					 * Note: Waiting on the process to gracefully complete
					 * itself, else the video written will be corrupted
					 */
					startVideoRecording().waitFor(2,TimeUnit.MINUTES);
					DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
					Date date = new Date();
					JadbConnection jadb = new JadbConnection();
					List<JadbDevice> devices = jadb.getDevices();
					for (JadbDevice device : devices) {
						device.pull(new RemoteFile("/sdcard/testvideo.mp4"), new File(
								Configuration.VIDEO_OUTPUT_DIR + "testvideo-" + dateFormat.format(date) + ".mp4"));
					}
				} else if (type == serviceType.STREAMING) {
					startVideoStreaming().waitFor(2,TimeUnit.MINUTES);
				}

				shutdownADBShellService(type);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (recTerm && strTerm) {
			Logger.write("========================================== ", LogLevel.INFO);
			Logger.write("Terminated session   ", LogLevel.INFO);
			Logger.write("========================================== ", LogLevel.INFO);
		}
	}

	public void shutdownADBShellService(serviceType sType) throws IOException {
		String command = "pkill -INT screenrecord";
		Process p;
		p = Runtime.getRuntime().exec(command);
		if (sType == serviceType.RECORDING) {
			Logger.write("Video recording service shutdown", LogLevel.INFO);
			recTerm = true;
		} else if (sType == serviceType.STREAMING) {
			Logger.write("Video streaming service shutdown", LogLevel.INFO);
			strTerm = true;
		}
	}

	public Process startVideoStreaming() throws IOException {
		Logger.write("start streaming video ", LogLevel.INFO);
		Main.startScreenCast(null);
		// String command = "sh echo `adb shell screenrecord
		// --output-format=h264 - | ffplay -`";
		String command = "sh " + Configuration.VIDEO_STREAMING_SCRIPT;
		Process p = AndroidController.getInstance().getProcessHandle();
		try {
			p = Runtime.getRuntime().exec(command);
			lockObj.wait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public Process startVideoRecording() {
		Logger.write("start recording video ", LogLevel.INFO);
		String command = "adb shell screenrecord /sdcard/testvideo" + ".mp4";
		Process p = AndroidController.getInstance().getProcessHandle();
		try {
			p = Runtime.getRuntime().exec(command);
			lockObj.wait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
}