package com.myerp.selenium;

import org.junit.*;
import com.thoughtworks.selenium.*;
import com.testingbot.*;
import java.util.*;

@SuppressWarnings("deprecation")
public abstract class MyERPTestCase extends SeleneseTestCase {

	protected Selenium selenium;
	

	@BeforeClass
	public void setUp() throws Exception {
		final boolean testingbot = "testingbot".equals(System.getProperty("tool"));
		String website = System.getProperty("website");
		website = (website == null) ? "https://pp.myerp.com" : website;
		
		if (testingbot) {
			selenium = new TestingBotSelenium("hub.testingbot.com", 4444,"firefox", website);
			selenium.start("version=10;platform=WINDOWS;screenrecorder=false");

			// print sessionID in output so that our Jenkins plugin maps the
			// sessionID to videos/screenshots
			System.out.println("TestingBotSessionID="+ selenium.getEval("selenium.sessionId"));
		} else {
			selenium = new DefaultSelenium("localhost", 4444, "*firefox",website);
			selenium.start();
		}

	}
	
	/** This function permits to give the date in millisecond in order to create a new account for every test.*/
	public long time() {
		Calendar cal = Calendar.getInstance();
		long t = cal.getTimeInMillis();
		return t;
		}

	/** This function allows to login on the url you want. It creates a new account at every login. */
	public void login(String page) throws Exception {
		long time = time();
		selenium.open("/account-creation?locale=en_US&email=test"+time+"@myerp.com&company_name=myerp&name=Tester&password=qwerty&openid_provider=none&remote_addr=127.0.0.1&openid_claimedId&billing");
		selenium.waitForPageToLoad("5000");
		selenium.open(page);
		assertTrue(selenium.getTitle().endsWith("myERP.com"));
		for (int i = 0; i < 30; i++) {
			if (selenium.isElementPresent("//*[@id=\"userUsername\"]")) {
				selenium.type("//*[@id=\"userUsername\"]", "test"+time+"@myerp.com");
				selenium.fireEvent("//*[@id=\"userUsername\"]", "blur");
				selenium.type("//*[@id=\"userPassword\"]", "qwerty");
				selenium.fireEvent("//*[@id=\"userPassword\"]", "blur");
				selenium.click("//body/div/div[2]/form/div[3]/input");
				selenium.waitForPageToLoad("20000");
				}
			else {
				break;
				}
			}
	}

