package com.hazelcast.actions;

import com.hazelcast.pages.mancenter.ManageClustersPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClusterActions {

    @Autowired
    ManageClustersPage manageClustersPage;

    public void selectCluster(String clusterName) {
        manageClustersPage.selectClusterByName(clusterName);
    }
}
