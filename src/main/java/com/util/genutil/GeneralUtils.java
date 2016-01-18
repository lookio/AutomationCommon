
/**
 * General Utils
 * Performs util actions for test
 *
 * @author ASIH
 *
 */


package com.util.genutil;

import com.ui.service.drivers.Drivers;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

public class GeneralUtils {

	private static final Logger logger = Logger.getLogger(GeneralUtils.class);
	private static boolean isTestFailed = false;

	/**
	 * Private constructor
	 *
	 */

	private GeneralUtils(){

	}

	/**
	 * Get string from stack trace
	 *
	 * @param t - the exception
	 * @return stack trace string
	 */
	public static String stacktraceToString(Throwable t){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		logger.info("Stack trace parsed");
		return sw.toString();
	}

	/**
	 * Tokenize To List
	 *
	 * @param input -
	 * @param delimiter -
	 *
	 * @return The tokenized string list
	 */
	public static List<String>  tokenizeToList(String input, String delimiter){
		StringTokenizer stk = new StringTokenizer(input, delimiter);
		List<String> retValue = new ArrayList<String>();
		while ( stk.hasMoreTokens() ) {
			retValue.add(stk.nextToken());
		}
		logger.debug("Tokenizer was parsed successfully");
		return retValue;
	}

	public static <T extends Throwable> void handleError(String error , T t){
		logger.error(error, t);
		Assert.assertFalse(error + GeneralUtils.stacktraceToString(t), true);
	}

	public synchronized static void verifyIfExisted(String elementName, String expDataFileValue, String actualPageValue){
		//		String trimmedExpected = expDataFileValue.trim();
		try{
			if(!expDataFileValue.equalsIgnoreCase("")){
				logger.info("Going to validate :");
				logger.info(elementName);
				logger.info("Expected value :");
				logger.info(expDataFileValue);
				assertEquals(elementName + " is not as expected", expDataFileValue, actualPageValue);
			}
		} catch(AssertionError e){
			handleError("General error in assertion", e);
		}
	}


	public synchronized static void verifyContainIfExisted(String elementName, String expDataFileValue, String actualPageValue){
		//		String trimmedExpected = expDataFileValue.trim();
		try{
			if(!expDataFileValue.equalsIgnoreCase("")){
				logger.info("Going to validate :");
				logger.info(elementName);
				logger.info("Expected value :");
				logger.info(expDataFileValue);
				if(!actualPageValue.contains(expDataFileValue)){
					Assert.assertFalse("Assertion error expected " + expDataFileValue + " actual " + actualPageValue, true);
				}
				else{
					logger.info("Assertion success for " +  elementName +
							" exp "+ expDataFileValue + " actual " + actualPageValue);
				}
			}
		}
		catch(AssertionError e){
			handleError("General error in assertion", e);
		}
	}

	public static String logWrappedMessage(String msg){
		StringBuilder sb = new StringBuilder();
		sb.append("================================================================================================================================")
		.append("\n")
        .append(msg)
		.append("\n")
		.append("================================================================================================================================");
		return sb.toString();
	}

	public static boolean isStringNotNull(String value){
		if((value != null) && (value.length() > 0)){
			return true;
		}
		return false;
	}

	public static boolean isTestFailed() {
		return isTestFailed;
	}

	public static void setTestFailed(boolean isTestFailed) {
		GeneralUtils.isTestFailed = isTestFailed;
	}

	public static void waitTimeOut(long waitInterval, long timeOutInMilisec) {
		try {
			Thread.sleep(waitInterval);
			timeOutInMilisec -= waitInterval;
			if(timeOutInMilisec <= 0){
				GeneralUtils.handleError("Time out finished without finding results",
						new Exception("Time out finished without finding results"));
			}
		}catch (InterruptedException e) {
			GeneralUtils.handleError("Error in wait for time out", e);
		}
	}

	public static String prepareAppiumUrl(String port, String ip){
		String urlPortWithPort = Drivers.Appium.APPIUM_SERVER_URL.replace("port", port);
		return urlPortWithPort.replace("ip", ip);
	}

	public static void runWindowsSideBySide(){
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec("cscript //logo c:\\users\\asih\\Desktop\\SideBySide.vbs");
		} catch (IOException e) {
			handleError("Failed in run Windows Side By Side", e);
		}
	}



}
