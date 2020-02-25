package com.hazelcast.api;

import com.hazelcast.config.FrameworkConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FrameworkConfig.class})
@ExtendWith({SpringExtension.class})
public class BaseApiClient {

    private static final String BASE_URL;

    static {
        String providedUrl = System.getenv("MC_START_URL");
        BASE_URL = providedUrl == null ? "http://localhost:8080" : providedUrl;
    }

    protected RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.URLENC.withCharset("UTF-8"))
                .addFilter(new AllureRestAssured())
                .build();
    }
}
