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

public class AddEmployee {
    WebDriver chrome;
    String url = "http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login";

    @BeforeTest
    public void openBrowserAndNavigate() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        chrome.get(url);
    }

    @Test(priority = 0)
    public void validAdminLogin() {
        chrome.findElement(By.id("txtUsername")).sendKeys("Admin");
        chrome.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        chrome.findElement(By.id("btnLogin")).click();
        WebElement welcomeMessage = chrome.findElement(By.xpath("//a [contains (text(), 'Welcome')]"));
        Assert.assertTrue(welcomeMessage.isDisplayed(), "Welcome message is not displayed");
    }

    @Test(dependsOnMethods = "validAdminLogin")
    public void addEmployee() throws InterruptedException {
        chrome.findElement(By.xpath("//b [text() = 'PIM']")).click();
        Thread.sleep(4000);
        chrome.findElement(By.xpath("//a [text() = 'Add Employee']")).click();
        Thread.sleep(4000);
        WebElement fullnameLabel = chrome.findElement(By.xpath("//label [@class = 'hasTopFieldHelp']"));
        WebElement employeeidLabel = chrome.findElement(By.xpath("//label [@for = 'employeeId']"));
        WebElement photographLabel = chrome.findElement(By.xpath("//label [@for = 'photofile']"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(fullnameLabel.isDisplayed(), "Full Name label is not displayed");
        softAssert.assertTrue(employeeidLabel.isDisplayed(), "Employee Id label is displayed");
        softAssert.assertTrue(photographLabel.isDisplayed(), "Photograph label is not displayed");

        chrome.findElement(By.id("firstName")).sendKeys("Michael");
        chrome.findElement(By.id("lastName")).sendKeys("Scott");
        chrome.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\Bultozavrik\\Downloads\\Sloth.jpg");
        chrome.findElement(By.id("btnSave")).click();
        WebElement emergencyContacts = chrome.findElement(By.xpath("//a [text() = 'Emergency Contacts']"));
        softAssert.assertTrue(emergencyContacts.isDisplayed(), "Employee was not added");
        WebElement employeeID = chrome.findElement(By.id("personal_txtEmployeeId"));
        System.out.println("Employee with ID " + employeeID.getText() + " was created");
    }
    @AfterTest
    public void quitBrowser() {
        chrome.quit();
    }
}
