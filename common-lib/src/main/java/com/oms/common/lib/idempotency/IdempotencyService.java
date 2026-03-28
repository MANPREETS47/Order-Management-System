package com.oms.common.lib.idempotency;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class IdempotencyService {

    private final StringRedisTemplate redisTemplate;

    public boolean isProcessed(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void markProcessed(String key) {
        redisTemplate.opsForValue().set(key, "PROCESSED");
    }
}