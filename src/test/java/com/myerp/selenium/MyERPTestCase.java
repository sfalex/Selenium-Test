package com.myerp.selenium;

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

	@AfterClass
	public void tearDown() throws Exception {
		selenium.stop();
	}
}

