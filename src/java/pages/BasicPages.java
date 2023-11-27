package pages;

import io.qameta.allure.Step;

public class BasicPages {
    @Step("Print {message} message")
    public void printOutAssert(String message) {
        System.out.println(message);
    }
}
