package com.study.redis_practice.domain.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 원래 Data는 쓰면 안됨
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
  private Long id;
  private String username;
  private String email;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;
}
