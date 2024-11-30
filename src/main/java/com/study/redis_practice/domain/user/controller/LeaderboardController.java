package com.study.redis_practice.domain.user.controller;

import com.study.redis_practice.domain.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 테스트용 및 연습용 유저 컨트롤러

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {
  private final LeaderboardService leaderboardService;

  // 유저 점수 추가
  @PostMapping("/scores")
  public ResponseEntity<?> addScore(
      @RequestParam String userId,
      @RequestParam double score
  ) {
    leaderboardService.addScore(userId, score);
    return ResponseEntity.ok().build();
  }

  // 상위 플레이어 조회
  @GetMapping("/top/{count}")
  public ResponseEntity<?> getTopPlayers(
      @PathVariable int count
  ) {
    return ResponseEntity.ok(leaderboardService.getTopPlayers(count));
  }

  // 유저 순위 조회
  @GetMapping("/rank/{userId}")
  public ResponseEntity<?> getUserRank(
      @PathVariable String userId
  ) {
    Long userRank = leaderboardService.getUserRank(userId);
    return userRank != null ? ResponseEntity.ok(userRank + 1) : ResponseEntity.notFound().build();
  }

  // 유저 점수 조회
  @GetMapping("/scores/{userId}")
  public ResponseEntity<?> getUserScore(
      @PathVariable String userId
  ) {
    Double userScore = leaderboardService.getUserScore(userId);
    return userScore != null ? ResponseEntity.ok(userScore) : ResponseEntity.notFound().build();
  }

}
