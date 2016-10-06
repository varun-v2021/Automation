package com.web.app.automation.services;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.web.app.automation.controller.AndroidController;
import com.web.app.automation.interfaces.VideoService;
import com.web.app.automation.log.LogLevel;
import com.web.app.automation.log.Logger;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;
import se.vidstige.jadb.RemoteFile;

public class VideoServiceImpl implements VideoService {
    DateFormat dateFormat;
    Date date;
    String videoFilename = "";
    String videoFileLocation = "/sdcard/";

    private static VideoServiceImpl vServiceInstance = null;

    private VideoServiceImpl() {
    }

    public static VideoServiceImpl getInstance() {
        if (vServiceInstance == null)
            vServiceInstance = new VideoServiceImpl();
        return vServiceInstance;
    }

}
