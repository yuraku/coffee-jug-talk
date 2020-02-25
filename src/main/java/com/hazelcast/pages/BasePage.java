package com.hazelcast.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.hazelcast.config.FrameworkConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.codeborne.selenide.Selenide.$;

@ContextConfiguration(classes = {FrameworkConfig.class})
@ExtendWith({SpringExtension.class})
public abstract class BasePage {

    private static final long DEFAULT_WAIT_TIME = 120;
    private static final String LOGIN_ROOT_ELEMENT_CSS = "[id='react-app-auth']";
    private static final String MC_ROOT_ELEMENT_CSS = "[id='mc']";

    protected void waitForLoginPageLoad() {

        ExpectedCondition<Boolean> pageLoadCondition = driver ->
                driver != null && ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete");

        new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_WAIT_TIME).until(pageLoadCondition);

        $(LOGIN_ROOT_ELEMENT_CSS).shouldBe(Condition.appear);
    }

    protected void waitForPageLoad() {

        ExpectedCondition<Boolean> pageLoadCondition = driver ->
                driver != null && ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete");

        new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_WAIT_TIME).until(pageLoadCondition);

        $(MC_ROOT_ELEMENT_CSS).shouldBe(Condition.appear);
    }
}
