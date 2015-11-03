package com.test;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by asih on 24/08/2015.
 */
public class AppiumScriptHandler {

//    static StringTokenizer startAppiumServerArgs = new StringTokenizer("/c,node.exe,node_modules\\appium\\bin\\appium.js,--address,127.0.0.1,--port,4723,--bootstrap-port,4724,--no-reset,--local-timezone,--log,appiumServerLogs.txt", ",");
    static String startAppiumServerOnWindowsArgs = "/c,node.exe,node_modules\\appium\\bin\\appium.js,--address,INPUT_IP,--port,INPUT_PORT";
    static String startAppiumServerOnMacArgs = "node_modules\\appium\\bin\\appium.js,--address,127.0.0.1,--port,4723";
    static StringTokenizer stopAppiumServerArgs = new StringTokenizer("/c,taskkill,/F,/IM,node.exe", ",");
    static List<String> argsWithFalseFlag = Arrays.asList("127.0.0.1", "--port", "4723", "--bootstrap-port" , "4724" , "--no-reset");
    static List<String> argsWithAppiumHomePrefix = Arrays.asList("node.exe", "node_modules\\appium\\bin\\appium.js", "appiumServerLogs.txt");

    private AppiumScriptHandler(){

    }


    public enum Machine {

        WINDOWS(startAppiumServerOnWindowsArgs) ,
        MAC(startAppiumServerOnMacArgs);

        public String args;

        Machine(String args){
            this.args = args;
        }
    }
}
