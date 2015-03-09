package com.ui.service.base;

/**
 * Driver Manager Service.
 * General driver actvities contract
 * 
 * 
 * @author asih
 */

import java.util.concurrent.TimeUnit;


public interface DriverService {

     void closeDriver();
    
     void implicitWait(long implicitWait, TimeUnit time);

     void openBrowser(String url) throws Exception;
    
     void closeBrowser() throws Exception;

}
