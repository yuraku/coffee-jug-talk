package com.hazelcast.config.defaults;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

@Component
public class BrowserCapabilities {

    public DesiredCapabilities setBrowserStackBrowserCapabilities(DesiredCapabilities capabilities) {

        BrowserStackBrowserProperties bsConfig = getBrowserStackBrowserConfig();
        capabilities.setBrowserName(bsConfig.webBrowser());
        capabilities.setCapability(EnvironmentSpecificCapabilities.BROWSER_VERSION.getValue(), bsConfig.browserVersion());
        capabilities.setCapability(EnvironmentSpecificCapabilities.OS.getValue(), bsConfig.os());
        capabilities.setCapability(EnvironmentSpecificCapabilities.OS_VERSION.getValue(), bsConfig.osVersion());
        capabilities.setCapability(EnvironmentSpecificCapabilities.RESOLUTION.getValue(), bsConfig.resolution());
        return capabilities;
    }

    private BrowserStackBrowserProperties getBrowserStackBrowserConfig() {
        String browser = System.getProperty("browser");
        ConfigFactory.setProperty("browser", browser);
        return ConfigFactory.create(BrowserStackBrowserProperties.class);
    }
}
