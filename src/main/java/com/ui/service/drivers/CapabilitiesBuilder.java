package com.ui.service.drivers;

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

    public static Properties appiumDriverProps = null;

    public static CapabilitiesBuilder getInstance() {
        return CAPS_BUILDER_INSTANCE;
    }

    private CapabilitiesBuilder(){

    }

    public DesiredCapabilities getCapabilities(String capFilePath){
        return new DesiredCapabilities(getCapsMap(capFilePath));

    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getCapsMap(String capFilePath){
        Map<String, String> caps = new HashMap<>((Map)
                PropertiesHandlerImpl.getInstance().
                        parse(capFilePath + Constants.CAPS_PROPERTY_FILE_PATH));
        caps.put(Constants.DESIRED_CAP_APP_KEY, getAppAbsolutePath(capFilePath));
        return caps;
    }

    private String getAppAbsolutePath(String capFilePath){
        Properties appiumDriverProps =
                PropertiesHandlerImpl.getInstance().
                        parse(capFilePath + Constants.APPIUM_DRIVER_PROPERTY_FILE_PATH);
        File appDir = new File(appiumDriverProps.getProperty(Constants.APPIUM_DRIVER_PROP_APP_DIR_KEY));
        File app = new File(appDir, appiumDriverProps.getProperty(Constants.APPIUM_DRIVER_PROP_APP_NAME_KEY));
        return app.getAbsolutePath();
    }

    private static class Constants{

        private static final String CAPS_PROPERTY_FILE_PATH = "capabilities.properties";
        private static final String APPIUM_DRIVER_PROPERTY_FILE_PATH = "appium_driver.properties";

        private static final String DESIRED_CAP_APP_KEY = "app";
        private static final String APPIUM_DRIVER_PROP_APP_DIR_KEY = "appDir";
        private static final String APPIUM_DRIVER_PROP_APP_NAME_KEY = "appName";
    }
}
