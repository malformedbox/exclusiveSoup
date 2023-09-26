package tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Basic tests
 */
public class SimpleMath {
    @Test
    @Feature("Addition")
    public void addition() {
        int sum = 4;
        Assert.assertEquals(2, 2, sum);
    }

    @Test
    @Feature("Subtraction")
    public void subtraction() {
        int difference = 1;
        Assert.assertEquals(5, 4, difference);
    }
}
