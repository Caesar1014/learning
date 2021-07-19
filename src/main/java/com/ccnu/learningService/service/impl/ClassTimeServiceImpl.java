package com.ccnu.learningService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccnu.learningService.entity.ClassTime;
import com.ccnu.learningService.mapper.ClassTimeMapper;
import com.ccnu.learningService.service.ClassTimeService;
import org.springframework.stereotype.Service;

/**
 * 这是课程上课时间表
 */
@Service
public class ClassTimeServiceImpl extends ServiceImpl<ClassTimeMapper, ClassTime> implements ClassTimeService {
}
