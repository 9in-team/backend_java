package com.guin.storage.sharding.config;

import com.guin.storage.sharding.property.ShardingProperty;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sharding")
@Setter
public class ShardingPropertyConfig {

    private ShardingProperty property;

}
