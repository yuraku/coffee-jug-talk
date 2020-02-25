package com.hazelcast.pages.chunks.toolbar;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.hazelcast.pages.BasePage;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class ToolbarChunk extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolbarChunk.class);

    private static final String TOOLBAR_CSS = "[class='navbar-inner']";

    private SelenideElement getToolbarElement() {
        waitForPageLoad();
        return $(TOOLBAR_CSS).shouldBe(Condition.visible);
    }

    @Step("Return status of the page if it's displayed")
    public Boolean isDisplayed() {
        LOGGER.debug("Return status of the page if it's displayed");
        return getToolbarElement().isDisplayed();
    }
}
