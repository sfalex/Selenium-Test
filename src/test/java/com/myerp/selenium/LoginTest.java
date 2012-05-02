package com.myerp.selenium;

import org.junit.*;
import com.thoughtworks.selenium.*;
import com.testingbot.*;

public class LoginTest extends MyERPTestCase {
	@Test
	public void testLogin() throws Exception {
		selenium.open("/login");
		assertEquals("Sign In | myERP.com", selenium.getTitle());
		selenium.type("//*[@id=\"userUsername\"]", "alex.myerp@gmail.com");
    		selenium.fireEvent("//*[@id=\"userUsername\"]", "blur");
    		selenium.type("//*[@id=\"userPassword\"]", "311088");
    		selenium.fireEvent("//*[@id=\"userPassword\"]", "blur");
    		selenium.click("css=input[type=\"image\"]");
    		selenium.waitForPageToLoad("30000");
	}
}
