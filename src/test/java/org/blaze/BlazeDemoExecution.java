package org.blaze;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.locator.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.util.FunctionalLib;

public class BlazeDemoExecution extends FunctionalLib {
	HomePage homePage;

	@BeforeClass
	public void beforeClass() {
		browserLaunch();
		launchUrl("https://blazedemo.com/index.php");
	}

	@AfterMethod
	public void aftMethod(Method m) {

		tkScreenshot(m.getName());

	}

	@Test (priority = 1)
	public void tc01() {
		homePage = new HomePage();
		homePage.verifyHomeText();
		homePage.clickOnHyperLink();

	}

	@Parameters({ "from", "to" })
	@Test(priority = 2)
	public void tc02(String from, String to) {
		homePage.purchaseTicket(from, to);
	}

	@Test(priority = 3)
	public void tc03() {
		List<WebElement> findElements = driver.findElements(By.xpath("//table//tr//td[6]"));
		List<Double> l = new ArrayList<Double>();
		List<String> l1 = new ArrayList<String>();
		for (int i = 0; i < findElements.size(); i++) {

			l.add(Double.parseDouble(findElements.get(i).getText().replace("$", "")));
			l1.add(findElements.get(i).getText());
		}

		String valueOf = String.valueOf(Collections.min(l));
		int index = 0;

		for (int i = 0; i < l1.size(); i++) {

			if (l1.get(i).replace("$", "").equals(valueOf)) {
				index = i + 1;
			}

		}

		WebElement btnClick = driver
				.findElement(By.xpath("(//table//tr//td//input[@type='submit'])[" + String.valueOf(index) + "]"));
		btnClick.click();
	}

	@Test(priority = 4)
	private void tc04() {
		WebElement totalPrice = driver.findElement(By.xpath("//p/em"));
		String text = getText(totalPrice);
		System.out.println(text);

	}

	@Test(priority = 5)
	private void tc05() {
		WebElement btnPurchase = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		btnClick(btnPurchase);

	}

	@Test(priority = 6)
	private void tc06() {
		WebElement txtId = driver.findElement(By.xpath("//table//tr[1]//td[2]"));
		String text = getText(txtId);
		System.out.println("Id :"+ text);
		driver.quit();
	}

}
