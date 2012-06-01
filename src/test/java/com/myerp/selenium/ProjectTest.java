package com.myerp.selenium;

import org.junit.*;


public class ProjectTest extends MyERPTestCase {

	
	@Test
	/**This test allows to create a project.*/
	public void testNewProject() throws Exception {
		login("/index.jsp");
		NewProject();
	// Check if it has been recorded:
		selenium.open("#project/list");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]");
		assertTrue(selenium.isElementPresent("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr"));	
    }

	@Test
	/**This test allows to view and to delete a project.*/
	public void testDeleteproject() throws Exception {
	// Create a new project:
		login("/index.jsp");
		NewProject();
	// Access list of projects:
		selenium.open("#project/list");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]/div");
	// Selection of the project :
		selenium.click("//div[@style=\"outline:none;\"]");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div");
		mouse("//div[@class=\"html-face\"]");
	//Confirm deletion :
		selenium.click("//tr[@class=\"dialogMiddle\"]/td[2]/div/div/div[2]/button");
		Thread.sleep(1000);
	//Check that the task has been deleted :
		selenium.open("#project/list");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]");
		assertTrue(selenium.isElementPresent("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr"));	
	}

	@Test
	/**This test allows to create a task.*/
	public void testNewtask() throws Exception {
		login("/index.jsp");
		NewTask();
	// Check if it has been recorded:
		selenium.open("#project/list");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]");
		assertTrue(selenium.isElementPresent("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr"));
	}
	
	@Test
	/**This test allows to view and to delete a task.*/
	public void testDeletetask() throws Exception {
		login("/index.jsp");
		NewTask();
	// Access list of tasks :
		selenium.open("#task/list");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]/div");
	// Selection of the task :
		selenium.click("//div[@style=\"outline:none;\"]");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div");
		mouse("//div[@class=\"html-face\"]");
	//Confirm deletion :
		selenium.click("//tr[@class=\"dialogMiddle\"]/td[2]/div/div/div[2]/button");
		Thread.sleep(1000);
	//Check that the task has been deleted :
		selenium.open("#project/list");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]");
		assertTrue(selenium.isElementPresent("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr/td/div"));	
	}	

	// TODO: Rajouter la selection de service (et donc la création d'un service)
	@Test
	/**This test allows to create a time log.*/
	public void testNewtimelog() throws Exception {
	//First, create a new customer and a new project
		login("/index.jsp");
		NewCustomer();
		NewProject();

		selenium.open("/index.jsp#time_log/new");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div/div/div");
    // Name :
    	selenium.type("//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]", "Time Log 1");
    	selenium.fireEvent("//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]", "blur");
    // Hours :
    	selenium.type("//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]", "12");
    	selenium.fireEvent("//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]", "blur");
    //Choose customer:
    	mouse("//div[@data-id=\"v192168001116_1309523328805_180\"]/div[2]/div/div[2]/div/div/img");
    	waitForElement("//div[@class=\"SearchDataGrid selectionableCells\"]");
    	selenium.click("//div[@class=\"SearchDataGrid selectionableCells\"]/div[3]/div/div[2]/div/div/table/tbody/tr/td/div");
    	waitForElement("//div[@data-id=\"v192168001116_1309523337523_182\"]");
    //Choose project:
    	mouse("//div[@data-id=\"v192168001116_1309523337523_182\"]/div[2]/div/div[2]/div/div/img");
    	waitForElement("//tr[@class=\"dialogMiddle\"]");
    	selenium.click("//tr[@class=\"dialogMiddle\"]/td[2]/div/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr/td");
    	//waitForElement("//div[@data-id=\"v192168001116_1309523372185_187\"]");
    //Choose service:
    	//mouse("//div[@data-id=\"v192168001116_1309523372185_187\"]/div[2]/div/div[2]/div/div/img");
    	//waitForElement("//div[@class=\"SearchDataGrid selectionableCells\"]");
    	//waitForElement("//div[@data-id=\"v192168001116_1309523353414_185\"]");
    // Record and exit :
    	selenium.click("//div[@id=\"contentRootPanel\"]/div/div[2]/div/div/button");
    	Thread.sleep(1000);
    // Check if it has been recorded:
    	selenium.open("#time_log/list");
    	waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]");
    	assertTrue(selenium.isElementPresent("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr"));
	}
	
	@Test
	/**This test allows to view and delete a time log.*/
	public void testDeleteTimelog() throws     		Exception {
	//First, create a timelog
		
		login("/index.jsp");
		NewCustomer();
		NewProject();

		selenium.open("/index.jsp#time_log/new");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div/div/div");
    // Name :
		selenium.type("//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]", "Time Log 1");
	  	selenium.fireEvent("//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]", "blur");
	// Hours :
		selenium.type("//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]", "12");
		selenium.fireEvent("//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]", "blur");
    //Choose customer:
		mouse("//div[@data-id=\"v192168001116_1309523328805_180\"]/div[2]/div/div[2]/div/div/img");
		waitForElement("//div[@class=\"SearchDataGrid selectionableCells\"]");
		selenium.click("//div[@class=\"SearchDataGrid selectionableCells\"]/div[3]/div/div[2]/div/div/table/tbody/tr/td/div");
		waitForElement("//div[@data-id=\"v192168001116_1309523337523_182\"]");
	//Choose project:
		mouse("//div[@data-id=\"v192168001116_1309523337523_182\"]/div[2]/div/div[2]/div/div/img");
		waitForElement("//tr[@class=\"dialogMiddle\"]");
		selenium.click("//tr[@class=\"dialogMiddle\"]/td[2]/div/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr/td");
		//waitForElement("//div[@data-id=\"v192168001116_1309523372185_187\"]");
	//Choose service:
		//mouse("//div[@data-id=\"v192168001116_1309523372185_187\"]/div[2]/div/div[2]/div/div/img");
		//waitForElement("//div[@class=\"SearchDataGrid selectionableCells\"]");
		//waitForElement("//div[@data-id=\"v192168001116_1309523353414_185\"]");
	// Record and exit :
		 selenium.click("//div[@id=\"contentRootPanel\"]/div/div[2]/div/div/button");
		 Thread.sleep(1000);

	// Then delete it
	// Access list of time logs :
		 selenium.open("#time_log/list");
	    waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]");
	// Selection of the time log :
		selenium.click("//div[@style=\"outline:none;\"]");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div/div");
		mouse("//div[@class=\"html-face\"]");
	//Confirm deletion :
		selenium.click("//tr[@class=\"dialogMiddle\"]/td[2]/div/div/div[2]/button");
		Thread.sleep(1000);
	//Check that the time log has been deleted :
		selenium.open("#time_log/list");
		waitForElement("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]");
		assertTrue(selenium.isElementPresent("//div[@id=\"contentRootPanel\"]/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr"));	
	}	
	
	@Test
	/**This test allows to create a bill time.*/
	public void testNewBilltime() throws Exception {
	//First, create a customer to bill
		login("/index.jsp");
		NewProject();
		NewCustomer();
		
		selenium.open("#bill_time");
		Thread.sleep(1000);
    // Select a Customer to bill:
    	selenium.click("//div[@data-id=\"v192168000209_1306843241563_1198\"]//select[@class=\"gwt-ListBox\"]");
    	selenium.click("//div[@data-id=\"v192168000209_1306843241563_1198\"]//select[@class=\"gwt-ListBox\"]/option[2]");
    // Selection of Project :
    	mouse("//*[@data-id=\"v192168001104_1306152971438_783\"]/div[2]/div/div[2]/div/div/img");
    	waitForElement("//div[@class=\"SearchDataGrid selectionableCells\"]");
    	selenium.click("//div[@class=\"SearchDataGrid selectionableCells\"]/div[3]/div/div[2]/div/div/table/tbody/tr/td/div");
    	waitForElement("//div[@data-id=\"v192168000209_1306845217865_1244\"]");
    //Choose date range:
    	selenium.click("//div[@data-id=\"v192168000209_1306845217865_1244\"]");
    	selenium.click("//div[@data-id=\"v192168000209_1306845217865_1244\"]//option[@value=\"Week\"]");
    // Record and exit :
    	selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
    	Thread.sleep(1000);
    	}
	
	}
	