package com.example.awsdemo.cache;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    private final StringRedisTemplate redisTemplate;

    @Override
    public void set(String key, String value, long expirationSeconds) {
        redisTemplate.opsForValue().set(key, value, expirationSeconds, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void setList(String key, List<String> values, long expirationSeconds) {
        redisTemplate.delete(key);
        redisTemplate.opsForList().rightPushAll(key, values);
        redisTemplate.expire(key, expirationSeconds, TimeUnit.SECONDS);
    }

    @Override
    public List<String> getList(String key) {
        List<String> data = redisTemplate.opsForList().range(key, 0, -1);
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>();
        }
        return data;
    }
}
