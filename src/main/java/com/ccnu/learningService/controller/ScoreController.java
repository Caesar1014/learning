package com.ccnu.learningService.controller;


import com.ccnu.learningService.annotation.JwtToken;
import com.ccnu.learningService.config.SwaggerConfig;
import com.ccnu.learningService.service.CourseService;
import com.ccnu.learningService.service.ScService;
import com.ccnu.learningService.service.ScoreService;
import com.ccnu.learningService.service.UserService;
import com.ccnu.learningService.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 成绩 前端控制器
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Api(tags = { SwaggerConfig.TAG_6 })
@RestController
@RequestMapping("/learningService/score")
@CrossOrigin
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScService scService;

    @ApiOperation(value = "添加成绩")
    @PostMapping("addScore/{userId}/{type}")
//    @JwtToken
    public Result addScore(
            @ApiParam(name = "file", value = "excel文件", required = true)
                    MultipartFile file,
            @PathVariable String userId,
            @PathVariable Integer type) {
        try {
            scoreService.saveScore(file, userId, type, scoreService, scService, userService, courseService);
            return Result.ok();
        } catch (Exception e) {
            return Result.error().message(e.getCause().toString());
        }
    }
}

