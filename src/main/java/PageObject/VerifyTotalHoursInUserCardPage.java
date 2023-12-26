package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Utility.ExplicitWaits;
import Utility.pageFactory;

public class VerifyTotalHoursInUserCardPage extends pageFactory {

	public WebDriver driver;
	public ExplicitWaits wt;
	public String[] totalHours;
	public boolean userPresent = true;
	public boolean value;

	public VerifyTotalHoursInUserCardPage(WebDriver driver) {
		super(driver);
		wt = new ExplicitWaits(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//span[text()='Activity']")
	WebElement activity;

	public void selectTitle(String title) throws InterruptedException {
		wt.waitForVisibility(driver.findElement(By.xpath("//span[text()='" + title + "']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='" + title + "']")).click();
		Thread.sleep(3000);
	}

	public void viewScreenshot(String name) throws InterruptedException {
		try {
			value = driver
					.findElement(By.xpath("//p[text()='" + name
							+ "']/../../following-sibling::div//p[text()='Total Hours']/following-sibling::p"))
					.isDisplayed();
		} catch (Exception e) {
			value = false;
		}
		if (value) {
			String hours = driver
					.findElement(By.xpath("//p[text()='" + name
							+ "']/../../following-sibling::div//p[text()='Total Hours']/following-sibling::p"))
					.getText();
			totalHours = hours.split(" : ");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//p[text()='" + name + "']/../../a")).click();
		} else {
			userPresent = value;
		}
	}

	public void verifyHours() {
		if (userPresent) {
			wt.waitForVisibility(By.xpath("//p[text()='Activity']/following-sibling::div/p"));
			String actualHour = driver.findElement(By.xpath("//p[text()='Activity']/following-sibling::div/p"))
					.getText();
			String[] ActualHour = actualHour.split(" ");
			Assert.assertEquals(totalHours[0] + totalHours[1], ActualHour[2] + ActualHour[5] + " hrs");
		} else {
			System.out.println("User not found !!!");
		}
	}

}
