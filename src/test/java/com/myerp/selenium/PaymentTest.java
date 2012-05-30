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

	
	// TODO : remplacer sleep
	// This test checks the banner root panel when we have a free plan and that the banner root panel is not present when we have a billing plan.
	public void testBannerRootPanel() throws Exception {
		login("/index.jsp#manage_subscription");
		Thread.sleep(3000);
		waitForElement("//div[@id=\"bannerRootPanel\"]//*");
		fillCB("unlimited","alex","4111111111111111");
		selenium.click("//div[@data-plan=\"unlimited\"]//div[starts-with(@id, 'gwt-uid-')]//button");
		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
		selenium.open("/home");
		Thread.sleep(2000);
		selenium.waitForPageToLoad("30000");
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

	
	
	// TODO : capable de changer de plan avec le bouton change plan.
	public void testCBChangeButton () throws Exception {
		login("/index.jsp#manage_subscription");
		Thread.sleep(3000);
		String plan[] = {"team", "plus"};
		fillCB("unlimited","alex","4111111111111111");
		selenium.click("//div[@data-plan='unlimited']//div[starts-with(@id, 'gwt-uid-')]//button");
		waitForElement("//div[@data-plan='unlimited']//div[starts-with(@id, 'gwt-uid-') and @style='display: none;']");
		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
		selenium.open("/index.jsp#manage_subscription");
		selenium.waitForPageToLoad("30000");
		Thread.sleep(8000);
		
		for (int i = 0; i < 2; i++) {
			
			waitForElement("//div[@data-plan='"+plan[i]+"']//button");
			selenium.click("//div[@data-plan='"+plan[i]+"']//button");
			waitForElement("//div[@data-plan='"+plan[i]+"']//button[@disabled]");
			Thread.sleep(1000);
			
			// Check that the Name field for the CB is disabled.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input[@disabled]"));
			// Check that the Number Card field for the CB is disabled.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input[@disabled]"));
			// Check the the Expiration date is disabled.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[@disabled]"));
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2]");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2][@disabled]"));

			// Click on the button change plan.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div/div/div");
			mouse("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div/div/div");
		
			
			// Check that the Name field for the CB is enabled and not empty.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input");
			assertFalse(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input[@disabled]"));
			Thread.sleep(2000);
			System.out.println(selenium.getText("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input"));
			assertEquals(selenium.getText("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input"),"");
			// Check that the Number Card field for the CB is enabled and not empty.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input");
			assertFalse(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input[@disabled]"));
			assertEquals(selenium.getText("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input"),"");
			// Check that the month of expiration for the CB is enabled.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select");
			assertFalse(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[@disabled]"));
			// Check that the year of expiration for the CB is enabled.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2]");
			assertFalse(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2][@disabled]"));
			
			// Click on the button change plan.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div/div/div");
			mouse("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div/div/div");
			
			// Check that the Name field for the CB is disabled.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span/input[@disabled]"));
			// Check that the Number Card field for the CB is disabled.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input");			
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input[@disabled]"));
			// Check the the Expiration date is disabled.
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[@disabled]"));
			waitForElement("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2]");
			assertTrue(selenium.isElementPresent("//div[@data-plan='"+plan[i]+"']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2][@disabled]"));
			}
	}


	// This test checks the prorata when a customer is moving to another plan.
	public void testProrata() throws Exception {
		login("/index.jsp#manage_subscription");
		Thread.sleep(3000);
		fillCB("team","alex","4111111111111111");
		selenium.click("//div[@data-plan='team']//div[starts-with(@id, 'gwt-uid-')]//button");
		waitForElement("//div[@data-plan='team']//div[starts-with(@id, 'gwt-uid-') and @style='display: none;']");
		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
		selenium.open("/index.jsp#manage_subscription");
		selenium.waitForPageToLoad("30000");
		Thread.sleep(8000);
		Calendar cal = Calendar.getInstance();
		int daysinmonth = cal.getActualMaximum(((new Date()).getDay()));
		int remainingdays = daysinmonth - ((new Date()).getDay());
		int price = Integer.parseInt((selenium.getText("//body/div/div/div/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[4]/div/span")).substring(1));
		int ratio = price*remainingdays/daysinmonth;
		System.out.println(ratio);
		System.out.println(daysinmonth);System.out.println(remainingdays);System.out.println(price);
	}
	
	@Test 
	// This test allows to see if it is possible to ad 6 users or more with an Unlimited plan
		public void testAdUserUnlimitedPlan () throws Exception {
				// Subscribe for an unlimited plan
				login("/index.jsp?#manage_subscription");
				Thread.sleep(1000);
				fillCB("unlimited", "John", "4111111111111111" );
				selenium.click("//button[@class=\"btn btn-info\"]");
				waitForElement("//body/div/div/div/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[2]/div/div/div[6]/div[3]/div/span[2]");
				Thread.sleep(3000);
				selenium.click("//button[@type=\"button\"]");
				Thread.sleep(1000);
				selenium.open("/index.jsp?#manage_users");
				Thread.sleep(1000);

				for (int i = 0; i < 6; i++)
				{
				waitForElement("//div[@class=\"label\"]");
				long time = time();	
				Thread.sleep(3000);	
				selenium.click("//body/div/div/div/div[3]/div/div/div/div/div/div[3]/button");
				//selenium.mouseDown("//body/div/div/div/div[3]/div/div/div/div/div/div[3]/button");
				//selenium.mouseUp("//body/div/div/div/div[3]/div/div/div/div/div/div[3]/button");
				Thread.sleep(1000);
				selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div[4]/div/div[1]/input","Alex"+time+"");
				selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div[4]/div/div[2]/input","alex"+time+"@gmail.com");
				selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div[4]/div/div[3]/div/input","qwerty");
				selenium.click("//button[@type=\"button\"]");
				Thread.sleep(1000);
				selenium.open("#manage_users");
				Thread.sleep(1000);	
				}
		}	

	//This test allows to see if it is possible to ad 5 users and no more with a Team plan
		public void testAdUserTeamPlan () throws Exception {
				// Subscribe for a team plan
				login("/index.jsp?#manage_subscription");
				Thread.sleep(1000);
				fillCB("team", "John", "4111111111111111" );
				Thread.sleep(1000);
				selenium.click("//button[@type=\"button\"]");
				waitForElement("//body/div/div/div/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[6]/div[3]/div/span[2]");
				Thread.sleep(3000);
				selenium.click("//button[@type=\"button\"]");
				Thread.sleep(1000);
				selenium.open("/index.jsp?#manage_users");
				Thread.sleep(1000);

				for (int i = 0; i < 6; i++)
				{
				waitForElement("//div[@class=\"label\"]");
				long time = time();	
				Thread.sleep(3000);			
				selenium.click("//button[@class=\"GDOPQEJDPJ btn\"]");;
				Thread.sleep(1000);
				selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div[4]/div/div[1]/input","Alex"+time+"");
				selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div[4]/div/div[2]/input","alex"+time+"@gmail.com");
				selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div[4]/div/div[3]/div/input","qwerty");
				selenium.click("//button[@type=\"button\"]");
				Thread.sleep(1000);
				selenium.open("#manage_users");
				Thread.sleep(1000);	
				}
		}	
}
