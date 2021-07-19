package com.ccnu.learningService.controller;

import com.ccnu.learningService.annotation.JwtToken;
import com.ccnu.learningService.config.SwaggerConfig;
import com.ccnu.learningService.entity.Performance;
import com.ccnu.learningService.entity.User;
import com.ccnu.learningService.service.PerformanceService;
import com.ccnu.learningService.service.RiskService;
import com.ccnu.learningService.service.UserService;
import com.ccnu.learningService.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * <p>
 * 风险 前端控制器
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Api(tags = { SwaggerConfig.TAG_5 })
@RestController
@RequestMapping("/learningService/risk")
@CrossOrigin
public class RiskController {

    @Resource
    private PerformanceService performanceService;

    @Resource
    private RiskService riskService;

    @Resource
    private UserService userService;

    @ApiOperation(value = "班级学生风险分类")
    @GetMapping("classifyStuRisk/{courseId}")
//    @JwtToken
    public Result classifyStuRisk(@PathVariable String courseId) {
        ArrayList<Performance> performances = performanceService.getPerformancesByCourseId(courseId);

        ArrayList<User> lowRiskStu = new ArrayList<User>();
        ArrayList<User> mediumRiskStu = new ArrayList<User>();
        ArrayList<User> highRiskStu = new ArrayList<User>();

        Iterator<Performance> it = performances.iterator();
        while(it.hasNext()){
            Performance performance = it.next();
            int level = riskService.calculateRiskLevel(performance);
            if (level == 1) {
                lowRiskStu.add(userService.getUserById(performance.getStuId()));
            } else if (level == 2 || level == 3) {
                mediumRiskStu.add(userService.getUserById(performance.getStuId()));
            } else {
                highRiskStu.add(userService.getUserById(performance.getStuId()));
            }
        }

        HashMap<String, ArrayList<User>> riskClassification = new HashMap<String, ArrayList<User>>(3);
        riskClassification.put("low risk", lowRiskStu);
        riskClassification.put("medium risk", mediumRiskStu);
        riskClassification.put("high risk", highRiskStu);

        return Result.ok().data("riskClassification", riskClassification);
    }

    @ApiOperation(value = "预测单个学生风险")
    @GetMapping("predictStuRisk/{userId}/{courseId}")
//    @JwtToken
    public Result predictStuRisk(@PathVariable String userId,
                              @PathVariable String courseId) {
        Performance performance = performanceService.getPerformanceByStuIdAndCourseId(userId, courseId);

        Integer level = riskService.calculateRiskLevel(performance);

        return Result.ok().data("riskLevel", level);
    }
}
