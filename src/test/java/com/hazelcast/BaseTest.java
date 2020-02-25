package com.hazelcast;

import com.browserstack.local.Local;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.hazelcast.api.security.DefaultSecurityConfigApiClient;
import com.hazelcast.config.BrowserStackConfig;
import com.hazelcast.config.FrameworkConfig;
import com.hazelcast.config.defaults.BrowserCapabilities;
import com.hazelcast.config.defaults.BrowserStackGeneralProperties;
import com.hazelcast.config.defaults.DefaultCapabilities;
import com.hazelcast.harness.TestConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@ContextConfiguration(classes = {FrameworkConfig.class})
@ExtendWith(value = {SpringExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    private static Local bsLocal;
    private static Boolean isBrowserStackOn = runOnBrowserStack();


    @Autowired
    BrowserStackConfig browserstackConfig;

    @Autowired
    BrowserCapabilities browserCapabilities;


    @BeforeAll()
    public void setup() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));

        setupBrowserStackLocal();

        new DefaultSecurityConfigApiClient()
                .createDefaultSecurityConfig(TestConstants.DEFAULT_USER, TestConstants.DEFAULT_PASSWORD);
    }

    @AfterAll()
    public void teardown() {
        closeBrowserStackLocal();
    }

    @BeforeEach()
    public void browserSetup(TestInfo testInfo) {

        if (isBrowserStackOn) {
            LOGGER.debug("Setup browser for the test execution");

            WebDriverRunner.setWebDriver(browserstackConfig.browserStackSetup(getBrowserStackGeneralProperties()));
            ((RemoteWebDriver) getWebDriver()).setFileDetector(new LocalFileDetector());
        } else {
        WebDriverManager.chromedriver().setup();
        WebDriverRunner.setWebDriver(new ChromeDriver());
        }

        Configuration.startMaximized = true;

        logTestHeader(testInfo, "STARTED");
    }

    @AfterEach()
    public void teardown(TestInfo testInfo) {
        LOGGER.debug("Close the browser after test is finished");
        getWebDriver().quit();

        logTestHeader(testInfo, "FINISHED");
    }

    private void setupBrowserStackLocal() {

        LOGGER.debug("Setup BrowserStack Local configuration");
        if (runOnBrowserStack()) {
            bsLocal = new Local();
            HashMap<String, String> bsLocalArgs = new HashMap<>();
            bsLocalArgs.put("key", getBrowserStackGeneralProperties().bsAccessKey());
            bsLocalArgs.put("localIdentifier", DefaultCapabilities.BROWSERSTACK_LOCAL_IDENTIFIER);
            bsLocalArgs.put("forcelocal", "true");

            try {
                bsLocal.start(bsLocalArgs);
            } catch (Exception e) {
                LOGGER.error("Something went wrong while starting the local BrowserStack. " + e);
            }
        }
    }

    private static void closeBrowserStackLocal() {
        LOGGER.debug("Close the BrowserStack Local binary");
        if (bsLocal != null) {
            try {
                bsLocal.stop();
            } catch (Exception e) {
                LOGGER.error("For some reason local instance of BrowserStack can't be stopped " + e);
            }
        }
    }

    private static boolean runOnBrowserStack() {
        return Boolean.parseBoolean(System.getProperty("isBrowserstack"));
    }

    private BrowserStackGeneralProperties getBrowserStackGeneralProperties() {
        return ConfigFactory.create(BrowserStackGeneralProperties.class);
    }

    private void logTestHeader(TestInfo testInfo, String status) {
        LOGGER.info("- - - - - - - - - - - - - - - - - - - - - - - - - - - - *"
                + "\n"
                + "*****************************************************************************************************************************"
                + "\n"
                + "* TEST CASE: "
                + testInfo.getDisplayName()
                + " | HAS BEEN "
                + status
                + "\n"
                + "*****************************************************************************************************************************"
                + "\n");
    }
}
