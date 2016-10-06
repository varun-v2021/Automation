package com.web.app.automation.test;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.web.app.automation.interfaces.EventListener;
import com.web.app.automation.listeners.EventListenerImpl;

/*@Test(groups = { "test1" },dependsOnMethods = { "loginTest" }, alwaysRun = true,suiteName="testSuite")*/
public class LiveTVTest extends AbstractTestBase {

    EventListener eventCallback = new EventListenerImpl();

    public void liveTVTest() {
        try {
            System.out.println("<------------ LiveTVTest  START  ------------>");
            Thread.sleep(10000);
            WebElement element = driver.findElementById("com.TWCableTV:id/homeLiveTv");
            element.click();
            Thread.sleep(2000);
            System.out.println("<------------ LiveTVTest  END  ------------>");
            this.register(eventCallback);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void register(EventListener event) {
        event.testComplete("LiveTVTest");
    }

}
