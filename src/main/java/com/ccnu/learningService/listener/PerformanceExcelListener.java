package com.ccnu.learningService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccnu.learningService.entity.*;
import com.ccnu.learningService.entity.excel.PerformanceData;
import com.ccnu.learningService.handler.exceptionhandler.MyException;
import com.ccnu.learningService.service.CourseService;
import com.ccnu.learningService.service.PerformanceService;
import com.ccnu.learningService.service.ScService;
import com.ccnu.learningService.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PerformanceExcelListener extends AnalysisEventListener<PerformanceData> {

    private String userId;

    private PerformanceService performanceService;

    private ScService scService;

    private UserService userService;

    private CourseService courseService;

    @Override
    public void invoke(PerformanceData performanceData, AnalysisContext analysisContext) throws MyException{

        if (performanceData == null) {
            throw new MyException(20001, "文件数据为空");
        }

        String courseId = courseService.getIdByTitle(performanceData.getCourseTitle());
        String stuId = userService.getIdByCardNum(performanceData.getCardNum());

        if (!existTc(courseId)) {
            throw new MyException(20001, "教师与课程关系无法对应");
        }

        if (!existSc(stuId, courseId)) {
            throw new MyException(20001, "学生与课程关系无法对应");
        }

        // 清空performance表中与该学生所选课程有关的数据
        QueryWrapper<Performance> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", stuId);
        wrapper.eq("course_id", courseId);
        performanceService.remove(wrapper);
        //插入数据
        Performance performance = new Performance();
        performance.setStuId(stuId);
        performance.setCourseId(courseId);
        performance.setAnswerNum(performanceData.getAnswerNum());
        performance.setHomeworkScore(performanceData.getHomeworkScore());
        performance.setPostNum(performanceData.getPostNum());
        performance.setReplyNum(performanceData.getReplyNum());
        performanceService.save(performance);
    }

    private boolean existTc(String courseId) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("id", courseId);
        wrapper.eq("teacher_id", userId);
        return courseService.getOne(wrapper) != null;
    }

    private boolean existSc(String stuId, String courseId) {
        QueryWrapper<Sc> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", stuId);
        wrapper.eq("course_id", courseId);
        return scService.getOne(wrapper) != null;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
