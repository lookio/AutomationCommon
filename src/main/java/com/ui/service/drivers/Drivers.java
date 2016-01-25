package com.ui.service.drivers;

/**
 * Browsers.
 * Handle all browser types
 * Handle all browsers driver activities
 *
 *
 *
 * @author asih
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import com.liveperson.automation.webdriver.session.*;
import com.liveperson.automation.webdriver.session.*;
import com.test.AppiumScriptHandler;
import com.ui.service.AppiumService;
import com.ui.service.SeleniumService;
import com.util.genutil.GeneralUtils;
import com.util.properties.PropertiesHandlerImpl;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public enum Drivers {

	FIREFOX, IE, CHROME, SAFARI, ANDROID, IOS;

	private static final String ENV_VARS_PROPERTY_FILE_PATH = "/environment/env.properties";


	public static WebDriverSession setSeleniumDriver(Drivers driver, AppiumScriptHandler.Machine machine){
		try {
			return Selenium.setBrowserToDriver(driver, machine);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static AndroidDriver setAppiumDriver(Drivers driver, String testDir, AppiumScriptHandler.Machine machine, String port, String ip){
		try {
			return Appium.setDriver(driver, testDir, machine, port, ip);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Properties getProps(){
		return PropertiesHandlerImpl.getInstance().parseFromJar(ENV_VARS_PROPERTY_FILE_PATH);
	}


	public static class Selenium {

//    FIREFOX, IE, CHROME;

		private static final Logger logger = Logger.getLogger(Selenium.class);

		public static final String START_URL = "https://staging.feex.co.il/";

		//		private volatile static WebDriver driver = null;
		private static WebDriverSessionManager webDriverSessionManager;


		public static Properties props = null;

		private static final String PROP_KEY_CHROME_DRIVER_NAME_PATH_NAME = "Chrome_driver_property_name";
		private static final String PROP_KEY_CHROME_DRIVER_NAME_PATH_VALUE_WIN = "Chrome_driver_path_win";
		private static final String PROP_KEY_CHROME_DRIVER_NAME_PATH_VALUE_MAC = "Chrome_driver_path_mac";
		private static final String PROP_KEY_IE_DRIVER_NAME_PATH_NAME = "IE_driver_property_name";
		private static final String PROP_KEY_IE_DRIVER_NAME_PATH_VALUE = "IE_driver_path";

		private static String browserType;
//    static FirefoxProfile ffProfile = new FirefoxProfile("SanityTests");


		//		public static WebDriver setBrowserToDriver(Drivers browser, AppiumScriptHandler.Machine machine) throws MalformedURLException, Exception {
		public static WebDriverSession setBrowserToDriver(Drivers browser, AppiumScriptHandler.Machine machine) throws MalformedURLException, Exception {
			browserType = browser.name();
			logger.info("Trying to set " + browserType + " browser to driver");
			props = getProps();
//			switch (browser) {
//				case IE:
//					createIEDriver();
//					break;
//				case CHROME:
//					createChromeDriver(machine);
//					break;
//				case FIREFOX:
//					createFireFoxDriver();
//					break;
//				case SAFARI:
//					createSafariDriver();
//					break;
//			}
			webDriverSessionManager = new WebDriverSessionManagerDefaultImpl();
			WebDriverSessionConfiguration sessionConfig = new WebDriverSessionDefaultConfiguration();
			sessionConfig.setProxy("");
			webDriverSessionManager.createSession("my_session_id",sessionConfig);
			WebDriverSession webDriverSession = webDriverSessionManager.getCurrentSession();

			webDriverSession.manage().window().setSize(new Dimension(1800, 1150));
			webDriverSession.manage().window().setPosition(new Point(0, 0));
			webDriverSession.manage().window().maximize();
			webDriverSession.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			webDriverSession.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
			webDriverSession.manage().window().setSize(new Dimension(webDriverSession.manage().window().getSize().getWidth(),
					webDriverSession.manage().window().getSize().getHeight()));
//
//			SeleniumService.getInstance().setDriver(driver.getCurrentSession());
//			return driver.getCurrentSession();

//			return driver;
			return webDriverSession;
		}

		/**
		 * Creates Internaet Explorer driver.
		 *
		 * @throws Exception
		 */

		private synchronized static void createIEDriver() throws Exception {

			DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
			capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty(props.getProperty(PROP_KEY_IE_DRIVER_NAME_PATH_NAME), props.getProperty(PROP_KEY_IE_DRIVER_NAME_PATH_VALUE));
//			driver = new InternetExplorerDriver(capab);
			logger.info("====================================================================");
			logger.info("============== Created New Internet Explorer Driver ================");
			logger.info("====================================================================");
			logger.info("========== Starting New Test In Internet Explorer Driver ===========");
			logger.info("====================================================================");
			logger.debug("Internet explorer driver was created");
		}

		/**
		 * Creates Fire Fox driver.
		 *
		 * @throws Exception
		 */

		private synchronized static void createFireFoxDriver() throws Exception {

//       File pathToFirefoxBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//       FirefoxBinary firefoxbin = new FirefoxBinary(pathToFirefoxBinary);
//       WebDriver firefox = new FirefoxDriver();
//       firefox.get("google.com");
//       driver = firefox;

//       ffProfile.setPreference("webdriver.load.strategy", "unstable"); // As of 2.19. from 2.9 - f



//       driver = new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe")),ffProfile);
//			ProfilesIni allProfiles = new ProfilesIni();
//			FirefoxProfile profile = allProfiles.getProfile("SanityTests");
//			profile.setPreference("network.proxy.type", 1);
//			profile.setPreference("network.proxy.http","127.0.0.1");
//			profile.setPreference("network.proxy.http_port", 3128);
//			driver = new FirefoxDriver();




//       ProfilesIni profile = new ProfilesIni();
//
//       FirefoxProfile myprofile = profile.getProfile("ProfileToolQA");
//       driver = new FirefoxDriver(myprofile);
//       driver = new FirefoxDriver();
			logger.info("=========================================================");
			logger.info("============= Created New Fire Fox Driver ===============");
			logger.info("=========================================================");
			logger.info("========== Starting New Test In Fire Fox Driver =========");
			logger.info("=========================================================");
			logger.debug("Fire fox driver was created");
		}

		/**
		 * Creates Chrome driver.
		 *
		 * @throws Exception
		 */

		private synchronized static void createChromeDriver(AppiumScriptHandler.Machine machine)  {

			File file = null;
			if(machine == AppiumScriptHandler.Machine.WINDOWS) {
				file = new File(props.getProperty(PROP_KEY_CHROME_DRIVER_NAME_PATH_VALUE_WIN));
//          System.setProperty(props.getProperty(PROP_KEY_CHROME_DRIVER_NAME_PATH_NAME), file.getAbsolutePath());
				System.setProperty(props.getProperty(PROP_KEY_CHROME_DRIVER_NAME_PATH_NAME), "C:\\temp\\chromedriver.exe");
//          System.setProperty("webdriver.chrome.logfile", "C:\\temp\\chromedriver.log");

//          System.setProperty("webdriver.chrome.logfile", "chromedriver.exe --verbose --log-C:\\temp\\=chromedriver.log");

//          chromedriver.exe --verbose --log-path=chromedriver.log
			}else{
//          file = new File(props.getProperty(PROP_KEY_CHROME_DRIVER_NAME_PATH_VALUE_MAC));
				System.setProperty(props.getProperty(PROP_KEY_CHROME_DRIVER_NAME_PATH_NAME), props.getProperty(PROP_KEY_CHROME_DRIVER_NAME_PATH_VALUE_MAC));
			}


//			try {
//          ChromeDriverService service;
//          service = new ChromeDriverService.Builder()
//                .usingDriverExecutable(new File("C://temp//chromedriver.exe"))
//                .usingAnyFreePort()
//                .build();
//          service.start();
//          driver = new RemoteWebDriver(service.getUrl(),
//                DesiredCapabilities.chrome());





//				driver = new ChromeDriver();
//
//			}catch (Throwable t) {
//				logger.info("CDriver is " + driver);
//				GeneralUtils.handleError("error chrome driver", t);
//			}
//			logger.info("CDriver is " + driver);

			logger.info("=========================================================");
			logger.info("============= Created New Chrome Driver =================");
			logger.info("=========================================================");
			logger.info("========== Starting New Test In Chrome Driver ===========");
			logger.info("=========================================================");
			logger.debug("Chrome driver was created");
		}

		private synchronized static void createSafariDriver() throws Exception {
			WebDriver driver = new SafariDriver();
			logger.info("=========================================================");
			logger.info("============= Created New Safari Driver ===============");
			logger.info("=========================================================");
			logger.info("========== Starting New Test In Safari Driver =========");
			logger.info("=========================================================");
			logger.debug("Fire fox driver was created");
		}

		public static String getBrowserType() {
			return browserType;
		}

