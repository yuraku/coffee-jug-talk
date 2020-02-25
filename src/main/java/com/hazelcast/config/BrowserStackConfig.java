package com.hazelcast.config;

import com.hazelcast.config.defaults.BrowserCapabilities;
import com.hazelcast.config.defaults.BrowserStackGeneralProperties;
import com.hazelcast.config.defaults.DefaultCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@Import(FrameworkConfig.class)
public class BrowserStackConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserStackConfig.class);

    BrowserCapabilities browserCapabilities = new BrowserCapabilities();

    private DesiredCapabilities capabilities = new DesiredCapabilities();

    public WebDriver browserStackSetup(BrowserStackGeneralProperties generalProperties) {
        setGeneralCapabilities();
        setEnvironmentCapabilities();
        return new RemoteWebDriver(getBrowserStackURL(generalProperties), capabilities);
    }

    private void setGeneralCapabilities() {
        capabilities.setCapability("browserstack.debug", DefaultCapabilities.BROWSERSTACK_DEBUG);
        capabilities.setCapability("browserstack.ie.enablePopups", DefaultCapabilities.BROWSERSTACK_IE_ENABLE_POPUPS);
        capabilities.setCapability("browserstack.safari.enablePopups",
                DefaultCapabilities.BROWSERSTACK_SAFARI_ENABLE_POPUPS);
        capabilities.setCapability("browserstack.local", DefaultCapabilities.BROWSERSTACK_LOCAL);
        capabilities.setCapability("browserstack.localIdentifier", DefaultCapabilities.BROWSERSTACK_LOCAL_IDENTIFIER);
        capabilities.setCapability("browserstack.console", DefaultCapabilities.BROWSERSTACK_CONSOLE);
    }

    private void setEnvironmentCapabilities() {
        LOGGER.debug("Setup BrowserStack service execution environment");
        capabilities = browserCapabilities.setBrowserStackBrowserCapabilities(capabilities);
    }

    private URL getBrowserStackURL(BrowserStackGeneralProperties properties) {

        LOGGER.debug("Get BrowserStack service URL");
        URL browserStackURL = null;

        try {
            browserStackURL = new URL(
                    "http://" + properties.bsUsername() + ":" + properties.bsAccessKey() + "@" + properties.bsServer()
                            + "/wd/hub");
        } catch (MalformedURLException e) {
            LOGGER.error(String.format("Something went wrong while building the URL for Browserstack: %s", e));
        }

        return browserStackURL;
    }
}
