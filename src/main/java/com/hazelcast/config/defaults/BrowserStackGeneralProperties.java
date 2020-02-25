package com.hazelcast.config.defaults;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/browserstack_general.properties"})
public interface BrowserStackGeneralProperties extends Config {

    @Key("browserstack.server")
    String bsServer();

    @Key("browserstack.username")
    String bsUsername();

    @Key("browserstack.accessKey")
    String bsAccessKey();
}
