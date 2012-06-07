package com.myerp.selenium;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

@SuppressWarnings("deprecation")

public class PaymentTest extends MyERPTestCase {
	

	/** This function permits to fill the CB field to choose a plan. */
	private void fillCB(String plan, String name, String number ) throws Exception {
		String OptionPlan = "//div[@data-plan='"+plan+"']/";
		waitForElement(OptionPlan+"/button");
		selenium.click(OptionPlan+"/button");
		waitForElement(OptionPlan+"/button[@disabled]");
		Thread.sleep(1000);
		waitForElement(OptionPlan+"/div[starts-with(@id, 'gwt-uid-') and @style!='display: none;']");
		selenium.type(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span/input",name);
		selenium.fireEvent(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span/input","blur");
		selenium.type(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span[2]/input",number);
		selenium.fireEvent(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span[2]/input","blur");
	}
	
	
	/** This function permits to round at 2 Decimals. */
	private double round2Decimals(double input) {
		return new BigDecimal(input).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}
	

	// This test checks if the Manage Subscription appears in the research field.
	@Test
	public void testResearch() throws Exception {
		login("/login");
		waitForElement("//input[@class=\"gwt-TextBox placeholder\"]");
		//selenium.click("css=input.gwt-TextBox");
		selenium.type("css=input.gwt-TextBox", "manage");
		selenium.typeKeys("css=input.gwt-TextBox", " ");
		waitForElement("//*[@data-uri=\"manage_subscription\"]");
	}

	
	// This test checks the banner root panel when we have a free plan and that the banner root panel is not present when we have a billing plan.
	@Test
	public void testBannerRootPanel() throws Exception {
		login("/index.jsp#manage_subscription");
		Thread.sleep(3000);
		waitForElement("//div[@id=\"bannerRootPanel\"]//*");
		fillCB("unlimited","alex","4005519200000004");
		selenium.click(ChosenPlan+"div/div[6]/div[2]/button");
		waitForElement(Processing);
		Thread.sleep(5000);
		selenium.click(DoneButton);
		Thread.sleep(2000);
		selenium.open("/home");
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isElementPresent("//div[@id='bannerRootPanel']//*"));
	}
	
	
	// This test checks that 4 plans are displayed.
	@Test
	public void testFourPlansDisplayed() throws Exception {
		login("/index.jsp#manage_subscription");
		waitForElement("//div[@data-plan=\"free\"]");
		String plan[] = {"unlimited", "team", "plus", "free"};
		for (int i = 0; i < 4; i++) {
			assertTrue(selenium.isElementPresent("//div[@data-plan=\"" + plan[i] + "\"]"));
		}
	}
	
	
	// This test checks the enabilities and the disabilities of the plan buttons.
	@Test
	public void testButtonPlan() throws Exception {
		login("/index.jsp#manage_subscription");
		String plan[] = {"unlimited", "team", "plus"};
		waitForElement("//div[@data-plan=\"free\"]//button");	
		assertTrue(selenium.isElementPresent("//div[@data-plan=\"free\"]//button[@disabled]"));
		for (int i = 0; i < 3; i++) {
			String OptionPlan = "//div[@data-plan='"+plan[i]+"']/";
			
			assertFalse(selenium.isElementPresent(OptionPlan+"/button[@disabled]"));
			selenium.click(OptionPlan+"/button");
			waitForElement(OptionPlan+"/button[@disabled]");
			assertTrue(selenium.isElementPresent(OptionPlan+"/button[@disabled]"));
		}
	}
	
	
	// This test checks that the CB fields are editable, enable and blank. It checks also that the fields become disable until click on button change.
	@Test
	public void testCBField () throws Exception {
		login("/index.jsp#manage_subscription");
		Thread.sleep(3000);
		String plan[] = {"unlimited", "team", "plus"};
		for (int i = 0; i < 3; i++) {
			String OptionPlan = "//div[@data-plan='"+plan[i]+"']/";
			
			waitForElement(OptionPlan+"/button");
			selenium.click(OptionPlan+"/button");
			Thread.sleep(2000);
			waitForElement(OptionPlan+"/div[starts-with(@id, 'gwt-uid-') and @style!='display: none;']");
			waitForElement(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span/input");
			assertEquals(selenium.getText(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span/input"),"");
			waitForElement(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span[2]/input");
			assertEquals(selenium.getText(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span[2]/input"),"");
			assertEquals(Integer.parseInt(selenium.getSelectedLabel(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select")),(new Date()).getMonth()+1);
			assertEquals(selenium.getSelectedLabel(OptionPlan+"div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select[2]"),(new Date()).getYear()+1900);
			assertTrue(selenium.isChecked(OptionPlan+"div[6]/div[2]/div/span/input"));
		}
	}

	
	// This test checks that the button for changing the CB information works correctly.
	@Test
	public void testCBChangeButton () throws Exception {
		
		login("/index.jsp#manage_subscription");
		Thread.sleep(3000);
		String plans[] = {"team", "plus"};
		fillCB("unlimited","alex","4005519200000004");
		selenium.click("//div[@data-plan='unlimited']//div[starts-with(@id, 'gwt-uid-')]//button");
		waitForElement("//div[@data-plan='unlimited']//div[starts-with(@id, 'gwt-uid-') and @style='display: none;']");
		selenium.click(DoneButton);
		selenium.open("#manage_subscription");
		selenium.waitForPageToLoad("30000");
		Thread.sleep(5000);
	
		
		for (int i = 0; i < 2; i++) {
			String OptionPlan = "//div[@data-plan='"+plans[i]+"']/div[6]/div/div[2]/div/div[2]/div/";
			
			Thread.sleep(3000);
			waitForElement("//div[@data-plan='"+plans[i]+"']//button");
			selenium.click("//div[@data-plan='"+plans[i]+"']//button");
			waitForElement("//div[@data-plan='"+plans[i]+"']//button[@disabled]");
			Thread.sleep(1000);
			
			// Check that the Name field for the CB is disabled.
			waitForElement(OptionPlan+"span/input[@disabled]");
			assertTrue(selenium.isElementPresent(OptionPlan+"span/input[@disabled]"));
			// Check that the Number Card field for the CB is disabled.
			waitForElement(OptionPlan+"span[2]/input[@disabled]");
			assertTrue(selenium.isElementPresent(OptionPlan+"span[2]/input[@disabled]"));
			// Check the the Expiration date is disabled.
			waitForElement(OptionPlan+"span[3]/div[2]/select[@disabled]");
			assertTrue(selenium.isElementPresent(OptionPlan+"span[3]/div[2]/select[@disabled]"));
			waitForElement(OptionPlan+"span[3]/div[2]/select[2]");
			assertTrue(selenium.isElementPresent(OptionPlan+"span[3]/div[2]/select[2][@disabled]"));

			// Click on the button change plan.
			waitForElement("//div[@data-plan='"+plans[i]+"']/div[6]/div/div[2]/div/div/div/div");
			mouse("//div[@data-plan='"+plans[i]+"']/div[6]/div/div[2]/div/div/div/div");
		
			
			// Check that the Name field for the CB is enabled and not empty.
			waitForElement(OptionPlan+"span/input");
			assertFalse(selenium.isElementPresent(OptionPlan+"span/input[@disabled]"));
			Thread.sleep(2000);
			System.out.println(selenium.getText(OptionPlan+"span/input"));
			assertEquals(selenium.getText(OptionPlan+"span/input"),"");
			// Check that the Number Card field for the CB is enabled and not empty.
			waitForElement(OptionPlan+"span[2]/input");
			assertFalse(selenium.isElementPresent(OptionPlan+"span[2]/input[@disabled]"));
			assertEquals(selenium.getText(OptionPlan+"span[2]/input"),"");
			// Check that the month of expiration for the CB is enabled.
			waitForElement(OptionPlan+"span[3]/div[2]/select");
			assertFalse(selenium.isElementPresent(OptionPlan+"span[3]/div[2]/select[@disabled]"));
			// Check that the year of expiration for the CB is enabled.
			waitForElement(OptionPlan+"span[3]/div[2]/select[2]");
			assertFalse(selenium.isElementPresent(OptionPlan+"span[3]/div[2]/select[2][@disabled]"));
			
			// Click on the button change plan.
			waitForElement("//div[@data-plan='"+plans[i]+"']/div[6]/div/div[2]/div/div/div/div");
			mouse("//div[@data-plan='"+plans[i]+"']/div[6]/div/div[2]/div/div/div/div");
			
			// Check that the Name field for the CB is disabled.
			waitForElement(OptionPlan+"span/input");
			assertTrue(selenium.isElementPresent(OptionPlan+"span/input[@disabled]"));
			// Check that the Number Card field for the CB is disabled.
			waitForElement(OptionPlan+"span[2]/input");			
			assertTrue(selenium.isElementPresent(OptionPlan+"span[2]/input[@disabled]"));
			// Check the the Expiratiodecn date is disabled.
			waitForElement(OptionPlan+"span[3]/div[2]/select");
			assertTrue(selenium.isElementPresent(OptionPlan+"span[3]/div[2]/select[@disabled]"));
			waitForElement(OptionPlan+"span[3]/div[2]/select[2]");
			assertTrue(selenium.isElementPresent(OptionPlan+"span[3]/div[2]/select[2][@disabled]"));
			}
	}


	// This test allows to see if it is possible to ad 6 users or more with an Unlimited plan
	@Test 	
	public void testAdUserUnlimitedPlan () throws Exception {
		// Subscribe for an unlimited plan
			login("/index.jsp?#manage_subscription");
			Thread.sleep(1000);
			fillCB("unlimited", "John", "4005519200000004" );
			selenium.click(ChosenPlan+"div/div[6]/div[2]/button");
			waitForElement(Processing);
			Thread.sleep(5000);
			selenium.click(DoneButton);
			Thread.sleep(1000);
			selenium.open("#manage_users");
			Thread.sleep(1000);
			
			for (int i = 0; i < 6; i++) {
				waitForElement("//div[@class=\"label\"]");
				long time = time();	
				Thread.sleep(3000);	
				selenium.click(AddUserButton);
				Thread.sleep(1000);
				selenium.type(UserInfoField+"div[1]/input","Alex"+time+"");
				selenium.type(UserInfoField+"div[2]/input","alex"+time+"@gmail.com");
				selenium.type(UserInfoField+"div[3]/div/input","qwerty");
				selenium.click(DoneButton);
				Thread.sleep(1000);
				selenium.open("#manage_users");
				Thread.sleep(1000);	
			}
		}	

	//This test allows to check if the "close" button is always available for all plan in the CB information field
	@Test
	public void testCloseButtonAlwaysAvailable () throws Exception {
		// Open unlimited plan box
			login("/index.jsp?#manage_subscription");
			fillCB("unlimited", "John", "4005519200000004" );
			selenium.click(ChosenPlan+"div/div[6]/a");
			assertTrue(selenium.isElementPresent("//div[@data-plan=\"unlimited\"]//div[@style=\"display: none;\"]"));
		// Open team plan box
			fillCB("team", "John", "4005519200000004" );
			selenium.click(ChosenPlan+"div[2]/div[6]/a");
			assertTrue(selenium.isElementPresent("//div[@data-plan=\"team\"]//div[@style=\"display: none;\"]"));
		// Open plus plan box
			fillCB("plus", "John", "4005519200000004" );
			selenium.click(ChosenPlan+"div[3]/div[6]/a");
			assertTrue(selenium.isElementPresent("//div[@data-plan=\"plus\"]//div[@style=\"display: none;\"]"));
		}
	
	
	//This test allows to check if the "done" button is always available in the "Manage subscription" section"
	@Test
	public void testDoneButtonAlwaysAvailable () throws Exception {
			
		// Subscribe for an unlimited plan
			login("/index.jsp?#manage_subscription");
			Thread.sleep(2000);
			selenium.click("//div[@data-plan=\"unlimited\"]//button");
			assertTrue(selenium.isElementPresent(DoneButton));
		// Open team plan box
			selenium.click("//div[@data-plan=\"team\"]//button");
			assertTrue(selenium.isElementPresent(DoneButton));
		// Open plus plan box
			selenium.click("//div[@data-plan=\"plus\"]//button");
			assertTrue(selenium.isElementPresent(DoneButton));
		// Open free plan box
			selenium.click("//div[@data-plan=\"free\"]//button");
			assertTrue(selenium.isElementPresent(DoneButton));
		// Open "payment" tab
			mouse(PaymentTab);
			waitForElement(DisplayedPlan);
			assertTrue(selenium.isElementPresent(DoneButton));
		// Open "history" tab
			mouse(HistoryTab);
			waitForElement(DisplayedPlan);
			assertTrue(selenium.isElementPresent(DoneButton));
		
		}	

	//This test allows to check if the situation summary banner is always available in the "Manage subscription" section
	@Test
	public void testBlueBannerAlwaysPresent () throws Exception {
		// Subscribe for an unlimited plan
			login("/index.jsp?#manage_subscription");
			Thread.sleep(1000);
			fillCB("unlimited", "John", "4111111111111111" );
			Thread.sleep(1000);
			selenium.click(ChosenPlan+"div/div[6]/div[2]/button");
			waitForElement(Processing);
			Thread.sleep(3000);
			selenium.click(DoneButton);
			Thread.sleep(1000);
			selenium.open("#manage_subscription");
			Thread.sleep(5000);
		// Check if it is displayed in the "plans" tab
			assertTrue(selenium.isElementPresent(DisplayedPlan));
			Thread.sleep(1000);
		// Check if it is displayed in the "payment" tab
			waitForElement("//div[@id='contentRootPanel']/div/div/div/div/div/div/div[2]");
			assertEquals(selenium.getAlert(), "0 ");
			mouse("//div[@id='contentRootPanel']/div/div/div/div/div/div/div[2]/");
			Thread.sleep(1000);
			assertTrue(selenium.isElementPresent(DisplayedPlan));
		// Check if it is displayed in the "history" tab
			waitForElement("//div[@id='contentRootPanel']/div/div/div/div/div/div/div[3]");
			mouse("//div[@id='contentRootPanel']/div/div/div/div/div/div/div[3]");
			Thread.sleep(1000);
			assertTrue(selenium.isElementPresent(DisplayedPlan));
		}

	//This test allows to check if the Summary banner displays the chosen plan
		@Test
		public void testSummaryBanner () throws Exception {
		// Subscribe for an unlimited plan
			login("/index.jsp#manage_subscription");
			waitForElement("//div[@data-plan=\"free\"]");	
		// Check that the plan displayed is "free":
			assertTrue(selenium.isElementPresent("//div[@data-plan=\"free\"]//button[@disabled]"));
			assertEquals(selenium.getText(DisplayedPlan+"div/div[2]"),selenium.getText(ChosenPlan+"div[4]/div/div[1]"));
		// Sign for an unlimited plan:
			fillCB( "unlimited", "John", "4111111111111111" );
			Thread.sleep(500);
			selenium.click(ChosenPlan+"div/div[6]/div[2]/button");
			waitForElement(Processing);
		// Check that the plan displayed is "unlimited"
			Thread.sleep(6000);
			assertTrue(selenium.isElementPresent("//div[@data-plan='unlimited']//button[@disabled]"));
			assertEquals(selenium.getText(DisplayedPlan+"div/div[2]"),selenium.getText(ChosenPlan+"div/div/div[1]"));
		// Sign for a team plan:
			selenium.click("//div[@data-plan='team']//button");
			waitForElement(ChosenPlan+"div[2]/div[6]/div[2]/button");
			selenium.click(ChosenPlan+"div[2]/div[6]/div[2]/button");
			waitForElement(Processing);
			Thread.sleep(6000);
			assertTrue(selenium.isElementPresent("//div[@data-plan='team']//button[@disabled]"));
			assertEquals(selenium.getText(DisplayedPlan+"div/div[2]"),selenium.getText(ChosenPlan+"div[2]/div/div[1]"));
		// Sign for a plus plan:
			selenium.click("//div[@data-plan='plus']//button");
			waitForElement(confirmPlusPlanButton);
			selenium.click(confirmPlusPlanButton);
			waitForElement(Processing);	
		// Check that the plan displayed is "plus":
			Thread.sleep(6000);
			assertTrue(selenium.isElementPresent("//div[@data-plan='plus']//button[@disabled]"));
			assertEquals(selenium.getText(DisplayedPlan+"div/div[2]"),selenium.getText(ChosenPlan+"div[3]/div/div[1]"));	
		}
		
	// This test is to check that the information of the credit card can be changed in the Payment tab
		@Test
		public void testChangeCreditCardInfoPaymentTab () throws Exception {
		// Subscribe for an unlimited plan
			login("/index.jsp#manage_subscription");
			waitForElement("//div[@data-plan=\"free\"]");	
			fillCB( "unlimited", "John", "4111111111111111" );
			Thread.sleep(500);
			selenium.click(ChosenPlan+"div/div[6]/div[2]/button");
			waitForElement(Processing);
			Thread.sleep(6000);
			mouse(PaymentTab);
			Thread.sleep(500);
			selenium.click(ChosenPlan+"div/div/div/div/div");
			Thread.sleep(500);
			selenium.type(ChosenPlan+"div/div/div[2]/div/span/input","Alex");
			selenium.type(ChosenPlan+"div/div/div[2]/div/span[2]/input","4005519200000004");
			selenium.click(ChosenPlan+"div[2]/button");	
		}
		
	// This test checks the prorata when a customer is moving to another plan.
		@Test
	    public void testProrata() throws Exception {
	        login("/index.jsp#manage_subscription");
	        Thread.sleep(3000);
	        fillCB("plus","alex","4111111111111111");
	        selenium.click("//div[@data-plan='plus']//div[starts-with(@id, 'gwt-uid-')]//button");
	        waitForElement("//div[@data-plan='plus']//div[starts-with(@id, 'gwt-uid-') and @style='display: none;']");
	        selenium.click(DoneButton);
	       
	        Calendar cal = Calendar.getInstance();
	        double daysinmonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        double remainingdays = daysinmonth - cal.get(Calendar.DAY_OF_MONTH);
	       
	        double ratio = (remainingdays/daysinmonth);
	        double ratiodec = round2Decimals(ratio);
	       
	        String plan[] = {"team", "unlimited"};
	        double alreadypaid = 0;

	        for (int i = 0; i<2; i++) {
	        String OptionPlan = "//div[@data-plan='"+plan[i]+"']/";
	        	
	        selenium.open("#manage_subscription");
	        selenium.waitForPageToLoad("30000");
	        Thread.sleep(8000);
	        waitForElement(OptionPlan+"/button");
	        selenium.click(OptionPlan+"/button");
	        Thread.sleep(5000);
	       
	        double priceplan = Integer.parseInt((selenium.getText(OptionPlan+"div[4]/div/span")).substring(1));
	        double pricedisplayed = Double.parseDouble((selenium.getText(OptionPlan+"div[6]/div/div/div[2]/span")).substring(1));
	       
	       
	        if (remainingdays == 0) {
	            alreadypaid = 0;
	        }
	        else {
	            Thread.sleep(5000);
	            alreadypaid = round2Decimals(alreadypaid + Double.parseDouble((selenium.getText(DisplayedPlan+"div[3]/div/div[@data-timestamp]")).substring(1,6)));
	        }
	       
	       
	        double newpriceplan = ratiodec*priceplan;
	        double newpriceplandec = round2Decimals(newpriceplan);
	       
	        double pricetopay = round2Decimals(newpriceplandec - alreadypaid);
	               
	        if (remainingdays == 0) {
	            assertEquals(priceplan,pricedisplayed);
	        }
	        else {
	            assertEquals(pricetopay,pricedisplayed);
	        }
	        selenium.click(OptionPlan+"/div[starts-with(@id, 'gwt-uid-')]//button");
	        Thread.sleep(2000);
	        waitForElement(OptionPlan+"/div[starts-with(@id, 'gwt-uid-') and @style='display: none;']");
	        selenium.click(DoneButton);
	        }
	    }
		
		// This test checks that an error message is displayed when no name is typed in the CB field.
	    @Test
	    public void testErrorNameCB () throws Exception {
	        login("/index.jsp#manage_subscription");
	        Thread.sleep(3000);
	        fillCB("unlimited","","4111111111111111");
	        selenium.typeKeys("//div[@data-plan='unlimited']/div[6]/div/div[2]/div/div[2]/div/span/input","");
	        selenium.fireEvent("//div[@data-plan='unlimited']/div[6]/div/div[2]/div/div[2]/div/span/input","blur");
	        selenium.typeKeys("//div[@data-plan='unlimited']/div[6]/div/div[2]/div/div[2]/div/span[2]/input","");
	        selenium.fireEvent("//div[@data-plan='unlimited']/div[6]/div/div[2]/div/div[2]/div/span[2]/input","blur");
	        Thread.sleep(2000);
	        selenium.click("//div[@data-plan=\"unlimited\"]//div[starts-with(@id, 'gwt-uid-')]//button");
	        selenium.waitForPopUp("","20000");
	    }
	   
	   
	    // This test checks that a wrong card number cannot be accepted.
	    @Test
	    public void testErrorNumberCB () throws Exception {
	        login("/index.jsp#manage_subscription");
	       
	        String number[] = {"1237872837" , "98765456789876567876567" , "sdfjuh65456"};
	        for (int i=0; i<3; i++) {
	        selenium.open("#manage_subscription");
	        Thread.sleep(3000);
	        fillCB("unlimited","Alex",number[i]);
	        Thread.sleep(2000);
	        selenium.click("//div[@data-plan=\"unlimited\"]//div[starts-with(@id, 'gwt-uid-')]//button");
	        selenium.waitForPopUp("","20000");
	        }
	    }
	   
	    // This test checks that the date of expiration cannot be in the past.
	    @Test
	    public void testDateCB () throws Exception {
	        login("/index.jsp#manage_subscription");
	        Thread.sleep(3000);
	        fillCB("unlimited","Alex","4111111111111111");
	        Thread.sleep(2000);
	        selenium.select("//div[@data-plan='unlimited']/div[6]/div/div[2]/div/div[2]/div/span[3]/div[2]/select","01");
	        selenium.click("//div[@data-plan='unlimited']//div[starts-with(@id, 'gwt-uid-')]//button");
	        selenium.waitForPopUp("","20000");
	    }
	   
	   
	    // This test checks that a user have to check the box for paying.
	    @Test
	    public void testUncheckCB () throws Exception {
	        login("/index.jsp#manage_subscription");
	        Thread.sleep(3000);
	        fillCB("unlimited","Alex","4111111111111111");
	        Thread.sleep(2000);
	        selenium.uncheck("//div[@data-plan='unlimited']/div[6]div[2]/div/span/input");
	        selenium.click("//div[@data-plan='unlimited']//div[starts-with(@id, 'gwt-uid-')]//button");
	        selenium.waitForPopUp("","20000");
	    }
		
}
