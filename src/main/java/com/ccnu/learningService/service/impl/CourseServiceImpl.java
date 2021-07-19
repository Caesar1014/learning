package com.ccnu.learningService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccnu.learningService.entity.ClassTime;
import com.ccnu.learningService.entity.Course;
import com.ccnu.learningService.enums.Identity;
import com.ccnu.learningService.handler.exceptionhandler.MyException;
import com.ccnu.learningService.mapper.ClassTimeMapper;
import com.ccnu.learningService.mapper.CourseMapper;
import com.ccnu.learningService.service.CourseService;
import com.ccnu.learningService.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    CourseMapper courseMapper;

    @Resource
    ClassTimeMapper classTimeMapper;

    @Override
    public String getIdByTitle(String title) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        Course course = baseMapper.selectOne(wrapper);

        if (StringUtils.isEmpty(course)) {
            throw new MyException(20001, "未获取到课程");
        }

        return course.getId();
    }

    @Override
    public List<Course> getCourseList(HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        Integer type = JwtUtils.getMemberTypeByJwtToken(request);

        if (type == 1) {

        }
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        return null;

    }

    @Override
    public List<Course> listCoursesByUser(Long id,Integer type){
        List<Course> result = null;
        if(type.equals(Identity.TEACHER.getValue())){
            result = list(new QueryWrapper<Course>().eq("teacher_id",id));
        }else if(type.equals(Identity.STUDENT.getValue())){
            result = courseMapper.listCourceByStuId(id);
        }
        for(Course course:result){
            List<ClassTime> allTime = classTimeMapper.selectList(new QueryWrapper<ClassTime>()
                    .eq("course_id", course.getId()));
            course.setTimes(allTime);
        }
        return result;
    }

    @Override
    public List<Course> getCoursesByTeaId(String id) {
        List<Course> courses = courseMapper.selectList(new QueryWrapper<Course>().eq("teacher_id", id));
        return courses;
    }

}
