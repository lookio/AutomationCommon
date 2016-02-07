package com.ui.page;

import com.ui.page.base.BasePage;
import com.ui.page.base.NotInPageException;
import com.ui.service.AppiumService;
import com.util.log.ColoredLog;
import org.apache.log4j.Logger;

/**
 * Created by asih on 22/02/2015.
 */
public abstract class AppiumBasePage extends BasePage {

    private static final Logger logger = Logger.getLogger(AppiumBasePage.class);
    // problem in wait for page
    private long waitForPageContextTimeOut = 15000;
    protected static AppiumService service = AppiumService.getInstance();

    protected AppiumBasePage(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }
    //
    public abstract void prepareElements();

    @Override
    public boolean validateInPage() throws NotInPageException {
        logger.debug("Validating is in page to page " + this.getClass().getName());
        try {
            String driverPageSource = service.getPageSource(waitForPageContextTimeOut);
            String dynamicIdentifier = this.getPageUniqueIdentifier();
            if(driverPageSource.contains(dynamicIdentifier)) {
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "The expected message is contained in page source of page " + className +
                        ", you are in the correct location");
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "You expected message msg : \"" + dynamicIdentifier + "\" You contained in : \"" + driverPageSource + "\"");
                return true;
            } else {
                ColoredLog.printMessage(ColoredLog.LogLevel.WARN, "The page is not as expected, you are not in the correct location");
                ColoredLog.printMessage(ColoredLog.LogLevel.ERROR, "You expected to find the MSG \"" + dynamicIdentifier + "\" in page " + className + " and failed");
                report(dynamicIdentifier, driverPageSource);
                throw new NotInPageException("Page " + className + "not in the right location");
            }
        }catch(Exception e){
            return false;
        }
    }

    protected void report(String pageClassName, String url) throws NotInPageException{
        super.report(pageClassName, url);
    }

    public abstract String getPageUniqueIdentifier() throws NotInPageException;

}
