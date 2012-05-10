package com.myerp.selenium;

import org.junit.*;
import com.thoughtworks.selenium.*;
import com.testingbot.*;

public class PaymentTest extends MyERPTestCase {
		
	//This test checks if the Manage Subscription appears in the research field.
	@Test
	public void testResearch () throws Exception {
		login("/login");
		waitForElementPresent("//input[@class=\"gwt-TextBox placeholder\"]");
		selenium.type("css=input.gwt-TextBox","manage");
		selenium.typeKeys("css=input.gwt-TextBox"," ");
		selenium.click("css=input.gwt-TextBox");
		waitForElementPresent("//*[@data-uri=\"manage_subscription\"]");
	}

	
	//This test checks the banner root panel when we have a free plan.
	@Test 
	public void testBannerRootPanel () throws Exception {
		login("/index.jsp?locale=en_US#manage_subscription");
		waitForElementPresent("//div[@id=\"bannerRootPanel\"]");
		Thread.sleep(1000);
		if (selenium.isElementPresent("//div[@id=\"bannerRootPanel\"]//*")) {
		} 
		else {
		// These instructions permit to subscribe to a free plan to check the banner root panel.
		//Thread.sleep(10000);
		waitForElementPresent("//div[@id=\"gwt-uid-285\"]");
		selenium.click("//div[@data-plan=\"free\"]//*//button[@class=\"btn\"]");
		waitForElementPresent("//div[@data-plan=\"free\"]//*//button[@disabled]");
		selenium.click("//div[@id=\"gwt-uid-285\"]//*//button[@class=\"btn btn-info\"]");
		selenium.click("//button[@class=\"GJG0VAMCJJ\"]");
		selenium.refresh();
		selenium.waitForPageToLoad("30000");
		waitForElementPresent("//div[@id=\"bannerRootPanel\"]//*");
		}
	}
  
	
	
}
	


	

