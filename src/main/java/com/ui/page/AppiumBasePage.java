package com.ui.page;

import com.ui.page.base.BasePage;
import com.ui.page.base.NotInPageException;
import com.ui.service.AppiumService;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;

/**
 * Created by asih on 22/02/2015.
 */
public abstract class AppiumBasePage extends BasePage {

    private static final Logger logger = Logger.getLogger(SeleniumBasePage.class);
    private long waitForPageContextTimeOut = 2000;

    protected AppiumBasePage(boolean isToFailTestOnLocation, boolean isValidateOnPage){
        super(isToFailTestOnLocation, isValidateOnPage);
    }
//
    protected abstract void prepareElements();

    @Override
    public boolean validateInPage() throws NotInPageException {
        logger.debug("Validating is in page to page " + this.getClass().getName());
        try {
            String driverPageSource = AppiumService.getInstance().getPageSource(waitForPageContextTimeOut);
            String dynamicIdentifier = this.getPageUniqueIdentifier();
            if(driverPageSource.equalsIgnoreCase(this.getPageUniqueIdentifier())) {
                logger.info("The page URL is as expected, you are in the correct location");
                logger.info("You expected : " + dynamicIdentifier + " You are in : " + driverPageSource);
                return true;
            }
            else{
                logger.warn("The page URL is not as expected, you are not in the correct location");
                logger.info("You expected : " + dynamicIdentifier + " You are in : " + driverPageSource);
                report(dynamicIdentifier, driverPageSource);
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
