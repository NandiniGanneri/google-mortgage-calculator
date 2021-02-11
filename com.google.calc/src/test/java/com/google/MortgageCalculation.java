package com.google;

import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MortgageCalculation extends BrowserSetUp {
	
	public void mortgageCalculation(String principal, String Interest) {

		WebElement MortAmnt = driver.findElement(By.xpath("//input[@class='moqVDd RtrRsb']"));
		MortAmnt.clear();
		MortAmnt.sendKeys(principal);

		WebElement IntRate = driver.findElement(By.xpath("//input[@class='moqVDd VJAIFd']"));
		IntRate.clear();
		IntRate.sendKeys(Interest);

		for(int i=1;i<6;i++) {//starting from 1 as option at 0th position (10) is hidden behind the header after '40' is selected from dropdown - needs more research
			WebDriverWait wait=new WebDriverWait(driver, 2);
			driver.findElement(By.xpath("//div[@class='goog-inline-block goog-flat-menu-button-dropdown']")).click();
			WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//div[@class='goog-menuitem' and @id=':"+i+"']")));
			dropdownOption.click();
			
			String initialMonthlyAmnt = driver.findElement(By.xpath("//div[@class='SNbntc']")).getText();
			System.out.println("initialMonthlyAmnt "+initialMonthlyAmnt);
		}
		
		//		String tabName = driver.findElement(By.xpath("//div[@class='MAf3g']")).getText();
		//		if(tabName.equalsIgnoreCase("Maximum loan"))
		//			driver.findElement(By.xpath("//div[@class='MAf3g']")).click();
		//		String MaxLoanMonthlyAmnt = driver.findElement(By.xpath("//input[@class='moqVDd RtrRsb']")).getText();
		//		
		//		System.out.println("MaxLoanMonthlyAmnt "+MaxLoanMonthlyAmnt);
	}

	public void searchMortgageCal() {
		WebElement srchbox = driver.findElement(By.cssSelector("input[type='text']"));
		srchbox.sendKeys("Google Mortgage Calculator");
		srchbox.sendKeys(Keys.ENTER);
	}
	
	public static void main(String[] args) {
		MortgageCalculation obj = new MortgageCalculation();
		obj.browserSetUp();
		obj.searchMortgageCal();
		
		LinkedList<String> principalAmnt = new LinkedList<String>();
		
		principalAmnt.add("390000");
//		principalAmnt.add("390,000");
//		principalAmnt.add("390k");
		principalAmnt.add("00000");
//		principalAmnt.add("390000.987");
//		principalAmnt.add("asdf390000asdf");
		principalAmnt.add("%$#@390000)(*&");
//		principalAmnt.add("asdf");
//		principalAmnt.add("00.000");
//		principalAmnt.add("");
//		principalAmnt.add("qwsvghiytgfrdsnbvcs");

		LinkedList<String> InterestAmnt = new LinkedList<String>();
		InterestAmnt.add("2.5");
		InterestAmnt.add("~!@#$%^&*-+><:");
//		InterestAmnt.add("qwsvghiytgfrdsnbvcs");
//		InterestAmnt.add("2.512345");//rounding upto three decimal digits
//		InterestAmnt.add("002.0");
//		InterestAmnt.add("asdf2");
		InterestAmnt.add("234566789");//application should through an error/warning here, as NO interest rate cannot be such huge amount
//		InterestAmnt.add("!@#$%^2");
//		InterestAmnt.add("2%^&*(");
//		InterestAmnt.add("2.as098");
		InterestAmnt.add("2.as098.67yhj");
//		InterestAmnt.add("000.000");
//		InterestAmnt.add("qwer");
//		InterestAmnt.add("");

		//We could actually use data provider, a concept from TestNG for this testing 
		//And we can store all the data in Excel and read from it using Apache POI dependency or
		// We can use Scenario Outline with Examples keyword from Cucumber(BDD)
		
		for(String prinAmnt : principalAmnt) {
			for(String intrestAmnt : InterestAmnt) {
				obj.mortgageCalculation(prinAmnt, intrestAmnt);
			}
		}
		obj.quitBrowser();
	}
}
