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

	
	/** This function permits to do mouse Over, Down and Up. */
	public void mouse(String locator) throws Exception {
		selenium.mouseOver(locator);
		selenium.mouseDown(locator);
		selenium.mouseUp(locator);
	}
	
	
	/** This function allows to login on the url you want. It creates a new account at every login. */
	public void login(String page) throws Exception {
		long time = time();
		System.out.println(time);
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
        selenium.open("#project/new");
        waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div/div/div/div/div[2]/input");
    // Name :
        selenium.type("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div/div/div/div/div[2]/input", "Project 1");
        selenium.fireEvent("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div/div/div/div/div[2]/input", "blur");
    // Description :
        selenium.type("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div/div/div/div[2]/div[2]/textarea", "This is project 1");
        selenium.fireEvent("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div/div/div/div[2]/div[2]/textarea", "blur");
    // Record and exit :
        selenium.click("//div[@id=\"contentRootPanel\"]/div/div[2]/div/div/button");
        Thread.sleep(1000);

    }
   
    
    /**This function permits create a new task.*/
    public void NewTask() throws Exception {
        selenium.open("#task/new");
        waitForElement("//*[@data-id=\"v192168000209_1306417280414_1045\"]");
    // Name :
        selenium.type("//*[@data-id=\"v192168000209_1306417280414_1045\"]//input[@class=\"gwt-TextBox\"]", "Task 1");
        selenium.fireEvent("//*[@data-id=\"v192168000209_1306417280414_1045\"]//input[@class=\"gwt-TextBox\"]", "blur");
    // Description :
        selenium.type("//*[@data-id=\"v192168000209_1306417487476_1058\"]//textarea[@class=\"gwt-TextArea\"]", "This is task 1");
        selenium.fireEvent("//*[@data-id=\"v192168000209_1306417487476_1058\"]//textarea[@class=\"gwt-TextArea\"]", "blur");
    // Record and exit:
        selenium.click("//div[@id=\"contentRootPanel\"]/div/div[2]/div/div/button");
        Thread.sleep(1000);
    }
       
    
    /**This function permits create a new customer.*/
    public void NewCustomer() throws Exception {
        selenium.open("#customer/new");
        waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div[2]");
    // Name of customer :
        selenium.type("//body/div/div/div/div[3]/div/div/div/div/div/div/div[2]/div/div/div/div[2]/input", "Company 1");
        selenium.fireEvent("//body/div/div/div/div[3]/div/div/div/div/div/div/div[2]/div/div/div/div[2]/input", "blur");
    // Information about customer :
        selenium.type("//*[@data-id=\"v192168001111_1300802414894_6711\"]//input[@class=\"gwt-TextBox\"]", "Address customer 1");
        selenium.fireEvent("//*[@data-id=\"v192168001111_1300802414894_6711\"]//input[@class=\"gwt-TextBox\"]", "blur");
        selenium.type("//*[@data-id=\"v192168001111_1300802447714_6714\"]//input[@class=\"gwt-TextBox\"]", "San Francisco");
        selenium.fireEvent("//*[@data-id=\"v192168001111_1300802447714_6714\"]//input[@class=\"gwt-TextBox\"]", "blur");
        selenium.click("//body/div/div/div/div[3]/div/div/div/div/div/div/div[3]/div/div/div[5]/div[2]/div/div/select");
        selenium.click("//body/div/div/div/div[3]/div/div/div/div/div/div/div[3]/div/div/div[5]/div[2]/div/div/select/option[2]");
        selenium.type("//*[@data-id=\"v192168001111_1300802467493_6716\"]//input[@class=\"gwt-TextBox\"]", "94102");
        selenium.fireEvent("//*[@data-id=\"v192168001111_1300802467493_6716\"]//input[@class=\"gwt-TextBox\"]", "blur");
        selenium.type("//*[@data-id=\"v192168001111_1300802477572_6717\"]//input[@class=\"gwt-TextBox\"]", "USA");
        selenium.fireEvent("//*[@data-id=\"v192168001111_1300802477572_6717\"]//input[@class=\"gwt-TextBox\"]", "blur");
        selenium.type("//*[@data-id=\"v010003200202_1185578404333_857\"]//input[@class=\"gwt-TextBox\"]", "4151111111");
        selenium.fireEvent("//*[@data-id=\"v010003200202_1185578404333_857\"]//input[@class=\"gwt-TextBox\"]", "blur");
        selenium.type("//*[@data-id=\"v192168001111_1300791133394_4495\"]//input[@class=\"gwt-TextBox\"]", "azerty@gmail.com");
        selenium.fireEvent("//*[@data-id=\"v192168001111_1300791133394_4495\"]//input[@class=\"gwt-TextBox\"]", "blur");
        selenium.type("//*[@data-id=\"v192168000022_1120724265750_1146\"]//input[@class=\"gwt-TextBox\"]", "www.myerp.com");
        selenium.fireEvent("//*[@data-id=\"v192168000022_1120724265750_1146\"]//input[@class=\"gwt-TextBox\"]", "blur");
        //Confirm creation
        selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
        Thread.sleep(1000);
    }
	
    
	@AfterClass
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
