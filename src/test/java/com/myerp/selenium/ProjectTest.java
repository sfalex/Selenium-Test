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
		waitForElement(MainPanel+"div[2]/div/div[3]/div/div[2]/div/div/table/tbody");
		assertTrue(selenium.isElementPresent(MainPanel+"div[2]/div/div[3]/div/div[2]/div/div/table/tbody"));	
    }

	@Test
	/**This test allows to view and to delete a project.*/
	public void testDeleteproject() throws Exception {
	// Create a new project:
		login("/index.jsp");
		NewProject();
	// Access list of projects:
		selenium.open("#project/list");
		waitForElement(MainPanel+"div[2]/div");
	// Selection of the project :
		selenium.click("//div[@style=\"outline:none;\"]");
		waitForElement(MainPanel+"div/div");
		mouse("//div[@class=\"html-face\"]");
	//Confirm deletion :
		selenium.click(ConfirmDelete);
		Thread.sleep(1000);
	//Check that the task has been deleted :
		selenium.open("#project/list");
		waitForElement(MainPanel+"div[2]");
	}

	@Test
	/**This test allows to create a task.*/
	public void testNewtask() throws Exception {
		login("/index.jsp");
		NewTask();
	// Check if it has been recorded:
		selenium.open("#project/list");
		waitForElement(MainPanel+"div[2]/div/div[3]/div/div[2]/div/div/table/tbody");
		assertTrue(selenium.isElementPresent(MainPanel+"div[2]/div/div[3]/div/div[2]/div/div/table/tbody"));
	}
	
	@Test
	/**This test allows to view and to delete a task.*/
	public void testDeletetask() throws Exception {
		login("/index.jsp");
		NewTask();
	// Access list of tasks :
		selenium.open("#task/list");
		waitForElement(MainPanel+"div[2]/div");
	// Selection of the task :
		selenium.click("//div[@style=\"outline:none;\"]");
		waitForElement(MainPanel+"div/div");
		mouse("//div[@class=\"html-face\"]");
	//Confirm deletion :
		selenium.click(ConfirmDelete);
		Thread.sleep(1000);
	//Check that the task has been deleted :
		selenium.open("#project/list");
		waitForElement(MainPanel+"div[2]");	
	}	

	@Test
	/**This test allows to create a time log.*/
	public void testNewtimelog() throws Exception {
	//First, create a new customer and a new project
		login("/index.jsp");
		NewCustomer();
		NewProject();

		selenium.open("#time_log/new");
		waitForElement(MainPanel+"div/div/div/div");
    // Name :
		String NameField = "//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]";
    	selenium.type(NameField, "Time Log 1");
    	selenium.fireEvent(NameField, "blur");
    // Hours :
    	String HoursField = "//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]";
    	selenium.type(HoursField, "12");
    	selenium.fireEvent(HoursField, "blur");
    //Choose customer:
    	mouse("//div[@data-id=\"v192168001116_1309523328805_180\"]/div[2]/div/div[2]/div/div/img");
    	waitForElement("//div[@class=\"SearchDataGrid selectionableCells\"]");
    	selenium.click("//div[@class=\"SearchDataGrid selectionableCells\"]/div[3]/div/div[2]/div/div/table/tbody/tr/td/div");
    	String ProjectChoice = "//div[@data-id=\"v192168001116_1309523337523_182\"]";
    	waitForElement(ProjectChoice);
    //Choose project:
    	String ServiceChoice = "//div[@data-id=\"v192168001116_1309523372185_187\"]";
    	mouse(ProjectChoice+"/div[2]/div/div[2]/div/div/img");
    	waitForElement("//tr[@class=\"dialogMiddle\"]");
    	selenium.click("//tr[@class=\"dialogMiddle\"]/td[2]/div/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr/td");
    	//waitForElement(ServiceChoice);
    //Choose service:
    	//mouse(ServiceChoice+"div[2]/div/div[2]/div/div/img");
    	//waitForElement("//div[@class=\"SearchDataGrid selectionableCells\"]");
    	//waitForElement("//div[@data-id=\"v192168001116_1309523353414_185\"]");
    // Record and exit :
    	selenium.click(DoneButton);
    	Thread.sleep(1000);
    // Check if it has been recorded:
    	selenium.open("#time_log/list");
    	waitForElement(MainPanel+"div[2]");
    	assertTrue(selenium.isElementPresent(MainPanel+"div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr"));
	}
	
	@Test
	/**This test allows to view and delete a time log.*/
	public void testDeleteTimelog() throws Exception {
	//First, create a timelog
		
		login("/index.jsp");
		NewCustomer();
		NewProject();

		selenium.open("/index.jsp#time_log/new");
		waitForElement(MainPanel+"div/div/div/div");
    // Name :
		String NameField = "//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]";
		selenium.type(NameField, "Time Log 1");
	  	selenium.fireEvent(NameField, "blur");
	// Hours :
		String HoursField = "//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]";
		selenium.type(HoursField, "12");
		selenium.fireEvent(HoursField, "blur");
    //Choose customer:
		mouse("//div[@data-id=\"v192168001116_1309523328805_180\"]/div[2]/div/div[2]/div/div/img");
		waitForElement("//div[@class=\"SearchDataGrid selectionableCells\"]");
		selenium.click("//div[@class=\"SearchDataGrid selectionableCells\"]/div[3]/div/div[2]/div/div/table/tbody/tr/td/div");
		String ProjectChoice = "//div[@data-id=\"v192168001116_1309523337523_182\"]";
		waitForElement(ProjectChoice);
	//Choose project:
		String ServiceChoice = "//div[@data-id=\"v192168001116_1309523372185_187\"]";
		mouse(ProjectChoice+"/div[2]/div/div[2]/div/div/img");
		waitForElement("//tr[@class=\"dialogMiddle\"]");
		selenium.click("//tr[@class=\"dialogMiddle\"]/td[2]/div/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr/td");
		//waitForElement(ServiceChoice);
	//Choose service:
		//mouse(ServiceChoice+"div[2]/div/div[2]/div/div/img");
		//waitForElement("//div[@class=\"SearchDataGrid selectionableCells\"]");
		//waitForElement("//div[@data-id=\"v192168001116_1309523353414_185\"]");
	// Record and exit :
		 selenium.click(DoneButton);
		 Thread.sleep(1000);

	// Then delete it
	// Access list of time logs :
		 selenium.open("#time_log/list");
	    waitForElement(MainPanel+"div[2]");
	// Selection of the time log :
		selenium.click("//div[@style=\"outline:none;\"]");
		waitForElement(MainPanel+"div/div");
		mouse("//div[@class=\"html-face\"]");
	//Confirm deletion :
		selenium.click(ConfirmDelete);
		Thread.sleep(1000);
	//Check that the time log has been deleted :
		selenium.open("#time_log/list");
		waitForElement(MainPanel+"div[2]");
		assertTrue(selenium.isElementPresent(MainPanel+"div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr"));	
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
		String CustomerChoice = "//div[@data-id=\"v192168000209_1306843241563_1198\"]//select[@class=\"gwt-ListBox\"]";
    	selenium.click(CustomerChoice);
    	selenium.click(CustomerChoice+"/option[2]");
    // Selection of Project :
    	String SearchProject = "//div[@class=\"SearchDataGrid selectionableCells\"]";
    	String DateChoice = "//div[@data-id=\"v192168000209_1306845217865_1244\"]";
    	mouse("//*[@data-id=\"v192168001104_1306152971438_783\"]/div[2]/div/div[2]/div/div/img");
    	waitForElement(SearchProject);
    	selenium.click(SearchProject+"/div[3]/div/div[2]/div/div/table/tbody/tr/td/div");
    	waitForElement(DateChoice);
    //Choose date range:
    	selenium.click(DateChoice);
    	selenium.click(DateChoice+"//option[@value=\"Week\"]");
    // Record and exit :
    	selenium.click(DoneButton);
    	Thread.sleep(1000);
    	}
	
	}
	