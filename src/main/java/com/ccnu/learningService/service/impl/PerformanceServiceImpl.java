package com.ccnu.learningService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccnu.learningService.entity.Performance;
import com.ccnu.learningService.entity.excel.PerformanceData;
import com.ccnu.learningService.enums.Identity;
import com.ccnu.learningService.handler.exceptionhandler.MyException;
import com.ccnu.learningService.listener.PerformanceExcelListener;
import com.ccnu.learningService.mapper.PerformanceMapper;
import com.ccnu.learningService.service.CourseService;
import com.ccnu.learningService.service.PerformanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccnu.learningService.service.ScService;
import com.ccnu.learningService.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * <p>
 * 日常表现 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Service
public class PerformanceServiceImpl extends ServiceImpl<PerformanceMapper, Performance> implements PerformanceService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePerformance(MultipartFile file, String userId, Integer type,  PerformanceService performanceService, ScService scService, UserService userService, CourseService courseService) throws Exception {
        InputStream in = file.getInputStream();
        if (type.equals(Identity.TEACHER.getValue())) {
            EasyExcel.read(in, PerformanceData.class, new PerformanceExcelListener(userId, performanceService, scService, userService, courseService)).sheet().doRead();
        } else {
            throw new MyException(20001, "非教师无此权限");
        }
    }

    @Override
    public ArrayList<Performance> getPerformancesByCourseId(String courseId) {
        QueryWrapper<Performance> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        ArrayList<Performance> performances = (ArrayList<Performance>)baseMapper.selectList(wrapper);

        if (StringUtils.isEmpty(performances)) {
            throw new MyException(20001, "未获取到日常表现");
        }

        return performances;
    }

    @Override
    public Performance getPerformanceByStuIdAndCourseId(String stuId, String courseId) {
        QueryWrapper<Performance> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", stuId);
        wrapper.eq("course_id", courseId);
        Performance performance = baseMapper.selectOne(wrapper);

        if (StringUtils.isEmpty(performance)) {
            throw new MyException(20001, "未获取到日常表现");
        }

        return performance;
    }
}
