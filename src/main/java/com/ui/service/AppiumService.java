package com.ui.service;

import com.ui.service.base.UIService;
import com.util.genutil.GeneralUtils;
import com.ui.page.base.BasePage;
import com.ui.service.drivers.AppiumDrivers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.TouchAction;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by asih on 22/02/2015.
 */
public class AppiumService extends UIService<WebElement, AppiumDriver> {

    private static volatile AppiumDriver driver = null;
    private static final AppiumService APPIUM_SERVICE_INSTANCE = new AppiumService();
    private static final Logger logger = Logger.getLogger(SeleniumService.class);
    private static long waitForPageSourceTimeOutInterval = 500;



    public static AppiumService getInstance() {
        return APPIUM_SERVICE_INSTANCE;
    }

    private AppiumService(){

    }

    public final void setDriver(AppiumDrivers deviceDriver, String capsFileFolder) {
        try {
            driver = AppiumDrivers.setDriver(deviceDriver, capsFileFolder);
            super.setDriver(driver);
            logger.info("Setting browser to driver finished successfully. \n\t");
        } catch (Exception ex) {
            logger.error("Problem in setting browser to driver ", ex);
        }
    }

    public final void closeDriver() {
        super.closeDriver();
    }

    @Override
    public final WebElement findElement(By by, String elementName) {
        return super.findElement(by, elementName);
    }

    @Override
    public final WebElement findElement(By by) throws Exception {
        return super.findElement(by);
    }

    @Override
    public final synchronized void explicitWait(long explicitWait, By by) {
        super.explicitWait(explicitWait, by);
    }

    @Override
    public final synchronized void implicitWait(long implicitWait, TimeUnit time) {
        super.implicitWait(implicitWait, time);
    }

    public final boolean isElementVisible(WebElement element) {
        return super.isElementVisible(element);
    }

    public final synchronized void implicitWait(long implicitWait) {
        super.implicitWait(implicitWait);
    }

    @Override
    public final boolean IsElementPresent(By by) {
        return super.IsElementPresent(by);
    }

    @Override
    public <T extends BasePage> boolean initElement(T pageObject) {
        return super.initElement(pageObject);
    }

    @Override
    public final void openBrowser(String url) {
        super.openBrowser(url);
    }

    @Override
    public final void closeBrowser() throws Exception {
        super.closeBrowser();
    }

    public final MobileElement scroll(String val) throws Exception {
        try{
            return driver.scrollTo(val);
        }
        catch(Exception e){
            GeneralUtils.handleError("Can't find message", e);
        }
        return null;
    }

    public final String getPageSource(long timeOutInMillisec){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            GeneralUtils.handleError("Failed waiting for page context", e);        }
        while (driver.getPageSource() == null) {
            try {
                Thread.sleep(waitForPageSourceTimeOutInterval);
                timeOutInMillisec -= waitForPageSourceTimeOutInterval;
                if(timeOutInMillisec <= 0){
                    GeneralUtils.handleError("Time out finished without finding results",
                            new Exception("Time out finished without finding results"));
                    return null;
                }
            }catch (InterruptedException e) {
                GeneralUtils.handleError("Error in wait for time out", e);
            }

        }
        return driver.getPageSource();
    }

    public void tap(int xLoc, int yLoc){
        new io.appium.java_client.TouchAction(driver).tap(xLoc, yLoc).release().perform();
    }

    public WebElement getElementByText(By listLocator, String text){
        List<WebElement> elements = driver.findElements(listLocator);
        for(WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(text)) {
                return element;
            }
        }
        logger.warn("Element not found");
        return null;
    }

}
