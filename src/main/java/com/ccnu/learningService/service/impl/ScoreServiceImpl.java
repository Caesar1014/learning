package com.ccnu.learningService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.ccnu.learningService.entity.Score;
import com.ccnu.learningService.entity.excel.PerformanceData;
import com.ccnu.learningService.entity.excel.StudentUploadScoreData;
import com.ccnu.learningService.entity.excel.TeacherUploadScoreData;
import com.ccnu.learningService.enums.Identity;
import com.ccnu.learningService.listener.StudentUploadScoreExcelListener;
import com.ccnu.learningService.listener.TeacherUploadScoreExcelListener;
import com.ccnu.learningService.mapper.ScoreMapper;
import com.ccnu.learningService.service.CourseService;
import com.ccnu.learningService.service.ScService;
import com.ccnu.learningService.service.ScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccnu.learningService.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 成绩 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveScore(MultipartFile file, String userId, Integer type, ScoreService scoreService, ScService scService, UserService userService, CourseService courseService) throws Exception {
        InputStream in = file.getInputStream();
        if (type.equals(Identity.TEACHER.getValue())) {
            EasyExcel.read(in, TeacherUploadScoreData.class, new TeacherUploadScoreExcelListener(userId, scoreService, scService, userService, courseService)).sheet().doRead();
        } else if (type.equals(Identity.STUDENT.getValue())) {
            EasyExcel.read(in, StudentUploadScoreData.class, new StudentUploadScoreExcelListener(userId, scoreService, scService, courseService)).sheet().doRead();
        }
    }
}
