package com.ui.page.base;

/**
 * Pagable
 * 
 * 
 * @author cohenc1
 */

public interface Pagable {

     abstract boolean validateInPage() throws NotInPageException;

     abstract String getPageUniqueIdentifier() throws NotInPageException;

}
