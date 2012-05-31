package com.myerp.selenium;

import org.junit.*;


public class ProjectTest extends MyERPTestCase {

	
	@Test
	/**This test allows to create a project.*/
	public void testNewProject() throws Exception {
		login("/index.jsp#project/new");
		NewProject();
    }

	@Test
	/**This test allows to view and to delete a project.*/
	public void testDeleteproject() throws Exception {
		//NewProject();
			login("/index.jsp#project/new");
			NewProject();
			
			selenium.open("/index.jsp#project/list");
			Thread.sleep(1000);
		// Selection of the project :
			selenium.click("//div[@style=\"outline:none;\"]");
			Thread.sleep(1000);
			selenium.mouseOver("//div[@class=\"html-face\"]");
			selenium.mouseDown("//div[@class=\"html-face\"]");
			selenium.mouseUp("//div[@class=\"html-face\"]");
			Thread.sleep(3000);
		//Confirm :
			selenium.click("//body/div[6]/div/table/tbody/tr[2]/td[2]/div/div/div[2]/button");
			Thread.sleep(1000);
		
	}

	@Test
	/**This test allows to create a task.*/
	public void testNewtask() throws Exception {
			login("/index.jsp#task/new");
			NewTask();
			}
	
	@Test
	/**This test allows to view and to delete a task.*/
	public void testDeletetask() throws Exception {
			login("/index.jsp#task/new");
			NewTask();
			
			selenium.open("/index.jsp#task/list");
			Thread.sleep(1000);
		// Selection of the task :
			selenium.click("//div[@style=\"outline:none;\"]");
			Thread.sleep(1000);
			selenium.mouseOver("//div[@class=\"html-face\"]");
			selenium.mouseDown("//div[@class=\"html-face\"]");
			selenium.mouseUp("//div[@class=\"html-face\"]");
			Thread.sleep(1000);
		//Confirm :
			selenium.click("//body/div[6]/div/table/tbody/tr[2]/td[2]/div/div/div[2]/button");
			Thread.sleep(1000);
		
	}	
	

	@Test
	/**This test allows to create a customer.*/
	public void testNewCustomer() throws Exception {
			login("/index.jsp#customer/new");
			NewCustomer();
			}
	
	@Test
	/**This test allows to create a time log.*/
	public void testNewtimelog() throws Exception {
		//First, create a new customer and a new project
		
		//login("/index.jsp#customer/new");
		//NewCustomer();
		//open("/index.jsp#project/new");
		//NewProject();
		
			login("/index.jsp#time_log/new");
			Thread.sleep(1000);
    	// Name :
    		selenium.type("//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]", "Time Log 1");
    		selenium.fireEvent("//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]", "blur");
    	// Hours :
    		selenium.type("//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]", "12");
    		selenium.fireEvent("//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]", "blur");
    		Thread.sleep(1000);
    	//Choose customer:
    		//selenium.mouseOver("//body/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div[3]/div[2]/div/div[2]/div/div/img");
    		//selenium.mouseDown("//body/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div[3]/div[2]/div/div[2]/div/div/img");
    		//selenium.mouseUp("//body/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div[3]/div[2]/div/div[2]/div/div/img");
    		//Thread.sleep(1000);
    		//selenium.click("//div[@style=\"outline:none;\"]");
    		//Thread.sleep(1000);
    	//Choose project:
    		//selenium.click("//body/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div[4]/div[2]/div/div[2]/div/div/img");
    		//selenium.click("//body/div[6]/div/table/tbody/tr[2]/td[2]/div/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr");
    	//Choose service:
    		//selenium.click("//*[@data-id=\"v192168001116_1309523372185_187\"]//div[@class=\"gwt-PushButton-up\"]");
    		//selenium.click("//*[@class=\"GJG0VAMCHL GJG0VAMCJL\"]");
    	//Choose date:
    		//selenium.click("//body/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div[6]/div[2]/input");
    		//selenium.click("//body/div[5]/div/table/tbody/tr[2]/td/table/tbody/tr[5]/td[6]");
    	// Record and exit :
    		selenium.click("//button[@class=\"GDOPQEJDJJ\"]");
    		Thread.sleep(1000);
    		
    }
	
	@Test
	/**This test allows to view and delete a time log.*/
	public void testDeleteTimelog() throws     		Exception {
		//First, create a timelog
		login("/index.jsp#time_log/new");
		Thread.sleep(1000);
	// Name :
		selenium.type("//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]", "Time Log 1");
		selenium.fireEvent("//*[@data-id=\"v192168001116_1309523366090_186\"]//input[@class=\"gwt-TextBox\"]", "blur");
	// Hours :
		selenium.type("//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]", "12");
		selenium.fireEvent("//*[@data-id=\"v192168001116_1309523436710_189\"]//input[@class=\"gwt-TextBox\"]", "blur");
		Thread.sleep(1000);
	// Record and exit :
		selenium.click("//button[@class=\"GDOPQEJDJJ\"]");
		Thread.sleep(1000);
		
	// Then delete it
		selenium.open("/index.jsp#time_log/list");
		Thread.sleep(1000);
		selenium.click("//div[@style=\"outline:none;\"]");
		Thread.sleep(1000);
		selenium.mouseOver("//div[@class=\"html-face\"]");
		selenium.mouseDown("//div[@class=\"html-face\"]");
		selenium.mouseUp("//div[@class=\"html-face\"]");
		Thread.sleep(1000);
	//Confirm :
		selenium.click("//body/div[6]/div/table/tbody/tr[2]/td[2]/div/div/div[2]/button");
		Thread.sleep(1000);
		
			
		
	}	
	
	@Test
	/**This test allows to create a bill time.*/
	public void testNewBilltime() throws Exception {
		//First, create a customer to bill
			//login("/index.jsp#customer/new");
			//NewCustomer();
		
			login("/index.jsp#bill_time");
			Thread.sleep(1000);
    	// Select a Customer to bill:
    		//selenium.click("//body/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/div[2]/div/div/select/option[2]");
    		//selenium.click("//option[@value=\"125\"]");
    	// Selection of Project :
    		//selenium.click("//*[@data-id=\"v192168001104_1306152971438_783\"]//div[@class=\"contentCellTable\"]//div[@class=\"gwt-PushButton gwt-PushButton-up\"]");
    		//selenium.click("");
    	//Choose length:
    		//selenium.click("//*[@data-id=\"v192168000209_1306845217865_1244\"]//select[@class=\"gwt-ListBox\"]//option[@value=\"Week\"]");
    		//Thread.sleep(1000);
    	// Record and exit :
    		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
    		Thread.sleep(1000);
    	}
	
	
	}
	