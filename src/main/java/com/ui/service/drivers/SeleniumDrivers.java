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

import com.ui.service.SeleniumService;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import config.common.ui.service.SeleniumService;

public enum SeleniumDrivers {

	FIREFOX, IE, CHROME;

	private static final Logger logger = Logger.getLogger(SeleniumDrivers.class);

	public static final String START_URL = "https://staging.feex.co.il/";

	private volatile static WebDriver driver = null;

	public static Properties props = null;

	private static final String ENV_VARS_PROPERTY_FILE_PATH = "../../commons/src/test/resources/env.properties";

	private static final String PROP_KEY_CHROME_DRIVER_NAME_PATH_NAME = "Chrome_driver_property_name";
	private static final String PROP_KEY_CHROME_DRIVER_NAME_PATH_VALUE = "Chrome_driver_path";
	private static final String PROP_KEY_IE_DRIVER_NAME_PATH_NAME = "IE_driver_property_name";
	private static final String PROP_KEY_IE_DRIVER_NAME_PATH_VALUE = "IE_driver_path";

	private static String browserType;
	static FirefoxProfile ffProfile = new FirefoxProfile();

	/**
	 * Set Browser To Driver.
	 *
	 * @param browser
	 *            The Enum browsers type.
	 * @throws java.net.MalformedURLException
	 */

	public static WebDriver setBrowserToDriver(SeleniumDrivers browser) throws MalformedURLException, Exception {
		browserType = browser.name();
		logger.info("Trying to set " + browserType + " browser to driver");
//		props = PropertiesHandlerImpl.getInstance().parse(ENV_VARS_PROPERTY_FILE_PATH);
		switch (browser) {
			case IE:
				createIEDriver();
				break;
			case CHROME:
				createChromeDriver();
				break;
			case FIREFOX:
				createFireFoxDriver();
				break;
		}
//		driver.manage().window().setSize(new Dimension(1800, 1150));
//		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		SeleniumService.getInstance().setDriver(driver);
		return driver;
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
		driver = new InternetExplorerDriver(capab);
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
		driver = new FirefoxDriver(ffProfile);
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

	private synchronized static void createChromeDriver() throws Exception {
		File file = new File(props.getProperty(PROP_KEY_CHROME_DRIVER_NAME_PATH_VALUE));
		System.setProperty(props.getProperty(PROP_KEY_CHROME_DRIVER_NAME_PATH_NAME), file.getAbsolutePath());
		driver = new ChromeDriver();
		logger.info("=========================================================");
		logger.info("============= Created New Chrome Driver =================");
		logger.info("=========================================================");
		logger.info("========== Starting New Test In Chrome Driver ===========");
		logger.info("=========================================================");
		logger.debug("Chrome driver was created");
	}

	/**
	 * Creates Chrome driver.
	 *
	 * @return String - The browser value
	 */

	public static String getBrowserType() {
		return browserType;
	}

	/**
	 * Start Chrome Creates Chrome driver Opens start URL
	 *
	 * @throws Exception
	 */

	public static void startChrome() throws Exception {
		SeleniumService.getInstance().setDriver(SeleniumDrivers.CHROME);
		SeleniumService.getInstance().openBrowser(START_URL);
	}

	/**
	 * Start FIRE FOX Creates FIRE FOX driver Opens start URL
	 *
	 * @throws Exception
	 */

	public static void startFIREFOX() throws Exception {
		SeleniumService.getInstance().setDriver(SeleniumDrivers.FIREFOX);
		SeleniumService.getInstance().openBrowser(START_URL);
	}

	/**
	 * Start IE Creates IE driver Opens start URL
	 *
	 * @throws Exception
	 */

	public static void startIE() throws Exception {
		SeleniumService.getInstance().setDriver(SeleniumDrivers.IE);
		SeleniumService.getInstance().openBrowser(START_URL);
	}

	/**
	 * Start Chrome with time out Creates Chrome driver Opens start URL
	 *
	 * @throws Exception
	 */

	public static void startChromeWithTimeOut(int timeOutInSeconds) throws Exception {
		SeleniumService.getInstance().setDriver(SeleniumDrivers.CHROME);
		SeleniumService.getInstance().openBrowser(START_URL);
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
	}

	/**
	 * Start FIRE FOX with time out Creates FIRE FOX driver Opens start URL
	 *
	 * @throws Exception
	 */

	public static void startFIREFOXWithTimeOut(int timeOutInSeconds) throws Exception {
		SeleniumService.getInstance().setDriver(SeleniumDrivers.FIREFOX);
		SeleniumService.getInstance().openBrowser(START_URL);
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);

	}

	/**
	 * Start IE with time out Creates IE driver Opens start URL
	 *
	 * @throws Exception
	 */

	public static void startIEWithTimeOut(int timeOutInSeconds) throws Exception {
		SeleniumService.getInstance().setDriver(SeleniumDrivers.IE);
		SeleniumService.getInstance().openBrowser(START_URL);
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);

	}

	/**
	 * Close Close browser and driver Opens start URL
	 *
	 * @throws Exception
	 */

	public static void close() throws Exception {
		logger.info("Test on browser " + SeleniumDrivers.getBrowserType() + " finished succssesfully");
		SeleniumService.getInstance().closeBrowser();
		SeleniumService.getInstance().closeDriver();

	}


	public static void startBrowserByType(SeleniumDrivers browser) throws Exception {
		switch (browser) {
			case CHROME:
				startChrome();
				break;
			case FIREFOX:
				startFIREFOX();
				break;
			case IE:
				startIE();
				break;
			default:
				startFIREFOX();

		}

	}
}
