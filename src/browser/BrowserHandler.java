package browser;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class BrowserHandler {

	private BrowserConfiguration browserConfiguration;
	private WebDriver driver;

	public BrowserConfiguration getBrowserConfiguration() {
		return browserConfiguration;
	}

	public void setBrowserConfiguration(BrowserConfiguration browserConfiguration) {
		this.browserConfiguration = browserConfiguration;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public BrowserResult openPage(String url) {
		BrowserResult browserResult = new BrowserResult();
		if (driver != null) {
			driver.get(url);
			browserResult.setSuccessfull(true);
		} else {
			browserResult.setSuccessfull(false);
		}
		return browserResult;
	}

	public BrowserResult getText(String selector) {
		BrowserResult browserResult = new BrowserResult();
		if (driver != null) {
			WebElement element = driver.findElement(By.cssSelector(selector));
			if (element != null) {
				browserResult.setData(element.getText());
				browserResult.setSuccessfull(true);
			} else {
				browserResult.setSuccessfull(false);
			}
		} else {
			browserResult.setSuccessfull(false);
		}
		return browserResult;

	}

	public BrowserResult click(String selector) {
		BrowserResult browserResult = new BrowserResult();
		if (driver != null) {
			try {
				WebElement element = driver.findElement(By.cssSelector(selector));
				if (element != null) {
					Actions action = new Actions(driver);
					JavascriptExecutor je = (JavascriptExecutor) driver;
					je.executeScript("arguments[0].scrollIntoView(false);", element);
					action.moveToElement(element).click().perform();
					browserResult.setSuccessfull(true);
				}
			} catch (Exception e) {
				browserResult.setSuccessfull(false);
			}
		} else {
			browserResult.setSuccessfull(false);
		}
		return browserResult;
	}

	public BrowserResult start() {
		String exePath = browserConfiguration.getExePath();
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		BrowserResult browserResult = new BrowserResult();
		browserResult.setSuccessfull(true);
		return browserResult;
	}

	public BrowserResult stop() {
		if (driver != null) {
			driver.close();
		}
		BrowserResult browserResult = new BrowserResult();
		browserResult.setSuccessfull(true);
		return browserResult;
	}

	public int getCount(String selector) {
		List<WebElement> list = driver.findElements(By.cssSelector(selector));
		int listSize = list.size();
		list.clear();
		return listSize;
	}

}
