package com.guin.storage.sharding.error;

import com.guin.storage.sharding.error.message.ErrorMessage;

public class NotFoundShardingKeyException extends RuntimeException {

    public NotFoundShardingKeyException() {
        super(ErrorMessage.NOT_FOUND_SHARDING_STRATEGY.getMessage());
    }
}
