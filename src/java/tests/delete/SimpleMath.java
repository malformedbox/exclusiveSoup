package tests.delete;

import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasicPages;

import java.net.MalformedURLException;
import java.net.URL;

/*
 * Math tests
 */
public class SimpleMath {
    public WebDriver driver;
    BasicPages basicPages = new BasicPages();
    @Test
    @Feature("Addition")
    public void addition() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "LATEST");
        System.out.println(System.getProperty("bsUsername"));
        System.out.println(System.getProperty("bsAccessKey"));
        try {
            WebDriver driver = new RemoteWebDriver(new URL("https://" + System.getProperty("bsUsername") + ":" + System.getProperty("bsAccessKey") + "@hub-cloud.browserstack.com/wd/hub"), chromeOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        int sum = 4;
        basicPages.printOutAssert("Sum is: " + sum);
        Assert.assertEquals(2, 2, sum);
    }

    @Test
    @Feature("Subtraction")
    public void subtraction() {
        int difference = 1;
        basicPages.printOutAssert("Difference is: " + difference);
        Assert.assertEquals(5, 4, difference);
    }

    @Test
    @Feature("Comparing secret value")
    public void testValue() {
        String testValue = System.getenv("VALUE");
        basicPages.printOutAssert("Comparing the values: " + testValue);
        Assert.assertEquals(testValue, "abc123");
    }
}
