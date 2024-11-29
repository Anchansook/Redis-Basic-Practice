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
@RequestMapping("/api/v1/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {
  private final LeaderboardService leaderboardService;

  @PostMapping("/scores")
  public ResponseEntity<?> addScore(
      @RequestParam String userId,
      @RequestParam double score
  ) {
    leaderboardService.addScore(userId, score);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/top/{count}")
  public ResponseEntity<?> getTopPlayers(
      @PathVariable int count
  ) {
    return ResponseEntity.ok(leaderboardService.getTopPlayers(count));
  }

  @GetMapping("/rank/{userId}")
  public ResponseEntity<?> getUserRank(
      @PathVariable String userId
  ) {
    Long userRank = leaderboardService.getUserRank(userId);
    return userRank != null ? ResponseEntity.ok(userRank + 1) : ResponseEntity.notFound().build();
  }

}