//		public static void startChrome() throws Exception {
//			SeleniumService.getInstance().setDriver(Drivers.CHROME, null);
//			SeleniumService.getInstance().openBrowser(START_URL);
//		}
//
//		public static void startFIREFOX() throws Exception {
//			SeleniumService.getInstance().setDriver(Drivers.FIREFOX, null);
//			SeleniumService.getInstance().openBrowser(START_URL);
//		}
//
//		public static void startIE() throws Exception {
//			SeleniumService.getInstance().setDriver(Drivers.IE, null);
//			SeleniumService.getInstance().openBrowser(START_URL);
//		}
//
//		public static void startChromeWithTimeOut(int timeOutInSeconds) throws Exception {
//			SeleniumService.getInstance().setDriver(Drivers.CHROME, null);
//			SeleniumService.getInstance().openBrowser(START_URL);
////			driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
//		}
//
//		public static void startFIREFOXWithTimeOut(int timeOutInSeconds) throws Exception {
//			SeleniumService.getInstance().setDriver(Drivers.FIREFOX, null);
//			SeleniumService.getInstance().openBrowser(START_URL);
////			driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
//
//		}
//
//		public static void startIEWithTimeOut(int timeOutInSeconds) throws Exception {
//			SeleniumService.getInstance().setDriver(Drivers.IE, null);
//			SeleniumService.getInstance().openBrowser(START_URL);
////			driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
//
//		}

		public static void close() {
			logger.info("Test on browser " + Selenium.getBrowserType() + " finished succssesfully");
			try {
				SeleniumService.getInstance().closeBrowser();
				SeleniumService.getInstance().closeDriver();
			} catch (Exception e) {
				GeneralUtils.handleError("Error in close resources", e);
			}
		}


