package com.guin.storage.sharding.error;

import com.guin.storage.sharding.error.message.ErrorMessage;

public class ParsingShardingKeyException extends RuntimeException {

    public ParsingShardingKeyException() {
        super(ErrorMessage.PARSING_FORMAT_SHARDING_KEY.getMessage());
    }
}
