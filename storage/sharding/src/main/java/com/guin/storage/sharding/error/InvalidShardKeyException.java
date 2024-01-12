package com.guin.storage.sharding.error;

import com.guin.storage.sharding.error.message.ErrorMessage;

public class InvalidShardKeyException extends RuntimeException{

    public InvalidShardKeyException() {
        super(ErrorMessage.INVALID_SHARDING_KEY.getMessage());
    }
}
