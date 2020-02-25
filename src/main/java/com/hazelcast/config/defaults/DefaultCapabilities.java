package com.hazelcast.config.defaults;

public interface DefaultCapabilities {

    String BS_LOCAL_IDENTIFIER = System.getProperty("browserstack.localIdentifier");

    Boolean BROWSERSTACK_DEBUG = true;
    Boolean BROWSERSTACK_IE_ENABLE_POPUPS = true;
    Boolean BROWSERSTACK_SAFARI_ENABLE_POPUPS = true;
    Boolean BROWSERSTACK_LOCAL = true;
    String BROWSERSTACK_LOCAL_IDENTIFIER = BS_LOCAL_IDENTIFIER == null ? "MC_UI_TESTS" : BS_LOCAL_IDENTIFIER;
    String BROWSERSTACK_CONSOLE = "warnings";
}
