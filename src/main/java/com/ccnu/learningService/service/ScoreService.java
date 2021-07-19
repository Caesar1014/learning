package com.ccnu.learningService.service;

import com.ccnu.learningService.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 成绩 服务类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
public interface ScoreService extends IService<Score> {

    void saveScore(MultipartFile file, String userId, Integer type, ScoreService scoreService, ScService scService, UserService userService, CourseService courseService) throws Exception;
}
