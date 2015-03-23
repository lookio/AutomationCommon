package com.ui.page.base;

/**
 * Pagable
 * 
 * 
 * @author cohenc1
 */

public interface Pagable {

     boolean validateInPage() throws NotInPageException;

     String getPageUniqueIdentifier() throws NotInPageException;

     void prepareElements();

     public <T> T getValidate() ;

     public <T> T getActivate();

}
