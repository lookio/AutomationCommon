package com.ui.page.base;

import com.ui.service.base.UIService;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


/**
 * Created by asih on 22/02/2015.
 */

public abstract class BasePage implements  Pagable{

    private static final Logger logger = Logger.getLogger(BasePage.class);
    protected String className = this.getClass().getName();

    protected UIService service = new UIService();

    protected WebDriver driver = service.getDriver();


    protected BasePage(boolean isToFailTestOnLocation, boolean isValidateOnPage){
        logger.debug("In page constructor");
        try{
            if(isValidateOnPage){ // to validate???
                if(this.validateInPage()){
                logger.debug("Starting to prepare page elements for page "  + this.getClass().getName());
                this.prepareElements(); // polymorphysm
                logger.debug("Page elements was prepared successfully");
                }
            }
            else{
                this.prepareElements();
            }
            logger.debug("--------------------------------------------");
            logger.debug("---------- Now opening page with class : " + this.className + " ---------------------");
            logger.debug("--------------------------------------------");

        }catch(Throwable t){
            logger.error("Exception in BasePage constructor", t);
        }
        logger.info("New instanse of class " + this.getClass().getName() + " was created successfully");
    }

    protected abstract void prepareElements();

    public abstract boolean validateInPage() throws NotInPageException;

    protected void report(String pageClassName, String url) throws NotInPageException{
        throw new NotInPageException("The page URL is not as expected, you are not in the correct location" +
                "You expected : " + pageClassName + " You are in : " + url);
    }

    public abstract String getPageUniqueIdentifier() throws NotInPageException;

}
