package com.ui.service.drivers;


import com.ui.service.AppiumService;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by asih on 22/02/2015.
 */
public enum AppiumDrivers {

    ANDROID, IOS;

    private static final Logger logger = Logger.getLogger(AppiumDrivers.class);

    public static final String START_URL = "https://staging.feex.co.il/";
    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";


    private volatile static AppiumDriver driver = null;

    public static Properties props = null;

    private static String browserType;


    public static AppiumDriver setDriver(AppiumDrivers driverType, String testDir) throws MalformedURLException, Exception {
        logger.info("Trying to set " + browserType + " browser to driver");
        DesiredCapabilities caps = CapabilitiesBuilder.getInstance().getCapabilities(testDir); // how to get???
        switch (driverType) {
            case ANDROID:
                driver = createAndroidDriver(caps);
                if(driver != null){
                    printAndroidSuccessCreationMsg();
                }
                break;
            case IOS:
                driver = createIOSDriver(caps);
                if(driver != null){
                    printIOSSuccessCreationMsg();
                }
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    private synchronized static AppiumDriver createAndroidDriver(DesiredCapabilities caps) throws Exception {

        return new AndroidDriver(new URL(APPIUM_SERVER_URL), caps);
    }

    private static void printAndroidSuccessCreationMsg(){
        logger.info("====================================================================");
        logger.info("============== Created New Android Driver ================");
        logger.info("====================================================================");
        logger.info("========== Starting New Test In Internet Explorer Driver ===========");
        logger.info("====================================================================");
        logger.debug("Internet explorer driver was created");
    }

    synchronized static AppiumDriver createIOSDriver(DesiredCapabilities caps) throws Exception {
        return new IOSDriver(new URL(APPIUM_SERVER_URL), caps);
    }

    private static void printIOSSuccessCreationMsg(){
        logger.info("====================================================================");
        logger.info("============== Created New IOS Driver ================");
        logger.info("====================================================================");
        logger.info("========== Starting New Test In Internet Explorer Driver ===========");
        logger.info("====================================================================");
        logger.debug("Internet explorer driver was created");
    }

    public static void startDriverByType(AppiumDrivers browser, String testDir) throws Exception {
        switch (browser) {
            case ANDROID:
                startAndroidDriver(testDir);
                break;
            case IOS:
                startIOSDriver(testDir);
                break;

            default:
                startAndroidDriver(testDir);
        }

    }


    private static void startAndroidDriver(String testDir) throws Exception {
        AppiumService.getInstance().setDriver(AppiumDrivers.ANDROID, testDir);
        AppiumService.getInstance().openBrowser(START_URL);
    }

    private static void startIOSDriver(String testDir) throws Exception {
        AppiumService.getInstance().setDriver(AppiumDrivers.IOS, testDir);
        AppiumService.getInstance().openBrowser(START_URL);
    }

    public static void close() throws Exception {
        logger.info("Test on browser " + SeleniumDrivers.getBrowserType() + " finished succssesfully");
        AppiumService.getInstance().closeBrowser();
        AppiumService.getInstance().closeDriver();

    }

}
