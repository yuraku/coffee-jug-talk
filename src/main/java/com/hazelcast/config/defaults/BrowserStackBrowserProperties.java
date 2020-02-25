package com.hazelcast.config.defaults;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/${browser}/browser.properties"})
public interface BrowserStackBrowserProperties extends Config {

    String browser();

    @Key("browserstack.web.browser")
    String webBrowser();

    @Key("browserstack.web.browser.version")
    String browserVersion();

    @Key("browserstack.web.os")
    String os();

    @Key("browserstack.web.os.version")
    String osVersion();

    @Key("browserstack.web.resolution")
    String resolution();
}
