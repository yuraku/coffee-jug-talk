package com.hazelcast.config.defaults;

public enum EnvironmentSpecificCapabilities {

    BROWSER_VERSION("browser_version"),
    OS("os"),
    OS_VERSION("os_version"),
    RESOLUTION("resolution");

    private final String value;

    EnvironmentSpecificCapabilities(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
