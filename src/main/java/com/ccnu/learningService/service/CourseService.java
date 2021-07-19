package com.ccnu.learningService.service;

import com.ccnu.learningService.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
public interface CourseService extends IService<Course> {

    String getIdByTitle(String title);

    List<Course> getCourseList(HttpServletRequest request);

    List<Course> listCoursesByUser(Long id,Integer type);

    List<Course> getCoursesByTeaId(String id);
}
