
package com.test;

//import com.config.base.ConfigItemsRouter;
//import com.liveperson.AgentState;
//import com.liveperson.Rep;
//import com.service.validate.echo_test.ChatService;
import com.ui.service.AppiumService;
import com.ui.service.SeleniumService;
import com.ui.service.drivers.Drivers;
import com.util.genutil.GeneralUtils;
import com.util.log.OutputGenerator;
import com.util.poller.PollService;
import com.util.properties.PropertiesHandlerImpl;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;

//import static com.liveperson.AgentState.Online;


public class BaseTest {

    private static final String ENV_PROP_FILE_PATH = "/environment/env.properties";
    private static final String LOG4J_PROP_FILE_PATH_KEY_VALUE = "Log4jPropFilePath";
    protected static final String OUTPUT_METHOD_POSTFIX = "Desc";

    protected static StaticRouter staticRouter = new StaticRouter();
    protected Router router = new Router();
//    protected static ChatActivity chatActivity = new ChatActivity();
    protected static Logging logging = new Logging();
    protected static int uiModeOrdinal = 0;
    private static InputStream is;


    private static Properties props;

    private static final Logger logger = Logger.getLogger(BaseTest.class);

    static {
        setProps(PropertiesHandlerImpl.getInstance().parseFromJar(ENV_PROP_FILE_PATH));

    }

    protected static AppiumService appiumService = AppiumService.getInstance();
    protected static SeleniumService seleniumService = SeleniumService.getInstance();


    @Rule
    public TestName name = new TestName();


    protected static class StaticRouter extends PollService {

        public static <T extends BaseTest> void before(
                Map<DriverType, Drivers> drivers,
//                ConfigItemsRouter.ConfigType confType,
                String testPath,
                Class<T> testClass,
                StringBuilder desc,
                AppiumScriptHandler.Machine machine,
                String port,
                String ip)
                throws Exception {

            for (Map.Entry<DriverType, Drivers> entry : drivers.entrySet()) {
                DriverType driverType = entry.getKey();
                Drivers driver = entry.getValue();
                if (driverType == DriverType.SELENIUM) {
                    seleniumService.setDriver(Drivers.setDriver(driver, Drivers.DriverType.SELENIUM, "", machine, "", ""));
                } else if (driverType == DriverType.APPIUM) {
                    appiumService.setDriver(Drivers.setDriver(driver, Drivers.DriverType.APPIUM, testPath, machine, port, ip));
                }
            }
//            if(confType != null) {
////            ConfigItemsRouter.getInstance().routeAction(confType, testPath);
//            }
            Logging.generateTestClassOutput(testClass, desc);
        }

        public static void after(DriverType driver) {

            if(driver == DriverType.SELENIUM){
                Drivers.Selenium.close();
            }else if(driver == DriverType.APPIUM) {
                Drivers.Appium.close();
//                try {
//                    uninstallApp();
//                } catch (IOException e) {
//                    logger.error("Error in uninstall app");
//                }
            }
        }

        public static void uninstallApp() throws IOException {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("adb uninstall com.messaging.sample.app");
            BufferedReader r = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                logger.info(line);
            }
        }

        public void startAppiumServer(String appiumHome, long timeOutInMilisec, AppiumScriptHandler.Machine machine, String port, String ip, boolean isRemote) throws Exception {
            CommandLine command;

            if(machine.name().equalsIgnoreCase("WINDOWS")) {
                if(isRemote) {
                    command = new CommandLine("psexec \\\\" + ip + " cmd");
                } else {
                    command = new CommandLine("cmd");
                }
            }else{
                command = new CommandLine("node");
            }
            String[] tokens = machine.args.split(",");

            for(String nextArg : tokens){
                if(AppiumScriptHandler.argsWithFalseFlag.contains(nextArg)){
                    command.addArgument(nextArg, false);
                }
                else if(AppiumScriptHandler.argsWithAppiumHomePrefix.contains(nextArg)){
                    command.addArgument(appiumHome + nextArg);
                }
                else if(nextArg.contains("INPUT_PORT")){
                    String appiumPort = nextArg.replace("INPUT_PORT", port);
                    command.addArgument(appiumPort);
                }
                else if(nextArg.contains("INPUT_IP")){
                    String appiumIp = nextArg.replace("INPUT_IP", ip);
                    command.addArgument(appiumIp);
                }
                else{
                    command.addArgument(nextArg);
                }
                ;
            }
            execCommandByIsRemote(isRemote, command, "Going to execute start appium server: ");
        }

