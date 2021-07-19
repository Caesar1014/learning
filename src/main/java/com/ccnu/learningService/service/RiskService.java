package com.ccnu.learningService.service;

import com.ccnu.learningService.entity.Performance;
import com.ccnu.learningService.entity.Risk;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 风险 服务类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
public interface RiskService extends IService<Risk> {

    Integer getRiskLevelByStuIdAndCourseId(String stuId, String courseId);

    Integer calculateRiskLevel(Performance performance);
}
