package com.myerp.selenium;

import javax.print.DocFlavor.STRING;

import org.junit.*;

import com.thoughtworks.selenium.*;
import com.testingbot.*;

public abstract class MyERPTestCase extends SeleneseTestCase {

	protected Selenium selenium;
	
	@BeforeClass
	public void setUp() throws Exception {
		final boolean testingbot = "testingbot".equals(System.getProperty("tool"));
		String website = System.getProperty("website");
		website = (website == null) ? "https://pp.myerp.com" : website;

		if (testingbot) {
			selenium = new TestingBotSelenium(
		            "hub.testingbot.com",
		            4444,
		            "firefox",
			    website);
			selenium.start("version=10;platform=WINDOWS;screenrecorder=false");
    
		    	// print sessionID in output so that our Jenkins plugin maps the sessionID to videos/screenshots
			System.out.println("TestingBotSessionID=" + selenium.getEval("selenium.sessionId"));
		} else {
			selenium = new DefaultSelenium(
			"localhost",
			4444, 
			"*firefox", 
			website);
			selenium.start();
		}
		
	}
	
	//This function allows to login on the url you want.
	public void login(String page) throws Exception{
		selenium.open(page);
		assertEquals("Sign In | myERP.com", selenium.getTitle());
		selenium.type("//*[@id=\"userUsername\"]", "alex.myerp@gmail.com");
		selenium.fireEvent("//*[@id=\"userUsername\"]", "blur");
		selenium.type("//*[@id=\"userPassword\"]", "311088");
		selenium.fireEvent("//*[@id=\"userPassword\"]", "blur");
		selenium.click("css=input[type=\"image\"]");
		selenium.waitForPageToLoad("30000");
	}
	
	//This function allows to wait for an element to be present.
	public void waitForElementPresent(String locator) throws Exception {
		int i;
		for (i=0; i<50 ; i++) {
			if (selenium.isElementPresent(locator)) {	
				break;
			}
			else {
				Thread.sleep(50);
				if (i==49) {
					Assert.fail("Error: Element Not Found");
				}
				else { }
			}
			}
		}
	
	//This function allows to wait for a text to be present on the page.
	public void waitForTextPresent(String text) throws Exception {
		int i;
		for (i=0; i<50 ; i++) {
			if (selenium.isElementPresent(text)) {	
				break;
			}
			else {
				Thread.sleep(50);
				if (i==49) {
					Assert.fail("Error: Text Not Found");
				}
				else { }
			}
			}
		}
		
	@AfterClass
	public void tearDown() throws Exception {
		selenium.stop();
	}
}

