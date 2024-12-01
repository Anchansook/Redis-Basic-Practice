package com.study.redis_practice.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
  @Bean
  public org.springdoc.core.models.GroupedOpenApi publicApi() {
    return org.springdoc.core.models.GroupedOpenApi.builder()
        .group("public")
        .pathsToMatch("/**") // 모든 API 경로를 포함합니다.
        .build();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/swagger-ui/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/");
  }
}
