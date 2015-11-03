package com.ui.service.drivers;
import com.test.AppiumScriptHandler;
import com.util.properties.PropertiesHandlerImpl;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by asih on 25/02/2015.
 */
public class CapabilitiesBuilder {


    private static final CapabilitiesBuilder CAPS_BUILDER_INSTANCE = new CapabilitiesBuilder();

    private static final Logger logger = Logger.getLogger(CapabilitiesBuilder.class);

    private Properties appiumDriverProps;

    private String capFilePath;
    private Drivers driver;
    private AppiumScriptHandler.Machine machine;

    public static CapabilitiesBuilder getInstance() {
        return CAPS_BUILDER_INSTANCE;
    }

    private CapabilitiesBuilder(){

    }

    public DesiredCapabilities getCapabilities(String capFilePath, Drivers driver, AppiumScriptHandler.Machine machine){
        this.capFilePath = capFilePath;
        this.driver = driver;
        this.machine = machine;
        return new DesiredCapabilities(getCapsMap());
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getCapsMap(){
        Map<String, String> caps = new HashMap<>((Map)
                PropertiesHandlerImpl.getInstance().parseFromJar(
                                capFilePath + Constants.CAPS_PROPERTY_FILE_PATH));
        if(driver == Drivers.ANDROID) {
            caps.put(Constants.DESIRED_CAP_APP_KEY, getAppAbsolutePath());
        }
        return caps;
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getCapsMapNotFromJar(){
        Map<String, String> caps = new HashMap<>((Map)
                        PropertiesHandlerImpl.getInstance().parse(
                        capFilePath + Constants.CAPS_PROPERTY_FILE_PATH));
        if(driver == Drivers.ANDROID) {
            caps.put(Constants.DESIRED_CAP_APP_KEY, getAppAbsolutePath());
        }
        return caps;
    }

    private String getAppAbsolutePath(){
        File appDir = null;
        appiumDriverProps = getAppDriverProps(capFilePath);
        appDir = new File(appiumDriverProps.getProperty(Constants.APPIUM_DRIVER_PROP_APP_DIR_WIN_KEY));
        File app = new File(appDir, appiumDriverProps.getProperty(Constants.APPIUM_DRIVER_PROP_APP_NAME_KEY));
//        int c = 0;
        return app.getAbsolutePath();
    }

    public Properties getAppDriverProps(String capFilePath){
        return PropertiesHandlerImpl.getInstance().parseFromJar(capFilePath + Constants.APPIUM_DRIVER_PROPERTY_FILE_PATH);
//        return PropertiesHandlerImpl.getInstance().parse(capFilePath + Constants.APPIUM_DRIVER_PROPERTY_FILE_PATH);
    }

    public Properties getAppDriverProps(){
        return appiumDriverProps;
    }

    private static class Constants{

        private static final String CAPS_PROPERTY_FILE_PATH = "capabilities.properties";
        private static final String APPIUM_DRIVER_PROPERTY_FILE_PATH = "native_app.properties";

        private static final String DESIRED_CAP_APP_KEY = "app";
        private static final String APPIUM_DRIVER_PROP_APP_DIR_WIN_KEY = "appDirWindows";
        private static final String APPIUM_DRIVER_PROP_APP_DIR_MAC_KEY = "appDirMac";

        private static final String APPIUM_DRIVER_PROP_APP_NAME_KEY = "appName";
    }
}
