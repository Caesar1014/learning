package com.ccnu.learningService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccnu.learningService.entity.Course;
import com.ccnu.learningService.entity.Sc;
import com.ccnu.learningService.entity.Score;
import com.ccnu.learningService.entity.excel.TeacherUploadScoreData;
import com.ccnu.learningService.handler.exceptionhandler.MyException;
import com.ccnu.learningService.service.CourseService;
import com.ccnu.learningService.service.ScService;
import com.ccnu.learningService.service.ScoreService;
import com.ccnu.learningService.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TeacherUploadScoreExcelListener extends AnalysisEventListener<TeacherUploadScoreData> {

    private String userId;

    private ScoreService scoreService;

    private ScService scService;

    private UserService userService;

    private CourseService courseService;

    @Override
    public void invoke(TeacherUploadScoreData teacherUploadScoreData, AnalysisContext analysisContext) throws MyException {

        if (teacherUploadScoreData == null) {
            throw new MyException(20001, "文件数据为空");
        }

        String courseId = courseService.getIdByTitle(teacherUploadScoreData.getCourseTitle());
        String stuId = userService.getIdByCardNum(teacherUploadScoreData.getCardNum());

        if (!existTc(courseId)) {
            throw new MyException(20001, "教师与课程关系无法对应");
        }

        if (!existSc(stuId, courseId)) {
            throw new MyException(20001, "学生与课程关系无法对应");
        }

        // 清空score表中与该学生所选课程有关的数据
        QueryWrapper<Score> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", stuId);
        wrapper.eq("course_id", courseId);
        scoreService.remove(wrapper);
        //插入数据
        Score score = new Score();
        score.setStuId(stuId);
        score.setCourseId(courseId);
        score.setScore(teacherUploadScoreData.getScore());
        scoreService.save(score);
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