//		public static void startBrowserByType(Drivers browser) throws Exception {
//			switch (browser) {
//				case CHROME:
//					startChrome();
//					break;
//				case FIREFOX:
//					startFIREFOX();
//					break;
//				case IE:
//					startIE();
//					break;
//				default:
//					startFIREFOX();
//
//			}
//
//		}

	}


	public static class Appium {

//    static int counter = 0;

//    ANDROID, IOS;

		private static final Logger logger = Logger.getLogger(Appium.class);

		public static final String APPIUM_SERVER_URL = "http://ip:port/wd/hub"; // 4723
		private static final String IMPLICIT_WAIT = "implicitWait";

		private volatile static AppiumDriver driver = null;

		public static Properties props = null;

		private static String browserType;


		public static <T extends RemoteWebDriver> T setDriver(Drivers driverType, String testDir, AppiumScriptHandler.Machine machine, String port, String ip) throws MalformedURLException, Exception {
			logger.info("Trying to set " + driverType.name() + " driver");
			DesiredCapabilities caps = CapabilitiesBuilder.
					getInstance().getCapabilities(testDir, driverType, machine);
//       if(driver != null){
//          throw new NullPointerException("Driver allready exists");
//       }
			switch (driverType) {
				case ANDROID:
//             if(counter == 0) {
					driver = createAndroidDriver(caps, port, ip);
//             }else if(counter == 1) {
//                driver2 = createAndroidDriver(caps, port);
//             }
//             counter++;
					printAndroidSuccessCreationMsg();
					break;
				case IOS:
					driver = createIOSDriver(caps, port, ip);
					printIOSSuccessCreationMsg();

			}
//        driver.(10, TimeUnit.SECONDS);
			if(machine.name().equalsIgnoreCase(AppiumScriptHandler.Machine.WINDOWS.name())) {
				int wait = new Integer(CapabilitiesBuilder.getInstance().getAppDriverProps().getProperty(IMPLICIT_WAIT));
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			}
			AppiumService.getInstance().setDriver(driver);
			return (T) driver;
		}

		private synchronized static AppiumDriver createAndroidDriver(DesiredCapabilities caps, String port, String ip) throws Exception {
			return new AndroidDriver(new URL(GeneralUtils.prepareAppiumUrl(port, ip)), caps);
		}

		private static void printAndroidSuccessCreationMsg(){
			logger.info("====================================================================");
			logger.info("============== Created New Android Driver ================");
			logger.info("====================================================================");
			logger.info("========== Starting New Test In Android Driver ===========");
			logger.info("====================================================================");
		}

		synchronized static AppiumDriver createIOSDriver(DesiredCapabilities caps, String port, String ip) throws Exception {
			return new IOSDriver(new URL(GeneralUtils.prepareAppiumUrl(port, ip)), caps);
		}

		private static void printIOSSuccessCreationMsg(){
			logger.info("====================================================================");
			logger.info("============== Created New IOS Driver ================");
			logger.info("====================================================================");
			logger.info("========== Starting New Test In IOS Driver ===========");
			logger.info("====================================================================");
		}

		public static void close()  {
			logger.info("Test on driver finished succssesfully");
			try {
				if(driver != null) {

					AppiumService.getInstance().closeBrowser();
					AppiumService.getInstance().closeDriver();
					setDriver(null);
//                AppiumService.getInstance().closeApp();
				}
			}catch (Exception e){
				GeneralUtils.handleError("Error in close resources", e);
			}
		}

		public static void close(WebDriver driver)  {
			logger.info("Test on driver finished succssesfully");
			try {
				if(driver != null) {

//             driver.quit();
					driver.close();
				}
			}catch (Exception e){
				GeneralUtils.handleError("Error in close resources", e);
			}
		}

		private static void setDriver(AppiumDriver driver) {
			Appium.driver = driver;}

	}

	public enum DriverType{
		SELENIUM, APPIUM;
	}


}