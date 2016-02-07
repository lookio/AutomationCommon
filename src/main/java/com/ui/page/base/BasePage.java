package com.ui.page.base;

import com.ui.page.AppiumBasePage;
import com.ui.service.drivers.Drivers;
import com.util.log.ColoredLog;
import org.apache.log4j.Logger;


/**
 * Created by asih on 22/02/2015.
 */

public abstract class BasePage implements  Pagable{

    private static final Logger logger = Logger.getLogger(BasePage.class);
    protected String className = this.getClass().getName();
    private boolean shouldValidateOnPage;
    private boolean shouldFailTestOnLocation;



    protected BasePage(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        this.shouldValidateOnPage = shouldValidateOnPage;
        this.shouldFailTestOnLocation = shouldFailTestOnLocation;

    }

    public void validate() throws NotInPageException{
        try {
            if (shouldValidateOnPage) { // to validate???
                if (this.validateInPage()) {
                    ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "--------------------------------------------");
                    ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "---------- Now opening page with class : " + className + " ---------------------");
                    ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "--------------------------------------------");
                    ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "New instanse of class " + this.getClass().getName() + " was created successfully");
                }
            }
        }catch(NotInPageException t) {
            if (shouldFailTestOnLocation) {
                logger.error("The page " + className + " is not in correct location and exit test was requested", t);
                this.close();
                throw new NotInPageException("Page " + className + "not in the right location");
            }
        }
    }

    public abstract void prepareElements();

    public abstract boolean validateInPage() throws NotInPageException;

    protected void report(String pageClassName, String url) throws NotInPageException{
        throw new NotInPageException("The page URL is not as expected, you are not in the correct location" +
                "You expected : " + pageClassName + " You are in : " + url);
    }

    public abstract String getPageUniqueIdentifier() throws NotInPageException;

    private void close(){
        if(this instanceof AppiumBasePage){
            Drivers.Appium.close();
        } else{
            Drivers.Selenium.close();
        }
    }

}
