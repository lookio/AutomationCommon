package com.ui.service;

import com.test.AppiumScriptHandler;
import com.ui.page.base.BasePage;
import com.ui.service.drivers.Drivers;
import com.util.jenutil.GeneralUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by asih on 22/02/2015.
 */
//public class ConcurrentAppiumService extends AppiumService {
public class ConcurrentAppiumService extends UIService<WebElement, AppiumDriver> {

    private AppiumDriver driver = null;
    private static final Logger logger = Logger.getLogger(SeleniumService.class);
    private final long waitForPageSourceInterval = 500;
    private static String lastPageSource = "";
    private UiMode requestedScreenMode;

    public ConcurrentAppiumService() {
    }


//    public ConcurrentAppiumService(){
//        super();
//
//    }

    public final void setDriver(Drivers deviceDriver, String capsFileFolder, AppiumScriptHandler.Machine machine, String port, String ip) {
        try {
            driver = Drivers.Appium.setDriver(deviceDriver, capsFileFolder, machine, port, ip);
            this.setDriver(driver);
            logger.info("Setting browser to driver finished successfully. \n\t");
        } catch (Exception ex) {
            logger.error("Problem in setting browser to driver ", ex);
            Assert.assertTrue(deviceDriver.name() + " driver was not created", false);
        }
    }

    public final void setDriver(AppiumDriver _driver) {
        driver = _driver;
    }

    public final void setDriver(AppiumDriver _driver, String msg) {
        driver = _driver;
        logger.info(msg);
    }

    public final void closeDriver() {
        super.setDriver(driver);
        super.closeDriver();
    }

    @Override
    public final WebElement findElement(By by, String elementName) {
        super.setDriver(driver);
        return super.findElement(by, elementName);
    }

    @Override
    public final WebElement findElement(By by) throws Exception {
        super.setDriver(driver);
        return super.findElement(by);
    }

    @Override
    public final synchronized void explicitWait(long explicitWait, By by) {
        super.setDriver(driver);
        super.explicitWait(explicitWait, by);
    }

    @Override
    public final synchronized void implicitWait(long implicitWait, TimeUnit time) {
        super.setDriver(driver);
        super.implicitWait(implicitWait, time);
    }

    public final boolean isElementVisible(WebElement element) {
        super.setDriver(driver);
        return super.isElementVisible(element);
    }

    public final synchronized void implicitWait(long implicitWait) {
        super.setDriver(driver);
        super.implicitWait(implicitWait);
    }

    @Override
    public final boolean IsElementPresent(By by) {
        super.setDriver(driver);
        return super.IsElementPresent(by);
    }

    @Override
    public <T extends BasePage> boolean initElement(T pageObject) {
        super.setDriver(driver);
        return super.initElement(pageObject);
    }

    @Override
    public final void openBrowser(String url) {
        super.setDriver(driver);
        super.openBrowser(url);
    }

    @Override
    public final void closeBrowser() throws Exception {
        super.setDriver(driver);
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
        try {
            new TouchAction(driver).tap(xLoc, yLoc).release().perform();
        }catch (Exception e){
            GeneralUtils.handleError("Tap failed", e);
        }
    }

    public WebElement getElementByText(By listLocator, String text){
        super.setDriver(driver);
        return super.getElementByText(listLocator, text);
    }

    private UiMode getRequestedScreenMode() {
        return requestedScreenMode;
    }

    public void setRequestedScreenMode(UiMode requestedScreenMode) {
        this.requestedScreenMode = requestedScreenMode;
    }

    public void activateSelectedActivity(WebElement elem, ModeActivity modeActivity, String input){
        if((getRequestedScreenMode() == (UiMode.PORTRAIT))){
            if(driver.getOrientation() != ScreenOrientation.PORTRAIT) {
                rotate(ScreenOrientation.PORTRAIT);
            }
            activate(elem, modeActivity, input);
        }
        if(getRequestedScreenMode() == UiMode.LANDSCAPE) {
            if (driver.getOrientation() != ScreenOrientation.LANDSCAPE) {
                rotate(ScreenOrientation.LANDSCAPE);
            }
            activate(elem, modeActivity, input);
        }
        if(getRequestedScreenMode() == UiMode.TRANSITION) {
            if (driver.getOrientation() == ScreenOrientation.LANDSCAPE) {
                rotate(ScreenOrientation.PORTRAIT);
                activate(elem, modeActivity, input);
                rotate(ScreenOrientation.LANDSCAPE);
            }else{
                rotate(ScreenOrientation.LANDSCAPE);
                activate(elem, modeActivity, input);
                rotate(ScreenOrientation.PORTRAIT);
            }
        }
    }

    private void activate(WebElement elem, ModeActivity modeActivity, String input){
        if(modeActivity.name().equalsIgnoreCase(ModeActivity.SEND_KEYS.name())) {
            elem.sendKeys(input);
        }else if(modeActivity.name().equalsIgnoreCase(ModeActivity.CLICK.name())) {
            elem.click();
        }
    }

    public WebDriver getDriver(){
        return driver;
    }

    public enum UiMode {

        PORTRAIT, LANDSCAPE, TRANSITION;

    }

    public enum ModeActivity {

        SEND_KEYS, CLICK;

    }


}