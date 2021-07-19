package com.ccnu.learningService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccnu.learningService.entity.Sc;
import com.ccnu.learningService.entity.Score;
import com.ccnu.learningService.entity.excel.StudentUploadScoreData;
import com.ccnu.learningService.handler.exceptionhandler.MyException;
import com.ccnu.learningService.service.CourseService;
import com.ccnu.learningService.service.ScService;
import com.ccnu.learningService.service.ScoreService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StudentUploadScoreExcelListener extends AnalysisEventListener<StudentUploadScoreData> {
    private String userId;

    private ScoreService scoreService;

    private ScService scService;

    private CourseService courseService;

    @Override
    public void invoke(StudentUploadScoreData studentUpLoadScoreData, AnalysisContext analysisContext) throws MyException {

        if (studentUpLoadScoreData == null) {
            throw new MyException(20001, "文件数据为空");
        }

        String courseId = courseService.getIdByTitle(studentUpLoadScoreData.getCourseTitle());

        if (existSc(courseId)) {
            // 清空score表中与该学生所选课程有关的数据
            QueryWrapper<Score> wrapper = new QueryWrapper<>();
            wrapper.eq("stu_id", userId);
            wrapper.eq("course_id", courseId);
            scoreService.remove(wrapper);
            //插入数据
            Score score = new Score();
            score.setStuId(userId);
            score.setCourseId(courseId);
            score.setScore(studentUpLoadScoreData.getScore());
            scoreService.save(score);
        } else {
            throw new MyException(20001, "学生与课程关系无法对应");
        }
    }

    private boolean existSc(String courseId) {
        QueryWrapper<Sc> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", userId);
        wrapper.eq("course_id", courseId);
        return scService.getOne(wrapper) != null;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
