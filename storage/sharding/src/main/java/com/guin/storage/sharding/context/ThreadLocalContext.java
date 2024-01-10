package com.guin.storage.sharding.context;

public class ThreadLocalContext {

    private static final ThreadLocal<Context> THREAD_LOCAL = new ThreadLocal<>();

    public static Context getContext() {
        if(THREAD_LOCAL.get() == null) {
            Context context = new Context(null);
            THREAD_LOCAL.set(context);
        }
        return THREAD_LOCAL.get();
    }

    public static void setSharding(final String target, long shardKey) {
        getContext().setSharding(new Context.Sharding(target, shardKey));
    }

    public static void setShardingWithShardNo(final int shardNo) {
        Context.Sharding sharding = new Context.Sharding(shardNo);
        getContext().setSharding(sharding);
    }

    public static void clearingSharding() {
        getContext().setSharding(null);
    }

    public static Context.Sharding getSharding() {
        return getContext().getSharding();
    }

}
