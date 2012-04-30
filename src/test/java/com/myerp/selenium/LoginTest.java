package com.myerp.selenium;

import com.thoughtworks.selenium.*;
import org.junit.*;
import com.testingbot.*;

public class LoginTest extends TestingBotTestCase {
  public void setUp() throws Exception {
    TestingBotSelenium selenium = new TestingBotSelenium(
            "hub.testingbot.com",
            4444,
            "firefox",
            "http://ec2-23-20-169-224.compute-1.amazonaws.com");

    this.selenium = selenium;
    selenium.start("version=10;platform=WINDOWS;screenrecorder=false");
    
    // print sessionID in output so that our Jenkins plugin maps the sessionID to videos/screenshots
    System.out.println("TestingBotSessionID=" + this.selenium.getEval("selenium.sessionId"));
  }

  public void testLogin() throws Exception {
    this.selenium.open("/login");
    assertEquals("Sign In | myERP.com", this.selenium.getTitle());
    this.selenium.type("//*[@id=\"userUsername\"]", "alex.myerp@gmail.com");
    this.selenium.fireEvent("//*[@id=\"userUsername\"]", "blur");
    this.selenium.type("//*[@id=\"userPassword\"]", "311088");
    this.selenium.fireEvent("//*[@id=\"userPassword\"]", "blur");
    this.selenium.click("css=input[type=\"image\"]");
    this.selenium.waitForPageToLoad("30000");
  }

  public void tearDown() throws Exception {
    this.selenium.stop();
  }
}
