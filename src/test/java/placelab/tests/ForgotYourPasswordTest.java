package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class ForgotYourPasswordTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String expectedUrl = "https://demo.placelab.com/password/forgot";

    //Specify the driver and browser that will be used for this scenario

    @BeforeSuite
    public void openPlaceLab() {
        //Go to PlaceLab demo app
        driver = WebDriverSetup.getWebDriver("chrome");
        driver.navigate().to(host);
    }

    //Actual test case implementation

    @Test
    public void testForgotPasswordButton() {

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        //Click button Forgot your password?
        driver.findElement(By.xpath("//div[@id = 'password-area']/a[@class = 'link-btn']")).click();
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
