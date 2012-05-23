package com.myerp.selenium;

import org.junit.*;
import java.util.*;

@SuppressWarnings("deprecation")

public class PaymentTest extends MyERPTestCase {

	// This test checks if the Manage Subscription appears in the research.
	// field.
	@Test
	public void testResearch() throws Exception {
		login("/login");
		waitForElement("//input[@class=\"gwt-TextBox placeholder\"]");
		selenium.type("css=input.gwt-TextBox", "manage");
		selenium.typeKeys("css=input.gwt-TextBox", " ");
		selenium.click("css=input.gwt-TextBox");
		waitForElement("//*[@data-uri=\"manage_subscription\"]");
	}

	
	// This test checks the banner root panel when we have a free plan.
	@Test
	public void testBannerRootPanelFree() throws Exception {
		login("/index.jsp#manage_subscription");
		waitForElement("//div[@id=\"bannerRootPanel\"]//*");
	}

	
	// TODO : remplacer sleep
	// This test checks that the banner root panel is not present when we have a billing plan.
	public void testBannerRootPanelNotFree() throws Exception {
		login("/index.jsp#manage_subscription");
		Thread.sleep(8000);
		fillCB("unlimited","alex","4111111111111111");
		selenium.click("//div[@data-plan='unlimited']//div[starts-with(@id, 'gwt-uid-')]//button");
		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
		assertFalse(selenium.isElementPresent("//div[@id='bannerRootPanel']//*"));
	}
	
	
	// This test checks that 4 plans are displayed.
	public void testFourPlansDisplayed() throws Exception {
		login("/index.jsp#manage_subscription");
		waitForElement("//div[@data-plan=\"free\"]");
		String plan[] = {"unlimited", "team", "plus", "free"};
		for (int i = 0; i < 4; i++) {
			assertTrue(selenium.isElementPresent("//div[@data-plan=\"" + plan[i] + "\"]"));
		}
	}
	
	
	// This test checks the enabilities and the disabilities of the plan buttons.
	public void testButtonPlan() throws Exception {
		login("/index.jsp#manage_subscription");
		String plan[] = {"unlimited", "team", "plus"};
		waitForElement("//div[@data-plan=\"free\"]//button");	
		assertTrue(selenium.isElementPresent("//div[@data-plan=\"free\"]//button[@disabled]"));
		for (int i = 0; i < 3; i++) {
			assertFalse(selenium.isElementPresent("//div[@data-plan=\"" + plan[i] + "\"]//button[@disabled]"));
			selenium.click("//div[@data-plan=\"" + plan[i] + "\"]//button");
			waitForElement("//div[@data-plan=\"" + plan[i] + "\"]//button[@disabled]");
			assertTrue(selenium.isElementPresent("//div[@data-plan=\"" + plan[i] + "\"]//button[@disabled]"));
		}
	}
	
	
	// TODO : remplacer sleep
	// This test checks that the CB fields are editable, enable and blank. It checks also that the fields become disable until click on button change.
	public void testCBField () throws Exception {
		login("/index.jsp#manage_subscription");
		Thread.sleep(8000);
		String plan[] = {"unlimited", "team", "plus"};
		for (int i = 0; i < 3; i++) {
			waitForElement("//div[@data-plan='"+plan[i]+"']//button");
			selenium.click("//div[@data-plan='"+plan[i]+"']//button");
			Thread.sleep(2000);
			waitForElement("//div[@data-plan='"+plan[i]+"']//div[starts-with(@id, 'gwt-uid-') and @style!='display:${nbsp}none;']");
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input");
			assertEquals(selenium.getText("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input"),"");
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input");
			assertEquals(selenium.getText("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input"),"");
			assertEquals(selenium.getSelectedIndex("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select"),(new Date()).getMonth());
			assertEquals(selenium.getSelectedLabel("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2]"),(new Date()).getYear()+1900);
			assertTrue(selenium.isChecked("//div[@data-plan='"+plan[i]+"']/div[6]/div[2]/div/span/input"));
		}
	}

	
	
	// TODO : capable de changer de plan avec le bouton change plan
	public void testCBChangeButton () throws Exception {
		login("/index.jsp#manage_subscription");
		Thread.sleep(8000);
		String plan[] = {"team", "plus"};
		fillCB("unlimited","alex","4111111111111111");
		selenium.click("//div[@data-plan='unlimited']//div[starts-with(@id, 'gwt-uid-')]//button");
		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
		selenium.open("/index.jsp#manage_subscription");
		Thread.sleep(8000);
		
		for (int i = 0; i < 2; i++) {
			
			waitForElement("//div[@data-plan='"+plan[i]+"']//button");
			selenium.click("//div[@data-plan='"+plan[i]+"']//button");
			waitForElement("//div[@data-plan='"+plan[i]+"']//button[@disabled]");
			Thread.sleep(1000);
			
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input[@disabled='']"));
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input[@disabled='']"));
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[@disabled='']"));
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2]");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2][@disabled='']"));

			waitForElement("//div[@data-plan='"+plan+"']/div[6]/div/div[2]/div/div/div/div");
			selenium.click("//div[@data-plan='"+plan+"']/div[6]/div/div[2]/div/div/div/div");
			
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input");
			assertFalse(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input[@disabled='']"));
			assertNotEquals(selenium.getText("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input"),"");
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input");
			assertFalse(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input[@disabled='']"));
			assertEquals(selenium.getText("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input"),"");
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select");
			assertFalse(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[@disabled='']"));
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2]");
			assertFalse(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2][@disabled='']"));
			
			waitForElement("//div[@data-plan='"+plan+"']/div[6]/div/div[2]/div/div/div/div");
			selenium.click("//div[@data-plan='"+plan+"']/div[6]/div/div[2]/div/div/div/div");
			
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input[@disabled='']"));
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input[@disabled='']"));
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[@disabled='']"));
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2]");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2][@disabled='']"));
			}
	}
	
@Test 
	public void testAdUser () throws Exception {
		login("/index.jsp?#manage_users");
		waitForElement("//div[@class=\"label\"]");
		Thread.sleep(1000);
		
		
		if (selenium.isElementPresent("//body/div/div/div/div[2]/div/div/div/div/div/div/a")) {
			assertTrue(selenium.isElementPresent("//body/div/div/div/div[2]/div/div/div/div/div/div[3]/button[@disabled]"));
		
		} 
		else {
			int i;
			for (i=1; i<7 ; i++) {
			
			assertFalse(selenium.isElementPresent("//body/div/div/div/div[2]/div/div/div/div/div/div[3]/button[@disabled]"));
				
			selenium.click("//body/div/div/div/div[3]/div/div/div/div/div/div[3]/button");
			selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div[4]/div/div[1]/input","Alex"+i+"");
			selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div[4]/div/div[2]/input","alex"+i+"@gmail.com");
			selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div[4]/div/div[3]/div/input","qwerty");
			selenium.click("//body/div/div/div/div[3]/div/div/div/div/div/div[4]");
			
			}
		}		
	}	

	// TODO : verifier le prorata
	
	
		
}
