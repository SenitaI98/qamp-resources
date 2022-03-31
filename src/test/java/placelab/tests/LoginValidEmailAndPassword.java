package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class LoginValidEmailAndPassword {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String email = System.getProperty("email");
    private String password = System.getProperty("password");
    private String expectedUrl = "https://demo.placelab.com/dashboard/traffic";

    //Specify the driver and browser that will be used for this scenario

    @BeforeSuite
    public void openPlaceLab() {
        //Go to PlaceLab demo app
        driver = WebDriverSetup.getWebDriver("chrome");
        driver.navigate().to(host);
    }

    //Actual test case implementation

    @Test
    public void testLogin() {

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        //Enter valid email and password, click button submit
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();
        //Validate opened url
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

    }

    //Clean up - close the browser
    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}
