package org.locator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.util.FunctionalLib;

public class HomePage extends FunctionalLib {

	public HomePage() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[contains(text(),'Welcome')]")
	private WebElement txtHomePage;

	@FindBy(xpath = "//a[contains(text(),'destination ')]")
	private WebElement linkClick;

	@FindBy(name = "fromPort")
	private WebElement dropFrom;

	@FindBy(name = "toPort")
	private WebElement dropTo;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btnFindFlight;

	public WebElement getTxtHomePage() {
		return txtHomePage;
	}

	public WebElement getLinkClick() {
		return linkClick;
	}

	public WebElement getDropFrom() {
		return dropFrom;
	}

	public WebElement getDropTo() {
		return dropTo;
	}

	public WebElement getBtnFindFlight() {
		return btnFindFlight;
	}

	public void verifyHomeText() {
		boolean displayed = getTxtHomePage().isDisplayed();
		if (displayed) {
			System.out.println("This is the Home Page of application ");
		}

	}

	public void clickOnHyperLink() {
		btnClick(getLinkClick());
		boolean checkingURL = checkingURL("vacation");
		if (checkingURL) {
			driver.navigate().back();
		} else {
			System.out.println("vacation is not displayed");
		}
	}

	public void purchaseTicket(String from, String to) {
		selectByText(getDropFrom(), from);
		selectByText(getDropTo(), to);
		btnClick(getBtnFindFlight());

		
	}

}
