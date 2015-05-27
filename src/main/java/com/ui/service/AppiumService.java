package com.ui.service;

import com.ui.page.base.BasePage;
import com.ui.service.drivers.AppiumDrivers;
import com.util.genutil.GeneralUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by asih on 22/02/2015.
 */
public class AppiumService extends UIService<WebElement, AppiumDriver> {

    private volatile AppiumDriver driver = null;
    private static final AppiumService APPIUM_SERVICE_INSTANCE = new AppiumService();
    private static final Logger logger = Logger.getLogger(SeleniumService.class);
    private final long waitForPageSourceInterval = 500;
    private static String lastPageSource = "";


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

    public void isMsgInLocator(By by, String msg, String page){
        implicitWait(1500);
        WebElement elem = driver.findElement(by);
        Assert.assertEquals("MESSAGE VALIDATION : \"" + msg + "\" WAS NOT FOUND in page " + page, msg, elem.getText() );
        logger.info("MESSAGE VALIDATION : \"" + msg + "\" WAS FOUND in page \"" + page + "\"");
    }

    public final void closeApp() throws Exception {
        driver.closeApp();
    }

    public final MobileElement scroll(String val) {
        try{
            return driver.scrollTo(val);
        }
        catch(Exception e){
            GeneralUtils.handleError("Can't find message", e);
        }
        return null;
    }        

    public final String getPageSource(long timeOutInMilisec){
        implicitWait(1500);
        while ((driver.getPageSource() == null) ||
                (driver.getPageSource().equalsIgnoreCase(lastPageSource))){
            try {
                Thread.sleep(waitForPageSourceInterval);
                timeOutInMilisec -= waitForPageSourceInterval;
                if(timeOutInMilisec <= 0){
                    GeneralUtils.handleError("Time out finished without finding results",
                            new Exception("Time out finished without finding results"));
                    return null;
                }
            }catch (InterruptedException e) {
                GeneralUtils.handleError("Error in wait for time out", e);
            }
        }
        return lastPageSource = driver.getPageSource();
    }

    public final void rotate(ScreenOrientation orientation){
        driver.rotate(orientation);
    }

    public void tap(int xLoc, int yLoc){
        new TouchAction(driver).tap(xLoc, yLoc).release().perform();
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
