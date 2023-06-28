package org.util;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FunctionalLib {

	public static WebDriver driver;

	public void browserLaunch() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void launchUrl(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			System.out.println("Invalid URL");
		}
	}

	public void insertText(WebElement element, String text) {
		try {
			element.sendKeys(text);
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
		} catch (Exception e) {
			e.printStackTrace();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
		}
	}

	public void btnClick(WebElement element) {
		try {
			element.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			e.printStackTrace();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", element);
		}
	}

	public boolean checkingURL(String url) {

		if (driver.getCurrentUrl().contains(url)) {

			return true;
		}

		return false;
	}

	public void selectByText(WebElement element, String text) {
		try {
			Select se = new Select(element);
			se.selectByVisibleText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getText(WebElement element) {
		String text = "";
		try {
			text = element.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return text;
	}

	public void tkScreenshot(String name) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotAs, new File(name + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
