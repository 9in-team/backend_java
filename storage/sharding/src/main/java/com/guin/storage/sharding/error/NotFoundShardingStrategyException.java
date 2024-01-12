package com.guin.storage.sharding.error;

import com.guin.storage.sharding.error.message.ErrorMessage;

public class NotFoundShardingStrategyException extends RuntimeException {

    public NotFoundShardingStrategyException() {
        super(ErrorMessage.NOT_FOUND_SHARDING_STRATEGY.getMessage());
    }
}
