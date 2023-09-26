package tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Basic tests
 */
public class AlwaysTests {
    @Test
    @Feature("Pass")
    public void alwaysPass() {
        boolean alwaysTrue = true;
        System.out.println("Condition is: " + alwaysTrue);
        Assert.assertTrue(alwaysTrue);
    }

    @Test
    @Feature("Fail")
    public void alwaysFail() {
        boolean alwaysFalse = false;
        System.out.println("Condition is: " + alwaysFalse);
        Assert.assertTrue(alwaysFalse);
    }
}
