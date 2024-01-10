package com.guin.storage.sharding.property;

import com.guin.storage.sharding.constant.ShardingStrategy;

import java.util.List;

public record ShardingProperty(
        ShardingStrategy shardingStrategy,
        List<ShardingRule> rules,
        int mod
) {

    public record ShardingRule(
            int shardNo,
            Long rangeMin,
            Long rangeMax
    ) {
    }

}
