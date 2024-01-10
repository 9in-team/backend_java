package com.guin.storage.sharding.config;

import com.guin.storage.sharding.property.ShardingProperty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShardingConfig {

    private static Map<String, ShardingProperty> shardingPropertyMap = new ConcurrentHashMap<>();

    public static Map<String, ShardingProperty> getShardingPropertyMap() {
        return shardingPropertyMap;
    }

}
