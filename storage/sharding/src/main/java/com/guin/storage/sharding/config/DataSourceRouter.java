package com.guin.storage.sharding.config;

import com.guin.storage.sharding.constant.ShardingStrategy;
import com.guin.storage.sharding.context.Context;
import com.guin.storage.sharding.context.ThreadLocalContext;
import com.guin.storage.sharding.error.InvalidShardKeyException;
import com.guin.storage.sharding.error.NotFoundShardingStrategyException;
import com.guin.storage.sharding.property.ShardingProperty;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DataSourceRouter extends AbstractRoutingDataSource {

    private static final String MASTER = "master";
    private static final String SHARD_DELIMITER = "|";

    private Map<Integer, MhaDataSource> shards;

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);

        shards = new HashMap<>();

        targetDataSources.keySet()
                .stream()
                .map(String::valueOf)
                .forEach(dataSourceName -> {
                    String shardNoStr = dataSourceName.split(SHARD_DELIMITER)[0];
                    MhaDataSource shard = getShard(shardNoStr);

                    Consumer<String> dataSource = dataSourceName.contains(MASTER) ? shard::setMasterName : shard::setSlaveName;
                    dataSource.accept(dataSourceName);
                });
    }

    private MhaDataSource getShard(final String shardNoStr) {
        int shardNo = parseShardNumber(shardNoStr);

        return shards.computeIfAbsent(shardNo, k -> new MhaDataSource());
    }

    private int parseShardNumber(final String shardNoStr) {
        return isNumeric(shardNoStr) ? Integer.parseInt(shardNoStr) : 0;
    }

    private boolean isNumeric(final String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Context.Sharding sharding = ThreadLocalContext.getSharding();
        int shardNo = getShardNo(sharding);
        MhaDataSource dataSource = shards.get(shardNo);
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ?
                dataSource.getSlaveName() :
                dataSource.getMasterName();
    }

    private int getShardNo(Context.Sharding sharding) {
        if(sharding == null) {
            return 0;
        }

        if(sharding.getShardNo() != null) {
            return sharding.getShardNo();
        }

        ShardingProperty shardingProperty = ShardingConfig.getShardingPropertyMap().get(sharding.getTarget());

        if(shardingProperty.shardingStrategy() == ShardingStrategy.MODULER) {
            return getShardNoByModuler(sharding.getShardKey(), shardingProperty.mod());
        }

        if(shardingProperty.shardingStrategy() == ShardingStrategy.RANGE) {
            return getShardNoByRange(sharding.getShardKey(), shardingProperty.rules());
        }

        throw new NotFoundShardingStrategyException();
    }

    private int getShardNoByRange(final long shardKey, final List<ShardingProperty.ShardingRule> rules) {
        return rules.stream()
                .filter(rule -> rule.rangeMin() <= shardKey &&
                                rule.rangeMax() >= shardKey )
                .findFirst()
                .orElseThrow(() -> new InvalidShardKeyException())
                .shardNo();
    }

    private int getShardNoByModuler(final long shardKey, final int modules) {
        return (int) shardKey % modules;
    }

}
