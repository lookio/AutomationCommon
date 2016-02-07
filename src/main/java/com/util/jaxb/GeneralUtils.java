package com.util.jaxb;
/**
 * General Utils
 * Performs util actions for test
 *
 * @author ASIH
 *
 */



import com.ui.service.drivers.Drivers;
import com.util.log.ColoredLog;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

public class GeneralUtils {

//    private static final Logger logger = Logger.getLogger(GeneralUtils.class);
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
        ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Stack trace parsed");
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
        ColoredLog.printMessage(ColoredLog.LogLevel.DEBUG, "Tokenizer was parsed successfully");
        return retValue;
    }

    public static <T extends Throwable> void handleError(String error , T t){
        ColoredLog.printMessage(ColoredLog.LogLevel.ERROR, error + t.getMessage());
        Assert.assertFalse(error + GeneralUtils.stacktraceToString(t), true);
    }

    public synchronized static void verifyIfExisted(String elementName, String expDataFileValue, String actualPageValue){
        //		String trimmedExpected = expDataFileValue.trim();
        try{
            if(!expDataFileValue.equalsIgnoreCase("")){
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Going to validate :");
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, elementName);
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Expected value :");
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, expDataFileValue);
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
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Going to validate :");
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, elementName);
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Expected value :");
                ColoredLog.printMessage(ColoredLog.LogLevel.INFO, expDataFileValue);
                if(!actualPageValue.contains(expDataFileValue)){
                    Assert.assertFalse("Assertion error expected " + expDataFileValue + " actual " + actualPageValue, true);
                }
                else{
                    ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Assertion success for " +  elementName +
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

    public static StringBuilder catchLogDevice(LogCatType logCatType) {
        StringBuilder deviceLogCapture = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(logCatType.command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			Character[] bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()), 50000);

            String line;
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "======================================================================================");
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "============================ START OF ANDROID LOG CAT ================================");
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "======================================================================================");
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "======================================================================================");
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "============================= " + logCatType.msg + "==================================");
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "======================================================================================");
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("Liveperson")) {
                    ColoredLog.printMessage(ColoredLog.LogLevel.INFO, line);
//                        LPMobileLog.i(TAG, "line " + line);
                    line = line.substring(line.indexOf("Liveperson") + "Liveperson".length());
                    deviceLogCapture.append(line);
                    deviceLogCapture.append("\n");
                }
            }
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "======================================================================================");
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "============================== END OF ANDROID LOG CAT ================================");
            ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "======================================================================================");

        } catch (IOException e) {
            e.getMessage();
        }
        return deviceLogCapture;
    }

    public enum LogCatType {

        SERIAL_TEST_LOGCAT ("adb logcat -d", "VISITOR IN SERIAL TEST"),

        CONCURRENCY_SANITY_VISITOR("adb -s emulator-5554 shell logcat", "CONCURRENCY_SANITY_VISITOR"),
        CONCURRENCY_AGENT_RESOLVE_VISITOR("adb -s emulator-5556 shell logcat", "CONCURRENCY_AGENT_RESOLVE_VISITOR"),

        CONCURRENCY_SANITY_VISITOR_LOCAL("adb -s 192.168.56.100:5555 shell logcat", "CONCURRENCY_SANITY_VISITOR_LOCAL"),
        CONCURRENCY_AGENT_RESOLVE_VISITOR_LOCAL("adb -s 192.168.56.101:5555 shell logcat", "CONCURRENCY_AGENT_RESOLVE_VISITOR_LOCAL"),


        CONCURRENCY_SANITY_VISITOR_CLEAN("adb -s emulator-5554 shell logcat -c", "CONCURRENCY_SANITY_VISITOR_CLEAN"),
        CONCURRENCY_AGENT_RESOLVE_VISITOR_CLEAN("adb -s emulator-5556 shell logcat -c", "CONCURRENCY_AGENT_RESOLVE_VISITOR_CLEAN"),

        CONCURRENCY_SANITY_VISITOR_CLEAN_LOCAL("adb -s 192.168.56.100:5555 shell logcat -c", "CONCURRENCY_SANITY_VISITOR_CLEAN_LOCAL"),
        CONCURRENCY_AGENT_RESOLVE_VISITOR_CLEAN_LOCAL("adb -s 192.168.56.102:5555 shell logcat -c", "CONCURRENCY_AGENT_RESOLVE_VISITOR_CLEAN_LOCAL");

        String command;
        String msg;

        LogCatType(String command, String msg){
            this.command = command;
            this.msg = msg;
        }


    }



}