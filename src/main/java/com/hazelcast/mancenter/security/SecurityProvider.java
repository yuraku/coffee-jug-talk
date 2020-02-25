package com.hazelcast.mancenter.security;

public enum SecurityProvider {

    DEFAULT("Default"),
    JAAS("JAAS"),
    LDAP("LDAP"),
    AD("Active Directory");

    private final String value;

    SecurityProvider(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
