package com.ui.page;

import com.ui.page.base.BasePage;
import com.ui.page.base.NotInPageException;
import com.ui.service.SeleniumService;
import com.util.log.ColoredLog;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;

/**
 * Created by asih on 22/02/2015.
 */
public abstract class SeleniumBasePage extends BasePage {

    protected SeleniumService service = SeleniumService.getInstance();

    private static final Logger logger = Logger.getLogger(SeleniumBasePage.class);

    protected SeleniumBasePage(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    public abstract void prepareElements();

    @Override
    public boolean validateInPage() throws NotInPageException {
        logger.debug("Validating is in page to page " + this.getClass().getName());
        try {
            String url = service.getDriver().getCurrentUrl();
            String dynamicIdentifier = this.getPageUniqueIdentifier();
            if(url.contains(dynamicIdentifier)) {
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "The page URL is as expecteColoredLogMessages.printMessage(ColoredLogMessages.LogLevel.INFO, n the correct location");
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "You expected : " + dynamicIdentifier + " You are in : " + url);
                return true;
            }
            else{
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "The page URL is not as expected, you are not in the correct location");
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "You expected : " + dynamicIdentifier + " You are in : " + url);
                report(dynamicIdentifier, url);
                return false;
            }
        }catch(NoSuchWindowException e){
            return false;
        }
    }

    protected void report(String pageClassName, String url) throws NotInPageException{
        super.report(pageClassName, url);
    }

    public abstract String getPageUniqueIdentifier() throws NotInPageException;

}
