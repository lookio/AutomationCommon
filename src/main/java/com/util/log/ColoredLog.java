package com.util.log;


public class ColoredLog {

    public static final String COLOR_POSTFIX = "\u001B[0m";

    public static final String RED_COLOR_PREFIX = "\u001B[1;31m";
    public static final String INFO_COLOR_PREFIX = "\u001B[1;39m";
    public static final String WARN_COLOR_PREFIX = "\u001B[1;33m";
    public static final String DEBUG_COLOR_PREFIX = "\u001B[1;34m";


    private ColoredLog(){

    }

    public static void printMessage(LogLevel level, String msg){
        System.setProperty("jansi.passthrough", "true");
        if(level == LogLevel.DEBUG){
            System.out.println(DEBUG_COLOR_PREFIX  + msg + COLOR_POSTFIX);
        }  else if(level == LogLevel.INFO){
            System.out.println(INFO_COLOR_PREFIX  + msg + COLOR_POSTFIX);
        }else if(level == LogLevel.WARN){
            System.out.println(WARN_COLOR_PREFIX  + msg + COLOR_POSTFIX);
        } if(level == LogLevel.ERROR){
            System.out.println(RED_COLOR_PREFIX  + msg + COLOR_POSTFIX);
        }

    }


    public enum LogLevel{

        DEBUG, INFO, WARN, ERROR;

    }

}