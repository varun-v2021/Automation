package com.web.app.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.web.app.automation.config.Configuration;
import com.web.app.automation.controller.AndroidController;
import com.web.app.automation.controller.CoreController;
import com.web.app.automation.interfaces.VideoService;
import com.web.app.automation.interfaces.VideoService.serviceType;
import com.web.app.automation.listeners.EventListenerImpl;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;
import com.web.app.automation.services.VideoServiceImpl;
import com.web.app.automation.test.AbstractTestBase;

import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.RemoteFile;

public class VideoWorkerThread extends Thread {
	private VideoService.serviceType type;

	public VideoWorkerThread(VideoService.serviceType sType) {
		// TODO Auto-generated constructor stub
		type = sType;
	}

	public void run() {
		EventListenerImpl listener = new EventListenerImpl();
		try {
			synchronized (listener) {

				if (type == serviceType.RECORDING) {
					startVideoRecording().waitFor();
				} else if (type == serviceType.STREAMING) {
					startVideoStreaming().waitFor();
				}

				/*
				 * System.out.println(
				 * "@@@@@@@@@@@ VideoWorkerThread called @@@@@@@"); DateFormat
				 * dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss"); Date
				 * date = new Date(); String command =
				 * "adb shell screenrecord /sdcard/testvideo.mp4"; // + /*
				 * dateFormat.format(date) +
				 */ // ".mp4";
				// VideoServiceImpl.getInstance().startVideoRecorder();
				/*
				 * Process p =
				 * AndroidController.getInstance().getProcessHandle();
				 * System.out.println(
				 * "@@@@@@@@@@@ VideoWorkerThread executing command @@@@@@@"); p
				 * = Runtime.getRuntime().exec(command); System.out.println(
				 * "@@@@@@@@@@@ VideoWorkerThread waiting for command to end @@@@@@@"
				 * ); p.waitFor();
				 */
				Logger.write("end video capturing ", LogLevel.INFO);
				String command = "pkill -INT screenrecord";
				Process p;
				p = Runtime.getRuntime().exec(command);
				Logger.write("storing video output file", LogLevel.INFO);
				JadbConnection jadb = new JadbConnection();
				List<JadbDevice> devices = jadb.getDevices();
				for (JadbDevice device : devices) {
					device.pull(new RemoteFile("/sdcard/testvideo.mp4"),
							new File(Configuration.VIDEO_OUTPUT_DIR + "testvideo2.mp4"));
					/*
					 * device.pull(new RemoteFile(videoFileLocation +
					 * videoFilename), new File("/Users/VVenkatesh/Downloads/" +
					 * videoFilename));
					 */
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Process startVideoStreaming() {
		Logger.write("start streaming video ", LogLevel.INFO);
		String command = "adb shell screenrecord --output-format=h264 - | ffplay -";
		Process p = AndroidController.getInstance().getProcessHandle();
		try {
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public Process startVideoRecording() {
		Logger.write("start recording video ", LogLevel.INFO);
		String command = "adb shell screenrecord /sdcard/testvideo"
				+ /* dateFormat.format(date) + */ ".mp4";
		Process p = AndroidController.getInstance().getProcessHandle();
		try {
			p = Runtime.getRuntime().exec(command);
			// startVideoStreaming();
			// p.waitFor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
}