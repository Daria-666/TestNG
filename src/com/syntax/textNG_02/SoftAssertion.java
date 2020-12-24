package com.syntax.textNG_02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class SoftAssertion {
    WebDriver chrome;
    String url = "http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login";
    @BeforeTest
    public void openBrowserAndNavigate () {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        chrome.get(url);
    }
    @Test
    public void logoAndValidAdminLogin () {
        WebElement syntaxLogo = chrome.findElement(By.id("divLogo"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(syntaxLogo.isDisplayed(), "Logo is not displayed");

        chrome.findElement(By.id("txtUsername")).sendKeys("Admin");
        chrome.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        chrome.findElement(By.id("btnLogin")).click();
        WebElement welcomeMessage = chrome.findElement(By.id("welcome"));
        softAssert.assertTrue(welcomeMessage.isDisplayed(), "Login failed");
        softAssert.assertAll();
    }
    @AfterTest
    public void quitBrowser () {
        chrome.quit();
    }
}
