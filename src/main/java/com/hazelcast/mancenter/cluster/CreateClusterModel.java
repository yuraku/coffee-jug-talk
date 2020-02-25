package com.hazelcast.mancenter.cluster;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateClusterModel {
    private String name;
    private Boolean enabled;
    private String [] memberAddresses;
    private String password;
}
