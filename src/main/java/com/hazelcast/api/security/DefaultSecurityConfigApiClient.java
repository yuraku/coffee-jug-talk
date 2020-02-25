package com.hazelcast.api.security;

import com.hazelcast.api.BaseApiClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

@Component
public class DefaultSecurityConfigApiClient extends BaseApiClient {

    public void createDefaultSecurityConfig(String adminName, String adminPassword) {
        given(requestSpecification())
                .basePath("/security/saveConfig")
                .formParams(getDefaultSecurityConfig(adminName, adminPassword))
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    private Map<String, String> getDefaultSecurityConfig(String adminName, String adminPassword) {

        Map<String, String> defaultSecurityConfig = new HashMap<>();

        defaultSecurityConfig.put("userNameInput", adminName);
        defaultSecurityConfig.put("passwordInput", adminPassword);
        defaultSecurityConfig.put("confirmPasswordInput", adminPassword);
        defaultSecurityConfig.put("usernameInput", adminName);
        defaultSecurityConfig.put("providerHiddenInput", "Default");

        return defaultSecurityConfig;
    }
}
