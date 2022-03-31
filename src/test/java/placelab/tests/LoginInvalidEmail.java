package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class LoginInvalidEmail {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String email = "some.invalid@email.com";
    private String password = System.getProperty("password");
    private String expectedErrorMessage = "Invalid credentials!";

    //Specify the driver and browser that will be used for this scenario

    @BeforeSuite
    public void openPlaceLab() {
        //Go to PlaceLab demo app
        driver = WebDriverSetup.getWebDriver("edge");
        driver.navigate().to(host);
    }

    //Actual test case implementation

    @Test
    public void testLoginInvalidEmail() {

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        //Enter invalid email and valid password and click button submit
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();
        //Validate error message
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class = 'error-area']")).getText();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);

    }

    //Clean up - close the browser

    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}
