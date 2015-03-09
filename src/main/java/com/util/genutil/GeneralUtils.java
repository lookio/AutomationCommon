
/**
 * General Utils
 * Performs util actions for test
 *
 * @author ASIH
 *
 */


package com.util.genutil;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

public class GeneralUtils {

	private static final Logger logger = Logger.getLogger(GeneralUtils.class);
	private static boolean isTestFailed = false;
	private static String EMPTY_STRING = "";
	//	private static  Throwable currentException;

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

	/**
	 * close Resources
	 * close browser and driver after assert or exception
	 *
	 *
	 */
	public static void closeResources(){

		//		try {
		//			SeleniumManagerImpl.getInstance().closeBrowser();
		//		} catch (Exception e) {
		//
		//			logger.error("Problem with close Resources", e);
		//		}
		//		SeleniumManagerImpl.getInstance().closeDriver();
		//		setTestFailed(true);

	}


	public static <T extends Throwable> void handleError(String error , T t){
		logger.error(error, t);
		GeneralUtils.closeResources();
		Assert.assertFalse(error + GeneralUtils.stacktraceToString(t), true);
	}

//    /**
//     * Print Test Output
//     * Activate the generic method createGenericMethodDesc in OutputGenerator
//     * Trim the string of test flows taken from file
//     * Feed value from file for output activation
//     *
//     *
//     *  @param
//     *     String testClassName - Taken from file
//     *  @param
//     *     String testName - Taken from file
//     *  @param
//     *     String testDescription - Taken from file
//     *  @param
//     *     String testFlow - Taken from file - The test steps flows
//     *
//     */
//    public static void printTestOutput(String testClassName, String testName, String testDescription, String testFlow){
//	String prepareStepsForTokenizer = testFlow.replaceAll("\n", "");
//	List<String> flow = new ArrayList<String>(Arrays.asList(prepareStepsForTokenizer.split(";")));
//	logger.info(OutputGenerator.createGenericMethodDesc(testClassName,
//		testName, new StringBuilder(testDescription), flow));
//    }


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
		}
		catch(AssertionError e){
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


	/**
	 *
	 * @param
	 * @return
	 * @Throws
	 *
	 */
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

	//	public static Throwable getCurrentException(){
	//		return currentException;
	//	}
	//
	//	public static void setCurrentException(Throwable currentException){
	//		GeneralUtils.currentException = currentException;
	//	}


}
