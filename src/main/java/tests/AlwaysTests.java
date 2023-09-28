package tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasicPages;

/*
 * Basic tests
 */
public class AlwaysTests {
    BasicPages basicPages = new BasicPages();
    @Test
    @Feature("Pass")
    public void alwaysPass() {
        boolean alwaysTrue = true;
        String message = "Condition is: " + alwaysTrue;
        basicPages.printOutAssert(message);
        Assert.assertTrue(alwaysTrue);
    }

    @Test
    @Feature("Fail")
    public void alwaysFail() {
        boolean alwaysFalse = true;
        String message = "Condition is: " + alwaysFalse;
        basicPages.printOutAssert(message);
        Assert.assertTrue(alwaysFalse);
    }
}
