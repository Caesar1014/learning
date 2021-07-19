package com.ccnu.learningService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccnu.learningService.entity.Performance;
import com.ccnu.learningService.entity.Risk;
import com.ccnu.learningService.handler.exceptionhandler.MyException;
import com.ccnu.learningService.mapper.RiskMapper;
import com.ccnu.learningService.service.RiskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>
 * 风险 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Service
public class RiskServiceImpl extends ServiceImpl<RiskMapper, Risk> implements RiskService {

    @Override
    public Integer getRiskLevelByStuIdAndCourseId(String stuId, String courseId) {
        QueryWrapper<Risk> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", stuId);
        wrapper.eq("course_id", courseId);
        Risk risk = baseMapper.selectOne(wrapper);

        if (StringUtils.isEmpty(risk)) {
            return null;
        } else {
            return risk.getLevel();
        }
    }

    @Override
    public Integer calculateRiskLevel(Performance performance) {

        Integer level = getRiskLevelByStuIdAndCourseId(performance.getStuId(), performance.getCourseId());
        if (level != null) {
            return level;
        }

        double homeworkScore = performance.getHomeworkScore().doubleValue();
        int answerNum = performance.getAnswerNum() > 5 ? 5 : performance.getAnswerNum();
        int postNum = performance.getPostNum() > 5 ? 5 : performance.getPostNum();
        int replyNum = performance.getReplyNum() > 5 ? 5 : performance.getReplyNum();

        double riskScore = homeworkScore * 0.5 + (answerNum * 0.2 + postNum * 0.2 + replyNum * 0.1) * 20;


        if (riskScore >= 90) {
            level = 1;
        } else if (riskScore >= 80) {
            level = 2;
        } else if (riskScore >= 70) {
            level = 3;
        } else if (riskScore >= 60) {
            level = 4;
        } else {
            level = 5;
        }

        Risk risk = new Risk();
        risk.setStuId(performance.getStuId());
        risk.setCourseId(performance.getCourseId());
        risk.setLevel(level);
        baseMapper.insert(risk);

        return level;
    }
}
