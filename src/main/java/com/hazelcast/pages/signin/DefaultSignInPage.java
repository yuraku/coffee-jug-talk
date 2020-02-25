package com.hazelcast.pages.signin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.hazelcast.pages.BasePage;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class DefaultSignInPage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSignInPage.class);

    private static final String USERNAME_FIELD_CSS = "input[name='username']";
    private static final String PASSWORD_FIELD_CSS = "input[name='password']";
    private static final String LOGIN_BUTTON_CSS = "[type='submit']";

    private SelenideElement getUsernameField() {
        return $(USERNAME_FIELD_CSS).shouldBe(Condition.visible);
    }

    private SelenideElement getPasswordField() {
        return $(PASSWORD_FIELD_CSS).shouldBe(Condition.visible);
    }

    private SelenideElement getLoginButton() {
        return $(LOGIN_BUTTON_CSS).shouldBe(Condition.visible);
    }

    @Step("Perform login with username {0} and password {1}")
    public void login(String username, String password) {
        LOGGER.debug("Perform login with specified user and password");
        waitForLoginPageLoad();
        getUsernameField().clear();
        getUsernameField().sendKeys(username);
        getPasswordField().clear();
        getPasswordField().sendKeys(password);
        getLoginButton().click();
    }
}
