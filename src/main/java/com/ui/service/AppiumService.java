package com.ui.service;

import com.test.AppiumScriptHandler;
import com.ui.page.base.BasePage;
import com.ui.service.drivers.Drivers;
import com.util.genutil.GeneralUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by asih on 22/02/2015.
 */
public class AppiumService extends UIService<WebElement, AppiumDriver> {

    private static volatile AppiumDriver driver = null;
    private static final AppiumService APPIUM_SERVICE_INSTANCE = new AppiumService();
    private static final Logger logger = Logger.getLogger(SeleniumService.class);
    private final long waitForPageSourceInterval = 500;
    private static String lastPageSource = "";
    private UiMode requestedScreenMode;


    public static AppiumService getInstance() {
        return APPIUM_SERVICE_INSTANCE;
    }

    protected AppiumService(){

    }

    public void setDriver(Drivers deviceDriver, String capsFileFolder, AppiumScriptHandler.Machine machine, String port, String ip) {
        try {
            driver = Drivers.Appium.setDriver(deviceDriver, capsFileFolder, machine, port, ip);
            this.setDriver(driver);
            logger.info("Setting browser to driver finished successfully. \n\t");
        } catch (Exception ex) {
            logger.error("Problem in setting browser to driver ", ex);
            Assert.assertTrue(deviceDriver.name() + " driver was not created", false);
        }
    }

    public void setDriver(AppiumDriver _driver) {
        driver = _driver;
    }

    public void closeDriver() {
        // super.setDriver(driver);
        try {
            driver.close();
        } catch (SessionNotFoundException e) {
            logger.error("No session found");
        } catch (Throwable t) {
            GeneralUtils.handleError("Problem in closing the driver", t);
        }
        logger.info("Close Driver finished successfully");
    }

    @Override
    public WebElement findElement(By by, String elementName) {
//        super.setDriver(driver);
        try {
            super.parseMessage(elementName);
            logger.info("**********************************************************************");
            logger.info("TRYING TO FIND ELEMENT NAME  :  \"" + UIService.elementName + "\" IN CLASS  :  " + elementClassName);
            WebElement element = driver.findElement(by);
            logger.info("ELEMENT NAME  :  \"" + UIService.elementName + "\" IN CLASS  :  " + elementClassName + " WAS FOUND");
            logger.info("**********************************************************************");
            return element;
        } catch (NoSuchElementException e) {
            logger.error(printErrorMessage(elementName));
            GeneralUtils.handleError(GeneralUtils.stacktraceToString(e), e);
            return null;
        }
    }

    @Override
    public WebElement findElement(By by) throws Exception {
        return driver.findElement(by);
    }

    @Override
    public synchronized void explicitWait(long explicitWait, By by) {
        driver.switchTo().window(driver.getWindowHandle());
        WebDriverWait wait = new WebDriverWait(driver, explicitWait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        logger.debug("Excplicit wait finished");
    }

    @Override
    public synchronized void implicitWait(long implicitWait, TimeUnit time) {
        driver.switchTo().window(driver.getWindowHandle());
        driver.manage().timeouts().implicitlyWait(implicitWait, time);
        logger.debug("Implicit wait finished");
    }

    public synchronized void implicitWait(long implicitWait) {
        try {
            logger.debug("Sleeping for " + implicitWait + " mili");
            Thread.sleep(implicitWait);
            logger.debug("Implicit wait finished");
        } catch (InterruptedException e) {
            GeneralUtils.handleError("Problem while sleeping", e);
        }
    }

    @Override
    public boolean IsElementPresent(By by) {
        try {
            driver.findElements(by);
            logger.info("Element is present");
            return true;
        } catch (Exception e) {
            GeneralUtils.handleError("Problem in is element presented " + GeneralUtils.stacktraceToString(e), e);
            return false;
        }
    }

    @Override
    public boolean isElementVisible(WebElement element) {
        try {
            if (element.isDisplayed()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.info("Element not found");
            return false;
        }
    }

    @Override
    public <T extends BasePage> boolean initElement(T pageObject) {
        try {
            PageFactory.initElements(driver, pageObject);
            logger.info("Init elements finished successfully");
            return true;
        } catch (Exception e) {
            GeneralUtils.handleError("Problem in init the UI elements " + GeneralUtils.stacktraceToString(e), e);
            return false;
        }
    }

    @Override
    public void openBrowser(String url) {
        try {
            driver.get(url);
            logger.info("Open browser finished successfully");
        } catch (Throwable t) {
            GeneralUtils.handleError("Problem While trying to open the browser " + GeneralUtils.stacktraceToString(t), t);
        }
    }

    @Override
    public void closeBrowser() throws Exception {
        try {
            driver.quit();
            logger.info("Close browser finished successfully");
        } catch (Throwable t) {
            GeneralUtils.handleError("Problem While trying to close the browser " + GeneralUtils.stacktraceToString(t), t);
        }
    }

    public void isMsgInLocator(By by, String msg, String page){
        implicitWait(1500);
        WebElement elem = driver.findElement(by);
        Assert.assertEquals("MESSAGE VALIDATION : \"" + msg + "\" WAS NOT FOUND in page " + page, msg, elem.getText() );
        logger.info("MESSAGE VALIDATION : \"" + msg + "\" WAS FOUND in page \"" + page + "\"");
    }

    public void closeApp() throws Exception {
        driver.closeApp();
    }

    public MobileElement scroll(String val) {
        try{
            return driver.scrollTo(val);
        }
        catch(Exception e){
            GeneralUtils.handleError("Can't find message", e);
        }
        return null;
    }

    public String getPageSource(long timeOutInMilisec){
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

    public void rotate(ScreenOrientation orientation){
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
        return getElementByText(listLocator, text);
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
