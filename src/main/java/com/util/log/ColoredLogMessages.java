package com.util.log;


public class ColoredLogMessages {

    public static final String COLOR_POSTFIX = "\u001B[0m";

    public static final String RED_COLOR_PREFIX = "\u001B[1;31m";
    public static final String WARN_COLOR_PREFIX = "\u001B[1;33m";
    public static final String DEBUG_COLOR_PREFIX = "\u001B[1;34m";


    private ColoredLogMessages(){

    }

    public static void printMessage(LogLevel level, String msg){

        if(level == LogLevel.DEBUG){
            System.out.println(DEBUG_COLOR_PREFIX  + msg + COLOR_POSTFIX);
        } else if(level == LogLevel.WARN){
            System.out.println(WARN_COLOR_PREFIX  + msg + COLOR_POSTFIX);
        } if(level == LogLevel.ERROR){
            System.out.println(RED_COLOR_PREFIX  + msg + COLOR_POSTFIX);
        }

    }


    public enum LogLevel{

        DEBUG, WARN, ERROR;

    }

}