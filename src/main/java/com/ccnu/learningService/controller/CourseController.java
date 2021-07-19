package com.ccnu.learningService.controller;


import com.ccnu.learningService.config.SwaggerConfig;
import com.ccnu.learningService.entity.Course;
import com.ccnu.learningService.service.CourseService;
import com.ccnu.learningService.utils.JwtUtils;
import com.ccnu.learningService.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Api(tags = { SwaggerConfig.TAG_1 })
@RestController
@RequestMapping("/learningService/course")
@CrossOrigin
public class CourseController {

    @Resource
    CourseService courseService;
    @GetMapping("/get_classes/{id}/{type}")
    public Result listCourses(@PathVariable Long id, @PathVariable Integer type){
        List<Course> result = courseService.listCoursesByUser(id, type);
        return Result.ok().data("courses",result);
    }

    @GetMapping("/get_by_teacher_id/{id}")
    public Result getCourseBuTeaId(@PathVariable String id){
        List<Course> result = courseService.getCoursesByTeaId(id);
        return Result.ok().data("courses",result);
    }
}

