package com.web.app.automation.interfaces;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public interface ControllerService {
    public AppiumDriver<WebElement> getController();
}
