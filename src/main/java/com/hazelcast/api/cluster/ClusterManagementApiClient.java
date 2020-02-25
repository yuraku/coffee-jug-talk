package com.hazelcast.api.cluster;

import com.hazelcast.api.BaseApiClient;
import com.hazelcast.harness.TestConstants;
import com.hazelcast.mancenter.cluster.CreateClusterModel;
import io.restassured.http.ContentType;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

@Component
public class ClusterManagementApiClient extends BaseApiClient {

    /**
     * Add default config for IMDG cluster.
     */
    public void addDefaultClusterConfig() {
        given(requestSpecification())
                .basePath("api/clusters")
                .contentType(ContentType.JSON)
                .auth()
                .preemptive()
                .basic(TestConstants.DEFAULT_USER, TestConstants.DEFAULT_PASSWORD)
                .body(getAddClusterConfigBody())
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    /**
     * Delete cluster config from Management Center.
     *
     * @param clusterName String. Name of the cluster to be deleted
     */
    public void deleteClusterConfig(String clusterName) {
        given(requestSpecification())
                .basePath(String.format("api/clusters/%s", clusterName))
                .auth()
                .preemptive()
                .basic(TestConstants.DEFAULT_USER, TestConstants.DEFAULT_PASSWORD)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK);
    }

    private CreateClusterModel getAddClusterConfigBody() {
        return CreateClusterModel
                .builder()
                .name(TestConstants.DEFAULT_CLUSTER_NAME)
                .enabled(true)
                .memberAddresses(new String[]{TestConstants.MEMBER})
                .password(null)
                .build();
    }
}
