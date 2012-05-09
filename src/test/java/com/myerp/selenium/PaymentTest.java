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
	
	
	
}
		
	

