package com.guin.storage.sharding.property;

import java.util.List;

public record ShardingDataSourceProperty(
    List<Shard> shards
) {

    public record Shard(
            DataSourceProperty master,
            DataSourceProperty slave
    ) {
    }

    public record DataSourceProperty(
            String jdbcUrl,
            String driverClassName,
            String username,
            String password,
            String name
    ) {
    }

}
