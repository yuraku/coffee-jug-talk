package com.hazelcast;

import com.hazelcast.actions.ClusterActions;
import com.hazelcast.api.cluster.ClusterManagementApiClient;
import com.hazelcast.harness.TestConstants;
import com.hazelcast.pages.chunks.menu.MenuChunk;
import com.hazelcast.pages.chunks.toolbar.ToolbarChunk;
import com.hazelcast.pages.signin.DefaultSignInPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminLoginTest extends BaseTest {

    @Autowired
    public DefaultSignInPage loginPage;

    @Autowired
    MenuChunk menu;

    @Autowired
    ToolbarChunk toolbar;

    @Autowired
    ClusterManagementApiClient clusterManagementApiClient;

    @Autowired
    ClusterActions clusterActions;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoginTest.class);

    @Test
    @DisplayName("Access available for the user with admin permission")
    @Severity(SeverityLevel.CRITICAL)
    void adminUserPermissions() {

        LOGGER.info("Create new default test cluster");
        clusterManagementApiClient.addDefaultClusterConfig();

        LOGGER.info("Open Management Center 'Login' page");
        open(TestConstants.BASE_URL.concat("/login"));

        LOGGER.info("Login with admin user");
        loginPage.login(TestConstants.DEFAULT_USER, TestConstants.DEFAULT_PASSWORD);

        LOGGER.info("Select cluster configuration and select it");
        clusterActions.selectCluster(TestConstants.DEFAULT_CLUSTER_NAME);

        LOGGER.info("Verify created user has access to admin related menus");
        assertTrue(toolbar.isDisplayed(), "Login has been failed");
        assertTrue(menu.scriptingMenuIsVisible(), "Scripting menu is not available for the user, but should be");
        assertTrue(menu.consoleMenuIsVisible(), "Console menu is not available for the user, but should be");
        assertTrue(menu.administrationMenuIsVisible(), "Administration menu is not available for the user, but should be");
    }

    @AfterEach
    void deleteCluster() {
        clusterManagementApiClient.deleteClusterConfig(TestConstants.DEFAULT_CLUSTER_NAME);
    }
}
