package com.hazelcast.harness;

public final class TestConstants {

    private TestConstants() {
    }

    public static final String BASE_URL;
    public static final String MEMBER;

    public static final String DEFAULT_USER = "admin";
    public static final String DEFAULT_PASSWORD = "t3stP@ss";

    public static final String DEFAULT_CLUSTER_NAME = "dev";

    private static final String DEFAULT_INTERFACE = "127.0.0.1";
    private static final String DEFAULT_PORT = "5701";

    static {
        String mcStartUrl = System.getenv("MC_START_URL");
        BASE_URL = mcStartUrl == null ? "http://localhost:8080" : mcStartUrl;

        String memberHost = System.getenv("MEMBER_HOST");
        MEMBER = memberHost == null ? DEFAULT_INTERFACE.concat(":").concat(DEFAULT_PORT) : memberHost.concat(":").concat(DEFAULT_PORT);
    }
}
