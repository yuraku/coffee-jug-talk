package com.hazelcast.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = "com.hazelcast")
@PropertySource(value = "config/browserstack_general.properties")
public class FrameworkConfig {
}
