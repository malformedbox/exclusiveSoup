package tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasicPages;

/*
 * Math tests
 */
public class SimpleMath {
    BasicPages basicPages = new BasicPages();
    @Test
    @Feature("Addition")
    public void addition() {
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
}
