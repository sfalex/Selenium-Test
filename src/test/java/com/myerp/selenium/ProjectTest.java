package com.myerp.selenium;

import org.junit.*;
import com.thoughtworks.selenium.*;
import com.testingbot.*;

public class ProjectTest extends MyERPTestCase {
	@SuppressWarnings("deprecation")
	
	@Test
	/**This test allows to create a project.*/
	public void testNewProject() throws Exception {
		NewProject();
    }

	@Test
	/**This test allows to view and to delete a project.*/
	public void testDeleteproject() throws Exception {
		//NewProject();
			login("/index.jsp#project/list");
			
		// Selection of the project :
	
			selenium.click("//body/div/div/div/div[3]/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr/td/div");

			selenium.click("//body/div/div/div/div[2]/div/div[2]/div/div[2]/div");
		//Confirm :
			selenium.chooseOkOnNextConfirmation();
		
	}

	@Test
	/**This test allows to create a task.*/
	public void testNewtask() throws Exception {
			NewTask();
			}
	
	@Test
	/**This test allows to view and to delete a task.*/
	public void testDeletetask() throws Exception {
		
		login("/index.jsp#task/list");

		// Selection of the task :
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("View Tasks")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
	
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Search this list")) break; } catch (Exception e) {}
			Thread.sleep(1000);
			selenium.click("//body/div/div/div/div[3]/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr");
			selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div[2]/div");
		//Confirm :
			selenium.chooseOkOnNextConfirmation();
		}
	}	
	

	@Test
	/**This test allows to create a customer.*/
	public void testNewCustomer() throws Exception {
			NewCustomer();
			}
	
	@Test
	/**This test allows to create a time log.*/
	public void testNewtimelog() throws Exception {
			
			login("/index.jsp#time_log/new");
    		
    	// Name :
    		for (int second = 0;; second++) {
    			if (second >= 60) fail("timeout");
    			try { if (selenium.isTextPresent("Note")) break; } catch (Exception e) {}
    			Thread.sleep(1000);
    		}

    		selenium.type("//input[@class=\"gwt-TextBox\"]", "Time Log 1");
    		selenium.fireEvent("//input[@class=\"gwt-TextBox\"]", "blur");
    	// Hours :
    		selenium.type("//*[@data-id=\"v192168001116_1309523436710_189\"]", "12");
    		selenium.fireEvent("//*[@data-id=\"v192168001116_1309523436710_189\"]", "blur");
    	//Choose customer:
    		//selenium.click("//body/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div[3]/div[2]/div/div[2]/div/div/img");
    		//selenium.click("//body/div[6]/div/table/tbody/tr[2]/td[2]/div/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr");
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
    		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
    }
	
	@Test
	/**This test allows to view and delete a time log.*/
	public void testDeleteTimelog() throws Exception {
		login("/index.jsp#time_log/list");
		
		// Selection of the Timelog :
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("View Time Logs")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
	
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Search this list")) break; } catch (Exception e) {}
			Thread.sleep(1000);
			selenium.click("//body/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div/table/tbody/tr");
			selenium.click("//div[@id='contentRootPanel']/div/div[2]/div/div[2]");
		//Confirm:
			selenium.chooseOkOnNextConfirmation();
			
		}
	}	
	
	@Test
	/**This test allows to create a bill time.*/
	public void testNewBilltime() throws Exception {
			login("/index.jsp#bill_time");
    		
    	// Name :
    		for (int second = 0;; second++) {
    			if (second >= 60) fail("timeout");
    			try { if (selenium.isTextPresent("Customer to Bill")) break; } catch (Exception e) {}
    			Thread.sleep(1000);
    		}

    	// Select a Customer to bill:
    		//selenium.click("//*[@class=\"gwt-TextBox\"]");
    		selenium.click("//*[@data-id=\"v192168000209_1306843241563_1198\"]");
    		selenium.click("//option[@value=\"1\"]");
    	// Selection of Project :
    		//selenium.click("//*[@data-id=\"v192168001104_1306152971438_783\"]//div[@class=\"contentCellTable\"]//div[@class=\"gwt-PushButton gwt-PushButton-up\"]");
    		//selenium.click("");
    	//Choose length:
    		selenium.click("//*[@data-id=\"v192168000209_1306845217865_1244\"]");
    		selenium.click("//div[@id='contentRootPanel']/div/div/div/div/div/div/div[2]/div/div/div[2]/div/div/div/div/div[2]/div/div/select");
    	// Record and exit :
    		selenium.click("//body/div/div/div/div[3]/div/div[2]/div/div/button");
    	}
	
	
	}
	