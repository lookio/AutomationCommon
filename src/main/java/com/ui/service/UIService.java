package com.ui.service;

import com.ui.page.base.BasePage;
import com.ui.service.base.DriverService;
import com.ui.service.base.ElementService;
import com.util.jaxb.GeneralUtils;
import com.util.log.ColoredLog;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by asih on 22/02/2015.
 */
public class UIService<E,T> implements ElementService<E>, DriverService {

    private WebDriver driver = null;
//    private static final Logger logger = Logger.getLogger(SeleniumService.class);
    public static String elementClassName;
    public static String elementName;


    public UIService(){

    }

    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
    public <T extends WebDriver> T getDriver() {
        return (T) driver;
    }

    @SuppressWarnings("unchecked")
    public <T extends WebDriver> void setDriver(WebDriver _driver) {
        try {
            driver = (T) _driver;
//            ColoredLogMessages.printMessage(ColoredLogMessages.LogLevel.INFO, "Setting driver to service finished successfully. \n\t");
        } catch (Exception ex) {
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Problem in setting browser to driver " + ex.getMessage());
        }
    }

    @Override
    public void closeDriver() {
        try {
            driver.close();
        } catch (SessionNotFoundException e) {
            ColoredLog.printMessage(ColoredLog.LogLevel.ERROR, "No session found");
        } catch (Throwable t) {
            GeneralUtils.handleError("Problem in closing the driver", t);
        }
        ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Close Driver finished successfully");
    }

    @Override
    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
    public <E> E findElement(By by) throws Exception {
//        driver.switchTo().window(driver.getWindowHandle());
        WebElement element = driver.findElement(by);
        return (E) element;
    }

    @Override
    public <E> E findElement(By by, String elementName) {
        try {
            parseMessage(elementName);
            ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "**********************************************************************");
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "TRYING TO FIND ELEMENT NAME  :  \"" + UIService.elementName + "\" IN CLASS  :  " + elementClassName);
            WebElement element = driver.findElement(by);
            ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "ELEMENT NAME  :  \"" + UIService.elementName + "\" IN CLASS  :  " + elementClassName + " WAS FOUND");
            ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "**********************************************************************");
            return (E) element;
        } catch (NoSuchElementException e) {
            ColoredLog.printMessage(ColoredLog.LogLevel.ERROR, printErrorMessage(elementName));
            GeneralUtils.handleError(GeneralUtils.stacktraceToString(e), e);
            return null;
        }
    }

    @Override
    public synchronized void explicitWait(long explicitWait, By by) {
        driver.switchTo().window(driver.getWindowHandle());
        WebDriverWait wait = new WebDriverWait(driver, explicitWait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "Excplicit wait finished");
    }

    @Override
    public synchronized void implicitWait(long implicitWait, TimeUnit time) {
        driver.switchTo().window(driver.getWindowHandle());
        driver.manage().timeouts().implicitlyWait(implicitWait, time);
        ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "Implicit wait finished");
    }

    public synchronized void implicitWait(long implicitWait) {
        try {
            ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "Sleeping for " + implicitWait + " mili");
            Thread.sleep(implicitWait);
            ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "Implicit wait finished");
        } catch (InterruptedException e) {
            GeneralUtils.handleError("Problem while sleeping", e);
        }
    }

    @Override
    public boolean IsElementPresent(By by) {
        try {
            driver.findElements(by);
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Element is present");
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
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Element not found");
            return false;
        }
    }

    @Override
    public <T extends BasePage> boolean initElement(T pageObject) {
        try {
            PageFactory.initElements(driver, pageObject);
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Init elements finished successfully");
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
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Open browser finished successfully");
        } catch (Throwable t) {
            GeneralUtils.handleError("Problem While trying to open the browser " + GeneralUtils.stacktraceToString(t), t);
        }
    }

    @Override
    public void closeBrowser() throws Exception {
        try {
            driver.quit();
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Close browser finished successfully");
        } catch (Throwable t) {
            GeneralUtils.handleError("Problem While trying to close the browser " + GeneralUtils.stacktraceToString(t), t);
        }
    }

    public static void parseMessage(String fullElementName) {
        String[] values = fullElementName.split("\\=");
        elementClassName = values[0];
        elementName = values[1];
    }

    protected void isMessage(String msg, String page){
        try{
            By msgBy = By.xpath("//*[contains(text(),'" + msg + "')]");
            implicitWait(1500);
            WebElement elem = findElement(msgBy);
            Assert.assertEquals(elem.isDisplayed() == true, "Msg : " + msg + " was found in page " + page);
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Msg : " + msg + " was found in page " + page);

//            ColoredLogMessages.printMessage(ColoredLogMessages.LogLevel.INFO, "Message " + msgBy + " was found");
//            if(elem.isDisplayed()){
//                ColoredLogMessages.printMessage(ColoredLogMessages.LogLevel.INFO, "Msg : " + msg +" was found in page " + page);
//                logger.error("Msg : " + msg + " was found in page " + page);
//                return;
//            }
        }
        catch(Exception e){
            GeneralUtils.handleError("Can't find message " + msg +" in page " + page, e);
            return;
        }
    }

    public WebElement getElementByText(By listLocator, String text){
//        if(SeleniumService.getInstance().getDriver() instanceof Waitable) {
//            ((Waitable) SeleniumService.getInstance().getDriver()).timeout(30*1000);
//        }
//        driver.manage().window().maximize();
        List<WebElement> elements = driver.findElements(listLocator);
        ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Number of item titles is " + elements.size());
        for(WebElement element : elements) {
//            if ((element.getText().equalsIgnoreCase(text) && (element.isDisplayed()))) {
            if (element.getText().equalsIgnoreCase(text)) {
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Match for item title : " + element.getText() + " is dispayed");
                return element;
            }
        }
        ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "No match for item title : " + text);
        ColoredLog.printMessage(ColoredLog.LogLevel.WARN, "Element not found");
        return null;
    }

    public static String printErrorMessage(String fullElementName) {
        StringBuilder retValue = new StringBuilder();
        parseMessage(fullElementName);
        retValue.append("Cant find element name : " + elementName + " located in class : " + elementClassName + " ");
        return retValue.toString();
    }



}
