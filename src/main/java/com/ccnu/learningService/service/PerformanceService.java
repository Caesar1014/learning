package com.ccnu.learningService.service;

import com.ccnu.learningService.entity.Performance;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 * <p>
 * 日常表现 服务类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
public interface PerformanceService extends IService<Performance> {

    void savePerformance(MultipartFile file, String userId, Integer type, PerformanceService performanceService, ScService scService, UserService userService, CourseService courseService) throws Exception;

    ArrayList<Performance> getPerformancesByCourseId(String courseId);

    Performance getPerformanceByStuIdAndCourseId(String stuId, String courseId);
}
