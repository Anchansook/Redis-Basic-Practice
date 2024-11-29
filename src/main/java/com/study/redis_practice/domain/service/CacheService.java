package com.study.redis_practice.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

// Redis 캐싱
// + Delete도 만들어 보기

@Service
@Slf4j
@RequiredArgsConstructor
public class CacheService {
  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;

  public void cacheData(String key, Object data, long timeoutSeconds) {
    try {
      redisTemplate.opsForValue().set(key, data, timeoutSeconds, TimeUnit.SECONDS);
      log.info("data cached successfully for key: {}", key);
    } catch (Exception exception) {
      log.error("Error caching data: {}", exception.getMessage());
      throw new RuntimeException("Cache operation failed", exception);
    }
  }

  public <T> Optional<T> getCachedData(String key, Class<T> type) {
    try {
      Object data = redisTemplate.opsForValue().get(key);
      if (data == null) return Optional.empty();

      return Optional.of(objectMapper.convertValue(data, type));
    } catch (Exception exception) {
      return Optional.empty();
    }
  }

}