        private DefaultExecutor getExecutor() throws IOException {
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(1);

            is = new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            };
            executor.getStreamHandler().setProcessOutputStream(is);
            return executor;
        }

        public boolean isAppiumServerStarted() throws IOException {
            if(is.read() != 0) {
                return true;
            }else {
                return false;
            }
        }

        @Override
        public boolean until() throws Exception {
            return isAppiumServerStarted();
        }

        public void stopAppiumServer(String ip, boolean isRemote){
            CommandLine command;

            if(isRemote) {
                command = new CommandLine("psexec \\\\" + ip + " cmd");
            } else {
                command = new CommandLine("cmd");
            }
            while (AppiumScriptHandler.stopAppiumServerArgs.hasMoreTokens()) {
                command.addArgument(AppiumScriptHandler.stopAppiumServerArgs.nextToken());
            }
            execCommandByIsRemote(isRemote, command, "Going to execute kill appium server: ");
        }

        private void execCommandByIsRemote(boolean isRemote, CommandLine command, String msg){
            try {
                logger.info(msg + command);
                if(!isRemote) {
                    DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
                    DefaultExecutor executor = new DefaultExecutor();
                    executor.setExitValue(1);
                    executor.execute(command, resultHandler);
                } else {
                    Runtime.getRuntime().exec(command.toString());
                }
            } catch (IOException e) {
                GeneralUtils.handleError("Command fail", e);
            }
        }


    }

    protected class Router{

        public void setUp() throws Exception {
//            AppiumService.getInstance().getDriver().r
            logging.configureLog4J();
            getLogging().generateTestMethodOutput(name.getMethodName());
        }

        protected void tearDown(DriverType driver) throws Exception {
            if(driver == DriverType.SELENIUM){
                Drivers.Selenium.close();
            }else if(driver == DriverType.APPIUM){
                Drivers.Appium.close();
            }

        }


    }

//    protected static class ChatActivity {
//
//        protected void changeAgentState(List<AgentState> agentStates, List<Rep> agents, int agentLocation, AgentState stateToChange) {
//            agentStates.set(agentLocation, stateToChange);
//            agents.get(agentLocation).setAvailability(AgentState.Offline.name());
//        }
//
//        protected void changeAgentStateWithRange(List<AgentState> agentStates, List<Rep> agents, int fromLocation, int toLocation, AgentState stateToChange) {
//            for(int i = fromLocation; i <= toLocation; i++){
//                agentStates.set(i, stateToChange);
//                agents.get(i).setAvailability("Away");
//            }
//        }
//
////        protected void startChat(AgentService service, ChatService chatService, List<Rep> repsState, List<AgentState> agentStates, Rep agent) {
////            try {
////                chatService.startAndValidateChat(service, repsState, agentStates, agent);
////            } catch (Exception e) {
////                GeneralUtils.handleError("Error while starting chat", e);
////            }
////        }
////
////        protected void closeChat(AgentService service, ChatService chatService, Rep agent) {
////            try {
////                chatService.closeChat(service, agent);
////            } catch (Exception e) {
////                GeneralUtils.handleError("Error while starting chat", e);
////            }
////        }
//
//        public static void initAgentLoginState(int numberOfAgents, List<Rep> agents, List<Rep> repsState, List<AgentState> agentStates){
//            for(int i = 0; i < numberOfAgents; i++){
//                repsState.add(agents.get(i));
//                agentStates.add(Online);
//            }
//        }
//
//    }

    protected static class Logging {

        private void configureLog4J() {
            java.net.URL url = getClass().getResource(
                    getProps().getProperty(
                            LOG4J_PROP_FILE_PATH_KEY_VALUE)
            );
            PropertyConfigurator.configure(url);
        }

        protected static <T> void generateTestClassOutput(Class<T> testClass, StringBuilder desc) {
            try {
                logger.info(OutputGenerator.createGenericClassDesc(testClass, desc));
            } catch (Throwable t) {
                GeneralUtils.handleError(
                        "activate method in class output for " +
                                "generate test description, in output class "
                                + testClass.getName() + "failed", t);
            }
        }

        protected void generateTestMethodOutput(String testName){
            try {
                logger.info(OutputGenerator.createGenericMethodDesc(testName));
            } catch (Throwable t) {
                GeneralUtils.handleError(
                        "Invoke method in class output for " +
                                "generate test description, in output method "
                                + "failed" + " in test " + testName, t);
            }
        }

    }

    public enum DriverType{
        SELENIUM, APPIUM;
    }

    public static Properties getProps() {
        return props;
    }

    public static void setProps(Properties props) {
        BaseTest.props = props;
    }

    protected Router getRouter() {
        return router;
    }

    public static StaticRouter getStaticRouter() {
        return staticRouter;
    }

//    protected static ChatActivity getChatActivity() {
//        return chatActivity;
//    }

    protected static Logging getLogging() {
        return logging;
    }

}
