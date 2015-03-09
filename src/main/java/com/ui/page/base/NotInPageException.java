/**
 * Not In Page Exception
 * Validates flow correctnes
 * 
 * @extend exception         
 * @author asih
 */

package com.ui.page.base;


public class NotInPageException extends Exception {
	
	private static final long serialVersionUID = -5931306700355833645L;
	
	public NotInPageException(String message) {
		super(message);
	}
}