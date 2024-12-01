package com.study.redis_practice.domain.user.controller;

import com.study.redis_practice.domain.service.CacheService;
import com.study.redis_practice.domain.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 테스트용 및 연습용 유저 컨트롤러

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
  private final CacheService cacheService;

  // 유저 생성
  @Operation(summary = "유저 생성", description = "새로운 유저를 생성합니다.")
  @PostMapping
  public ResponseEntity<?> createUser(
      @RequestBody User user
  ) {
      user.setCreatedAt(LocalDateTime.now());
      cacheService.cacheData("user:" + user.getId(), user, 3600);
      return ResponseEntity.ok(user);
  }

  // 유저 조회
  @Operation(summary = "유저 조회", description = "경로에 해당되는 유저를 조회합니다.")
  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(
      @PathVariable Long id
  ) {
    return cacheService.getCachedData("user:" + id, User.class)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

}
