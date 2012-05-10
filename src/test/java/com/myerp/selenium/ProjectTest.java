package com.myerp.selenium;

import org.junit.*;
import com.thoughtworks.selenium.*;
import com.testingbot.*;

public class ProjectTest extends MyERPTestCase {
	@SuppressWarnings("deprecation")
	@Test
	public void testNewProject() throws Exception {
			login("/index.jsp?locale=en_US#project/new");
    		
    	// Name :
    	for (int second = 0;; second++) {
    		if (second >= 60) fail("timeout");
    		try { if (selenium.isTextPresent("Name")) break; } catch (Exception e) {}
    			Thread.sleep(1000);
    		}

    		selenium.type("//*[@class=\"gwt-TextBox\"]", "Project ,;:!?..§");
    		selenium.fireEvent("//*[@class=\"gwt-TextBox\"]", "blur");
    	// Description :
    		selenium.type("//*[@class=\"gwt-TextArea\"]", "This is project 2");
    		selenium.fireEvent("//*[@class=\"gwt-TextArea\"]", "blur");
    	// Record and exit :
    		selenium.click("//button[@class=\"GJG0VAMCJJ\"]");
    }

	@Test
	public void testDeleteproject() throws Exception {
			login("/index.jsp?locale=en_US#project/list");
		
		// Selection of the project :
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("View Projects")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

			selenium.click("//div/div/div/div[2]/div[2]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Search this list")) break; } catch (Exception e) {}
			Thread.sleep(1000);
			selenium.click("//*[@class=\"GJG0VAMCHL GJG0VAMCJL GJG0VAMCKL\"]");
			selenium.click("//div[@id='contentRootPanel']/div/div[2]/div/div[2]");
		//Confirm :
			selenium.chooseOkOnNextConfirmation();
		}
	}

	
	@Test
	public void testNewtask() throws Exception {
			login("/index.jsp?locale=en_US#task/new");
	
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
			selenium.click("css=span.GJG0VAMCEU");
			selenium.type("xpath=(//input[@type='text'])[6]", "12.00");
			selenium.click("css=input.gwt-DateBox");
			selenium.click("css=body.gecko1_8 div.dateBoxPopup div.popupContent table.gwt-DatePicker tbody tr td table.datePickerDays tbody tr td.datePickerDay");
		// Record and exit:
			selenium.click("//*[@class=\"GJG0VAMCJJ\"]");
	}
	
	@Test
	public void testDeletetask() throws Exception {
		login("/index.jsp?locale=en_US#task/list");
		
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
			selenium.click("//*[@class=\"GJG0VAMCHL GJG0VAMCIM\"]");
			selenium.click("//div[@id='contentRootPanel']/div/div[2]/div/div[2]");
		//Confirm :
			selenium.chooseOkOnNextConfirmation();
		}
	}	
	
	@Test
	public void testNewtimelog() throws Exception {
			login("/index.jsp?locale=en_US#time_log/new");
    		
    	// Name :
    		for (int second = 0;; second++) {
    			if (second >= 60) fail("timeout");
    			try { if (selenium.isTextPresent("Note")) break; } catch (Exception e) {}
    			Thread.sleep(1000);
    		}

    		selenium.type("//*[@class=\"gwt-TextBox\"]", "Time Log 1");
    		selenium.fireEvent("//*[@class=\"gwt-TextBox\"]", "blur");
    	// Hours :
    		selenium.type("//*[@data-id=\"v192168001116_1309523436710_189\"]", "12");
    		selenium.fireEvent("//*[@data-id=\"v192168001116_1309523436710_189\"]", "blur");
    	//Choose customer:
    		//selenium.click("//div[@class=\"gwt-PushButton-up\"]");
    		//selenium.click("//*[@class=\"GJG0VAMCHL GJG0VAMCJL GJG0VAMCKL\"]");
    	//Choose project:
    		//selenium.click("//*[@data-id=\"v192168001116_1309523337523_182\"]//div[@class=\"gwt-PushButton-up\"]");
    		//selenium.click("//*[@class=\"GJG0VAMCHL GJG0VAMCIM GJG0VAMCKL\"]");
    	//Choose service:
    		//selenium.click("//*[@data-id=\"v192168001116_1309523372185_187\"]//div[@class=\"gwt-PushButton-up\"]");
    		//selenium.click("//*[@class=\"GJG0VAMCHL GJG0VAMCJL\"]");
    	//Choose date:
    		selenium.click("css=input.gwt-DateBox");
    		selenium.click("css=body.gecko1_8 div.dateBoxPopup div.popupContent table.gwt-DatePicker tbody tr td table.datePickerDays tbody tr td.datePickerDay");
    	// Record and exit :
    		selenium.click("//button[@class=\"GJG0VAMCJJ\"]");
    }
	
	@Test
	public void testDeleteTimelog() throws Exception {
		login("/index.jsp?locale=en_US#time_log/list");
		
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
			selenium.click("//*[@class=\"GJG0VAMCHL GJG0VAMCIM GJG0VAMCKL\"]");
			selenium.click("//div[@id='contentRootPanel']/div/div[2]/div/div[2]");
		//Confirm:
			selenium.chooseOkOnNextConfirmation();
			
		}
	}	
	
	@Test
	public void testNewBilltime() throws Exception {
			login("/index.jsp?locale=en_US#bill_time");
    		
    	// Name :
    		for (int second = 0;; second++) {
    			if (second >= 60) fail("timeout");
    			try { if (selenium.isTextPresent("Customer to Bill")) break; } catch (Exception e) {}
    			Thread.sleep(1000);
    		}

    	// Select a Customer to bill:
    		//selenium.click("//*[@class=\"gwt-TextBox\"]");
    		selenium.click("//*[@data-id=\"v192168000209_1306843241563_1198\"]");
    		selenium.click("//option[@value=\"8\"]");
    	// Selection of Project :
    		//selenium.click("//*[@data-id=\"v192168001104_1306152971438_783\"]//div[@class=\"contentCellTable\"]//div[@class=\"gwt-PushButton gwt-PushButton-up\"]");
    		//selenium.click("");
    	//Choose length:
    		selenium.click("//*[@data-id=\"v192168000209_1306845217865_1244\"]");
    		selenium.click("//div[@id='contentRootPanel']/div/div/div/div/div/div/div[2]/div/div/div[2]/div/div/div/div/div[2]/div/div/select");
    	// Record and exit :
    		selenium.click("//button[@class=\"GJG0VAMCJJ\"]");
    	}
	
	}
	