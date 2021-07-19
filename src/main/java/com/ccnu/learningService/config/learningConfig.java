package com.ccnu.learningService.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ccnu.learningService.mapper")
public class learningConfig {
}
