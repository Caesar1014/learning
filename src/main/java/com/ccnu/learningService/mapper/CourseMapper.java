package com.ccnu.learningService.mapper;

import com.ccnu.learningService.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
public interface CourseMapper extends BaseMapper<Course> {

    @Select("select course.* from course INNER JOIN (SELECT course_id from sc where stu_id=#{stuId}) as courseIds on course.id=courseIds.course_id")
    List<Course> listCourceByStuId(Long stuId);


}
