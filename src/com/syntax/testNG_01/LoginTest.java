package com.syntax.testNG_01;

import com.sun.xml.internal.ws.api.server.WebServiceContextDelegate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LoginTest {
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
    public void validAdminLogin () {
        chrome.findElement(By.id("txtUsername")).sendKeys("Admin");
        chrome.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        chrome.findElement(By.id("btnLogin")).click();
        WebElement welcomeMessage = chrome.findElement(By.xpath("//a [contains (text(), 'Welcome')]"));
        Assert.assertTrue(welcomeMessage.isDisplayed(), "Welcome message is not displayed");
    }
    @Test
    public void titleValidation () {
        String actualTitle = chrome.getTitle();
        String expectedTitle = "Human Management System";
        Assert.assertEquals(actualTitle, expectedTitle, "Wrong Title is displayed");
    }
    @Test
    public void logoVerification () {
        WebElement syntaxLogo = chrome.findElement(By.id("divLogo"));
        Assert.assertTrue(syntaxLogo.isDisplayed(), "Logo is not displayed");
    }
    @Test
    public void invalidLogin () {
        chrome.findElement(By.id("txtUsername")).sendKeys("Admin");
        chrome.findElement(By.id("txtPasswordd")).sendKeys("Hum123");
        chrome.findElement(By.id("btnLogin")).click();
        WebElement errorMessage = chrome.findElement(By.id("spanMessage"));
        String actualMessage = errorMessage.getText();
        String expectedMessage = "Invalid credentials";
        Assert.assertEquals(actualMessage, expectedMessage, "Incorrect error message");
    }
    @AfterTest
    public void quitBrowser () {
        chrome.quit();
    }

}
