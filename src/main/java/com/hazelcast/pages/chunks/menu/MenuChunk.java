package com.hazelcast.pages.chunks.menu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$x;

@Component
@SuppressWarnings("checkstyle:methodcount")
public class MenuChunk {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuChunk.class);

    // Cluster menu
    private static final String ADMINISTRATION_MENU_XPATH = "//a[@data-test='menu-administration']";
    private static final String SCRIPTING_MENU_XPATH = "//a[@data-test='menu-scripting']";
    private static final String CONSOLE_MENU_XPATH = "//a[@data-test='menu-console']";

    private static final int MENU_TIMEOUT = 500;

    private SelenideElement getAdministrationMenuButton() {
        return $x(ADMINISTRATION_MENU_XPATH);
    }

    private SelenideElement getScriptingMenuButton() {
        return $x(SCRIPTING_MENU_XPATH);
    }

    private SelenideElement getConsoleMenuButton() {
        return $x(CONSOLE_MENU_XPATH);
    }


    @Step("Get 'Administration' menu visibility status")
    public Boolean administrationMenuIsVisible() {
        LOGGER.debug("Getting the 'Administration' menu visibility status");
        return getAdministrationMenuButton().waitUntil(Condition.visible, MENU_TIMEOUT).isDisplayed();
    }

    @Step("Get 'Scripting' menu visibility status")
    public Boolean scriptingMenuIsVisible() {
        LOGGER.debug("Getting the 'Scripting' menu visibility status");
        return getScriptingMenuButton().is(Condition.visible);
    }

    @Step("Get 'Console' menu visibility status")
    public Boolean consoleMenuIsVisible() {
        LOGGER.debug("Getting the 'Console' menu visibility status");
        return getConsoleMenuButton().is(Condition.visible);
    }
}
