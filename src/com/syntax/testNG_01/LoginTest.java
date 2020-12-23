package com.syntax.testNG_01;

import com.sun.xml.internal.ws.api.server.WebServiceContextDelegate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        WebElement welcomeMessage = chrome.findElement(By.xpath("//a [text() = 'Welcome Admin']"));
        if (welcomeMessage.isDisplayed()) {
            System.out.println("Login is successful");
        } else {
            System.out.println("Not logged in, Test failed");
        }
    }
    @Test
    public void titleValidation () {
        String actualTitle = chrome.getTitle();
        String expectedTitle = "Human Management System";
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Title verified, Test pass");
        } else {
            System.out.println("Wrong title, Test fail");
        }
    }
    @Test
    public void logeVerification () {
        WebElement syntaxLogo = chrome.findElement(By.xpath("//img [ @src = '/humanresources/symfony/web/webres_5acde3dbd3adc6.90334155/themes/default/images/login/syntax.png']"));
        if (syntaxLogo.isDisplayed()) {
            System.out.println("Logo is displayed, Test pass");
        } else {
            System.out.println("No logo, Test fail");
        }
    }
    @AfterTest
    public void quitBrowser () {
        chrome.quit();
    }

}
