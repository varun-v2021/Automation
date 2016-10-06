package com.web.app.automation.testng;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.web.app.automation.test.CloseTourOnStartupTest;
import com.web.app.automation.test.LiveTVTest;
import com.web.app.automation.test.LoginTest;

public class TestNGHandler implements Runnable {
    /*
     * Entry Point for TestNG framework , AbstractTestBase class will handle
     * initialization and tear down of appium server before and after tests
     */
    public void initTest() {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { /* CloseTourOnStartupTest.class */ LoginTest.class /*
                                                                                                * ,
                                                                                                * LiveTVTest
                                                                                                * .class
                                                                                                */ });
        testng.addListener(tla);
        // This function is not the same as thread implementation
        testng.run();
    }

    // Overriding thread implementation
    public void run() {
        // TODO Auto-generated method stub
        initTest();
    }

}