	/** This function permits to fill the CB field to choose a plan. */
	public void fillCB(String plan, String name, String number ) throws Exception {
		waitForElement("//div[@data-plan='"+plan+"']//button");
		selenium.click("//div[@data-plan='"+plan+"']//button");
		waitForElement("//div[@data-plan='"+plan+"']//button[@disabled]");
		Thread.sleep(1000);
		waitForElement("//div[@data-plan='"+plan+"']//div[starts-with(@id, 'gwt-uid-') and @style!='display:${nbsp}none;']");
		selenium.type("//div[@data-plan='"+plan+"']/div[6]/div/div[2]/div/div[2]/div/span/input",""+name+"");
		selenium.fireEvent("//div[@data-plan='"+plan+"']/div[6]/div/div[2]/div/div[2]/div/span/input","blur");
		selenium.type("//div[@data-plan='"+plan+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input",""+number+"");
		selenium.fireEvent("//div[@data-plan='"+plan+"']/div[6]/div/div[2]/div/div[2]/div/span[2]/input","blur");
	}
	
	/** This command permits to wait for an element defines by its locator to be present. */
	public void waitForElement(String locator) throws Exception {
		for (int i = 0; i < 50; i++) {
			if (selenium.isElementPresent(locator)) {
				return;
			}
			Thread.sleep(100);
		}
		Assert.fail("Error: " + locator + " Not Found");
	}
	
	/** This command permits to wait for a text in the page to be present. Rather use waitForElement. */
	public void waitForText(String text) throws Exception {
		for (int i = 0; i < 50; i++) {
			if (selenium.isTextPresent(text)) {
				return;
			}
			Thread.sleep(100);
		}
		Assert.fail("Error: " + text + " Not Found");
	}

	/** This command permits to wait for an element to be present. This command works in very particular situations. */
	public void waitVisibleElement(String locator) throws Exception {
		for (int i = 0; i < 50; i++) {
			if (selenium.isVisible(locator)) {
				return;
			}
			Thread.sleep(100);
		}
		Assert.fail("Error: " + locator + " Not Found");
	}

	/**This function permits create a new project.*/
	public void NewProject() throws Exception {
		login("/index.jsp#project/new");	
	// Name :
	for (int second = 0;; second++) {
		if (second >= 60) fail("timeout");
		try { if (selenium.isTextPresent("Name")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.type("//*[@data-id=\"v192168001112_1308844562694_404\"]", "Project ,;:!?..§");
		selenium.fireEvent("//*[@data-id=\"v192168001112_1308844562694_404\"]", "blur");
	// Description :
		selenium.type("//*[@data-id=\"v192168001112_1308844670776_425\"]", "This is project 2");
		selenium.fireEvent("//*[@data-id=\"v192168001112_1308844670776_425\"]", "blur");
	// Record and exit :
		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
	}
	
	/**This function permits create a new task.*/
	public void NewTask() throws Exception {
		login("/index.jsp#task/new");
	// Filling Text :
	for (int second = 0;; second++) {
		if (second >= 60) fail("timeout");
		try { if (selenium.isTextPresent("Subject")) break; } catch (Exception e) {}
		Thread.sleep(1000);
		}
		selenium.type("//*[@class=\"gwt-TextBox\"]", "Task 1");
		selenium.fireEvent("//*[@class=\"gwt-TextBox\"]", "blur");
	// Description :
		selenium.type("//*[@class=\"gwt-TextArea\"]", "This is task 1");
		selenium.fireEvent("//*[@class=\"gwt-TextArea\"]", "blur");
		selenium.click("//*[@data-id=\"v192168000062_1316012654315_6310\"]");
		selenium.type("xpath=(//input[@type='text'])[6]", "12.00");
		selenium.click("css=input.gwt-DateBox");
		selenium.click("css=body.gecko1_8 div.dateBoxPopup div.popupContent table.gwt-DatePicker tbody tr td table.datePickerDays tbody tr td.datePickerDay");
	// Record and exit:
		selenium.click("//*[@class=\"GDOPQEJDJJ\"]");
	}
		
	/**This function permits create a new customer.*/
	public void NewCustomer() throws Exception {
		login("/index.jsp#customer/new");
	// Filling Text :
	for (int second = 0;; second++) {
		if (second >= 60) fail("timeout");
		try { if (selenium.isTextPresent("Company Name")) break; } catch (Exception e) {}
		Thread.sleep(1000);
		}
		
		selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div/div[2]/div/div/div/div[2]/input", "Company 1");
		selenium.fireEvent("//body/div/div/div/div[3]/div/div/div/div/div/div/div[2]/div/div/div/div[2]/input", "blur");
	// Description :
		selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div/div[3]/div/div/div/div[2]/input", "Address customer 1");
		selenium.fireEvent("//body/div/div/div/div[3]/div/div/div/div/div/div/div[3]/div/div/div/div[2]/input", "blur");
		selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div/div[3]/div/div/div[4]/div[2]/input", "San Francisco");
		selenium.click("//body/div/div/div/div[3]/div/div/div/div/div/div/div[3]/div/div/div[5]/div[2]/div/div/select");
		selenium.click("//body/div/div/div/div[3]/div/div/div/div/div/div/div[3]/div/div/div[5]/div[2]/div/div/select/option[2]");
		selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div/div[3]/div/div/div[6]/div[2]/input","94102");
		selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div/div[3]/div/div/div[7]/div[2]/input","USA");
		selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div/div[6]/div/div/div/div[2]/input","4158990403");
		selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div/div[6]/div/div/div[2]/div[2]/input","azerty@gmail.com");
		selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div/div[6]/div/div/div[3]/div[2]/input","www.myerp.com");
		//Confirm creation
		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
	}
		
	@AfterClass
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
