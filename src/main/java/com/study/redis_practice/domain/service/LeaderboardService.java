package com.study.redis_practice.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

// 순위, 랭크 관리하는 서비스
// + 해당 유저의 점수 불러오기 만들어 보기

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaderboardService {

  private final RedisTemplate<String, String> redisTemplate;
  private static final String LEADERBOARD_KEY = "game:leaderboard";

  // 유저 점수 추가
  public void addScore(String userId, double score) {
    redisTemplate.opsForZSet().add(LEADERBOARD_KEY, userId, score);
    log.info("Score added for user: {} with scores: {}", userId, score);
  }

  // 상위 플레이어 조회
  public List<String> getTopPlayers(int count) {
    Set<String> topScores = redisTemplate.opsForZSet()
        .reverseRange(LEADERBOARD_KEY, 0, count - 1);
    return new ArrayList<>(topScores != null ? topScores : Collections.emptySet());
  }

  // 유저 순위 조회
  public Long getUserRank(String userId) {
    return redisTemplate.opsForZSet().reverseRank(LEADERBOARD_KEY, userId);
  }

  // 유저 점수 조회
  public Double getUserScore(String userId) {
    try {
      Double score = redisTemplate.opsForZSet().score(LEADERBOARD_KEY, userId);
      return score;

    } catch (Exception exception) {
      log.error("Error fetching score for user: {}", exception.getMessage());
      throw new RuntimeException("Failed to get user score", exception);
    }
  }

}
