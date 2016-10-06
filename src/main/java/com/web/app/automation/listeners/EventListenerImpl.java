package com.web.app.automation.listeners;

import java.io.IOException;

/*import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;*/

import com.web.app.automation.interfaces.EventListener;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;
import com.web.app.automation.services.VideoServiceImpl;

public class EventListenerImpl extends Thread implements EventListener {

    public void testComplete(String testName) {
        // TODO Auto-generated method stub
        notify();
        asyncCallBack(testName);
    }

    public static void asyncCallBack(String tName) {
        try {
            Logger.write("Test " + tName + " execution completed ... !", LogLevel.INFO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
