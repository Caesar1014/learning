package com.ccnu.learningService.controller;


import com.ccnu.learningService.annotation.JwtToken;
import com.ccnu.learningService.config.SwaggerConfig;
import com.ccnu.learningService.entity.Resource;
import com.ccnu.learningService.service.ResourceService;
import com.ccnu.learningService.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Api(tags = { SwaggerConfig.TAG_4 })
@RestController
@RequestMapping("/learningService/resource")
@CrossOrigin
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @ApiOperation(value = "添加资源")
    @PostMapping("addResource/{courseId}")
//    @JwtToken
    public Result addResource(@RequestBody String content, @PathVariable String courseId) {
            resourceService.saveResource(content, courseId);
            return Result.ok();
    }

    @ApiOperation(value = "获取资源")
    @GetMapping("getResource/{courseId}")
//    @JwtToken
    public Result getResource(@PathVariable String courseId) {
        List<Resource> resource = resourceService.getResourceByCourseId(courseId);
        return Result.ok().data("resource", resource);
    }
}

