package com.guin.storage.sharding.context;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Context {
    private Sharding sharding;

    public Context(final Sharding sharding) {
        this.sharding = sharding;
    }

    @Getter
    public static class Sharding {
        private String target;
        private long shardKey;
        private Integer shardNo;

        public Sharding(final String target,
                        final long shardKey) {
            this.target = target;
            this.shardKey = shardKey;
        }

        public Sharding(final Integer shardNo) {
            this.shardNo = shardNo;
        }
    }

}
