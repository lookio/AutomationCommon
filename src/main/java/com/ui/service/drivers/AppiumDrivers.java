package com.ui.service.drivers;


import com.ui.service.AppiumService;
import com.util.genutil.GeneralUtils;
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

    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    private static final String IMPLICIT_WAIT = "implicitWait";


    private volatile static AppiumDriver driver = null;

    public static Properties props = null;

    private static String browserType;


    public static AppiumDriver setDriver(AppiumDrivers driverType, String testDir) throws MalformedURLException, Exception {
        logger.info("Trying to set " + driverType.name() + " driver");
        DesiredCapabilities caps = CapabilitiesBuilder.getInstance().getCapabilities(testDir); // how to get???
        if(driver != null){
            throw new NullPointerException("Driver allready exists");
        }
        switch (driverType) {
            case ANDROID:
                driver = createAndroidDriver(caps);
                printAndroidSuccessCreationMsg();

                break;
            case IOS:
                driver = createIOSDriver(caps);
                printIOSSuccessCreationMsg();

        }
//        driver.(10, TimeUnit.SECONDS);
        int wait = new Integer(CapabilitiesBuilder.getInstance().getAppDriverProps().getProperty(IMPLICIT_WAIT)).intValue();
        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
        return driver;
    }

    private synchronized static AppiumDriver createAndroidDriver(DesiredCapabilities caps) throws Exception {

        return new AndroidDriver(new URL(APPIUM_SERVER_URL), caps);
    }

    private static void printAndroidSuccessCreationMsg(){
        logger.info("====================================================================");
        logger.info("============== Created New Android Driver ================");
        logger.info("====================================================================");
        logger.info("========== Starting New Test In Android Driver ===========");
        logger.info("====================================================================");
    }

    synchronized static AppiumDriver createIOSDriver(DesiredCapabilities caps) throws Exception {
        return new IOSDriver(new URL(APPIUM_SERVER_URL), caps);
    }

    private static void printIOSSuccessCreationMsg(){
        logger.info("====================================================================");
        logger.info("============== Created New IOS Driver ================");
        logger.info("====================================================================");
        logger.info("========== Starting New Test In IOS Driver ===========");
        logger.info("====================================================================");
    }

    public static void close()  {
        logger.info("Test on driver finished succssesfully");
        try {
            if(driver != null) {
                AppiumService.getInstance().closeBrowser();
                AppiumService.getInstance().closeDriver();
                setDriver(null);
//                AppiumService.getInstance().closeApp();
            }
        }catch (Exception e){
            GeneralUtils.handleError("Error in close resources", e);
        }
    }

    private static void setDriver(AppiumDriver driver) {
        AppiumDrivers.driver = driver;
    }

}
