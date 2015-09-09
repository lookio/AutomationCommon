
package com.ui.service;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.test_base.AppiumScriptHandler;
import com.ui.page.base.BasePage;
import com.ui.service.drivers.Drivers;
import com.util.genutil.GeneralUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumService extends UIService<WebElement, WebDriver> {

	private static volatile WebDriver driver = null;
	private static final SeleniumService SELENIUM_SERVICE_INSTANCE = new SeleniumService();
	private static final Logger logger = Logger.getLogger(SeleniumService.class);
	private static String originalHandle;
//	public UIService<WebElement, WebDriver> baseService = new UIService<>();


	public static SeleniumService getInstance() {
		return SELENIUM_SERVICE_INSTANCE;
	}

	private SeleniumService(){

	}

	public final void setDriver(Drivers browser, AppiumScriptHandler.Machine machine) {
		try {
			driver = Drivers.Selenium.setBrowserToDriver(browser, machine);
			this.setDriver(driver);
			logger.info("Setting browser to driver finished successfully. \n\t");
		} catch (Exception ex) {
			logger.error("Problem in setting browser to driver ", ex);
			Assert.assertTrue(browser.name() + " driver was not created", false);
		}
	}

	public final void setDriver(WebDriver _driver) {
		driver = _driver;
	}

	public final void closeDriver() {
		super.setDriver(driver);
		super.closeDriver();
	}

	@Override
	public final WebElement findElement(By by, String elementName) {
		super.setDriver(driver);
		return super.findElement(by, elementName);
	}

	@Override
	public final WebElement findElement(By by) throws Exception {
		super.setDriver(driver);
		return super.findElement(by);
	}

	@Override
	public final synchronized void explicitWait(long explicitWait, By by) {
		super.setDriver(driver);
		super.explicitWait(explicitWait, by);
	}

	@Override
	public final synchronized void implicitWait(long implicitWait, TimeUnit time) {
		super.setDriver(driver);
		super.implicitWait(implicitWait, time);
	}

	public boolean isElementVisible(WebElement element) {
		super.setDriver(driver);
		return super.isElementVisible(element);
	}

	public final synchronized void implicitWait(long implicitWait) {
		super.setDriver(driver);
		super.implicitWait(implicitWait);
	}

	@Override
	public final boolean IsElementPresent(By by) {
		super.setDriver(driver);
		return super.IsElementPresent(by);
	}

	@Override
	public <T extends BasePage> boolean initElement(T pageObject) {
		super.setDriver(driver);
		return super.initElement(pageObject);
	}

	@Override
	public final void openBrowser(String url) {
		super.setDriver(driver);
		super.openBrowser(url);
	}

	@Override
	public final void closeBrowser() throws Exception {
		super.setDriver(driver);
		super.closeBrowser();
	}


	public void executeScript(String script) {
		try {
			((JavascriptExecutor) getDriver()).executeScript(script);
			implicitWait(1500);
			logger.info("Script " + script + " performed successfully");
		} catch (Exception e) {
			logger.info("Script " + script + " failed");
		}
	}

	public void scrollAndClick(By by) {
		try {
			//	    getDriver().findElement(by).click();
			WebElement elem = getDriver().findElement(by);
			logger.info("Scrolling to x : " + elem.getLocation().x + "Scrolling to y : " + elem.getLocation().y);
			((JavascriptExecutor) getDriver()).executeScript("window.scroll(" + elem.getLocation().x + "," + elem.getLocation().y + ")");
			elem.click();
		} catch (Exception e) {
			logger.info("No clickable");
		}
	}


	public WebElement getDisplaiedElemFromList(By elementsLoc){
		List<WebElement> elems = getDriver().findElements(elementsLoc);
		for(WebElement elem : elems){
			if(elem.isDisplayed()){
				return elem;
			}
		}
		logger.error("Couldn't find elements displaied");
		return null;
	}

	public WebElement getElementByText(By listLocator, String text){
		super.setDriver(driver);
		return super.getElementByText(listLocator, text);
	}


	public boolean clickDisplaiedElement(List<WebElement> elems){
		for(WebElement elem : elems){
			if(elem.isDisplayed()){
				elem.click();
				return true;
			}
		}
		return false;
	}


	public boolean isMessage(String msg){
		try{
			By msgBy = By.xpath("//*[contains(text(),'" + msg + "')]");
			implicitWait(1500);
			WebElement elem = findElement(msgBy);
			logger.info("Message " + msgBy + " was found");
			if(elem.isDisplayed()){
				return true;
			}
			return false;
		}
		catch(Exception e){
			GeneralUtils.handleError("Can't find message", e);
			return false;
		}
	}

	public static void wait(int seconds) {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() < startTime + seconds * 1000) {
		}
	}

	public void blur(String elementId) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("$('#" + elementId + "').blur();");
	}

	public void click(String elementId) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("$('#" + elementId + "').click();");
	}

	public void sendKeys(String elementId, String value) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("$('#" + elementId + "').val('" + value + "');");
	}

	public static void uploadFile(WebElement element, String filePath, String fileInputNum, String idOfFileInputButton) {

		JavascriptExecutor js = (JavascriptExecutor) SeleniumService.getInstance().getDriver();
		js.executeScript("arguments[0].click()", element);
		if (fileInputNum == null) {
			SeleniumService.getInstance().getDriver().findElement(By.id(idOfFileInputButton)).sendKeys(filePath);
		} else {
			SeleniumService.getInstance().getDriver().findElement(By.id(idOfFileInputButton + fileInputNum)).sendKeys(filePath);

		}
	}

	private Robot initRobot(String filePath){
		implicitWait(5000);
		StringSelection selection = new StringSelection(new File(filePath).getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		Robot robot = null;
		try {
			return new Robot();
		} catch (AWTException e) {
			GeneralUtils.handleError("Problem starting robot", e);
		}
		return robot;
	}

	public static void clickWebElement(WebElement weElement, WebDriver wdDriver) {

		((JavascriptExecutor) wdDriver).executeScript("window.scrollTo(0," + weElement.getLocation().y + ")");
		// Click the element
		int iAttempts = 0;
		while (iAttempts < 5) {
			try {
				weElement.click();
				break;
			} catch (Exception e) {
			}
			iAttempts++;
		}

	}


	public int getNumberOfTabs(){
		return getDriver().getWindowHandles().size();
	}

	public static void switchTabs(int tabNumber) throws Exception {

		logger.info("Switching to tab number " + tabNumber);

		originalHandle = SeleniumService.getInstance().getDriver().getWindowHandle();

		Set<String> allWindowHandles = SeleniumService.getInstance().getDriver().getWindowHandles();
		String newTab = (String) allWindowHandles.toArray()[tabNumber];
		SeleniumService.getInstance().getDriver().switchTo().window(newTab);

		logger.info("Switching to tab is succsussfull");

	}

	public static void closeTab() throws Exception {

		logger.info("Start closing the tab driver is currently presenting");

		for (String handle : SeleniumService.getInstance().getDriver().getWindowHandles()) {

			if (!handle.equals(originalHandle)) {
				SeleniumService.getInstance().getDriver().switchTo().window(handle);
				SeleniumService.getInstance().getDriver().close();
				logger.info("Tab closed");
			}

		}
		logger.info("Switching back to main window, handle value : " + originalHandle);
		SeleniumService.getInstance().getDriver().switchTo().window(originalHandle);

	}

	public static String parseRandomKeyFromUrl(String url) {
		int keyIndex = url.indexOf("key=");
		int endIndex = url.indexOf('&', keyIndex);
		if (endIndex < 0) {
			endIndex = url.length();
		}
		return url.substring(keyIndex + 4, endIndex);
	}

	public WebDriver getDriver(){
		return driver;
	}
}
