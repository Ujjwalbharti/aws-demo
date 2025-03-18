package com.example.awsdemo.cache;

import java.util.List;

public interface RedisService {
    void set(String key, String value, long expirationSeconds);
    String get(String key);
    void delete(String key);
    void setList(String key, List<String> values, long expirationSeconds);
    List<String> getList(String key);
}
