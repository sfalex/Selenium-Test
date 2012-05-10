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
	
	//TODO 
	//This test checks the banner root panel when we have a free plan.
	@Test 
	public void testBannerRootPanel () throws Exception {
		login("/index.jsp?locale=en_US#manage_subscription");
		waitForElementPresent("//div[@id=\"bannerRootPanel\"]");
		if (selenium.isElementPresent("//*[@class=\"GJG0VAMCMO\"]")) {
		// This command clicks on the link Upgrade Now.
			selenium.click("//*[@class=\"GJG0VAMCMO\"]");
		} 
		else {
		// These instructions permit to subscribe to a free plan to check the banner root panel.
		waitForElementPresent("//div[@data-plan=\"free\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button[@class=\"btn\"]");
		selenium.click("//div[@data-plan=\"free\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button[@class=\"btn\"]");
		//selenium.mouseOver("//div[@data-plan=\"free\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button[@class=\"btn\"]");
		//selenium.mouseUp("//div[@data-plan=\"free\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button[@class=\"btn\"]");
		//selenium.mouseDown("//div[@data-plan=\"free\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button[@class=\"btn\"]");
		waitForElementPresent("//div[@id=\"gwt-uid-285\"]//div[@class=\"GJG0VAMCBT\"]//div[@class=\"GJG0VAMCAT\"]//button[@class=\"btn btn-info\"]");
		selenium.click("//div[@id=\"gwt-uid-285\"]//div[@class=\"GJG0VAMCBT\"]//div[@class=\"GJG0VAMCAT\"]//button[@class=\"btn btn-info\"]");
		waitForElementPresent("//div[@class=\"GJG0VAMCGJ\"]//button[@class=\"GJG0VAMCJJ\"]");
		selenium.click("//div[@class=\"GJG0VAMCGJ\"]//button[@class=\"GJG0VAMCJJ\"]");
		waitForElementPresent("//div[@id=\"bannerRootPanel\"]//div[@class=\"GJG0VAMCLO\"]//*[@class=\"GJG0VAMCMO\"]");
		selenium.click("//div[@id=\"bannerRootPanel\"]//div[@class=\"GJG0VAMCLO\"]//*[@class=\"GJG0VAMCMO\"]");
		selenium.waitForPageToLoad("30000");	
		}
	}
  
	//This test checks that the four plans are displayed in Manage Subscription.
	@Test
	public void testDisplayedPlan () throws Exception {
		login("/index.jsp?locale=en_US#manage_subscription");
		waitForElementPresent("//div[@data-plan=\"free\"]");
		String plan;
		String PLAN;
		plan = "unlimited";
		//System.out.println(selenium.getText("//div[@data-plan=\""+plan+"\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]"));
		if (selenium.getText("//div[@data-plan=\""+plan+"\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]").equals("Choose Plan $169 /month")) {
			selenium.click("//div[@data-plan=\"unlimited\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button");
			selenium.mouseOver("//div[@data-plan=\""+plan+"\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button");
			selenium.mouseUp("//div[@data-plan=\""+plan+"\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button");
			selenium.mouseDown("//div[@data-plan=\""+plan+"\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button");
			waitForTextPresent("You are moving to the "+plan.toUpperCase()+" plan");
			selenium.goBack();
		}
		if (selenium.getText("//div[@data-plan=\""+plan+"\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]") == "Current Plan") {
			selenium.click("//div[@data-plan=\""+plan+"\"]//div[@class=\"GJG0VAMCIR GJG0VAMCMR\"]//button[@class=\"btn\"]");
			selenium.goBack();
		}
		else { 
			Assert.fail("Button error");
		}
	}
}
	


	

