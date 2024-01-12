package com.guin.storage.sharding.aop;

import com.guin.storage.sharding.annotation.Sharding;
import com.guin.storage.sharding.annotation.ShardingKey;
import com.guin.storage.sharding.config.DataSourceRouter;
import com.guin.storage.sharding.context.ThreadLocalContext;
import com.guin.storage.sharding.error.NotFoundShardingKeyException;
import com.guin.storage.sharding.error.ParsingShardingKeyException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class ShardingAspect {

    private final DataSourceRouter dataSourceRouter;

    @Around("@annotation(sharding)")
    public Object handler(ProceedingJoinPoint joinPoint, Sharding sharding) throws Throwable {
        Long shardingKey = getShardingKey(joinPoint.getArgs());
        ThreadLocalContext.setSharding(sharding.target(), shardingKey);
        Object returnVal = joinPoint.proceed();
        ThreadLocalContext.clearingSharding();
        return returnVal;
    }

    private Long getShardingKey(final Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg != null && arg.getClass().isAnnotationPresent(ShardingKey.class))
                .findFirst()
                .map(arg -> parseInteger(arg.toString()))
                .orElseThrow(() -> new NotFoundShardingKeyException());
    }

    private Long parseInteger(final String arg) {
        try {
            return Long.valueOf(arg);
        } catch (NumberFormatException e) {
            throw new ParsingShardingKeyException();
        }
    }

}
