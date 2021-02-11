package com.google;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BrowserSetUp {
	WebDriver driver;
	
	public void browserSetUp() {

		Properties prop = new Properties();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browser = prop.getProperty("Browser");

		//settingUp the driver 
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if(browser.equalsIgnoreCase("Opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}else if(browser.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}else {
			System.out.println("Requested Browser is not supported,\n Please choose one of the following :"
					+"\n Chrome \n Firefox \n Edge \n Opera \n IE");
			System.exit(0);
		}
	
		//Maximizing the browser, deleting the cookies & Adding the Implicit wait
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String url = prop.getProperty("Url");
		//checking if url is empty
		if(url != "")
			driver.get(url);
		else
			driver.get("about:blank");
	}


	//closing the browser		
	public void quitBrowser() {
		driver.quit();
	}

	public void closeCurrWindow() {
		driver.close();
	}
}
