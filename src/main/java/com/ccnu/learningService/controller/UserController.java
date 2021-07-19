package com.ccnu.learningService.controller;

import com.ccnu.learningService.config.SwaggerConfig;
import com.ccnu.learningService.entity.User;
import com.ccnu.learningService.service.UserService;
import com.ccnu.learningService.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Api(tags = { SwaggerConfig.TAG_7 })
@RestController
@RequestMapping("/learningService/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public Result register(
            @ApiParam(name = "user", value = "用户信息", required = true)
            @RequestBody User user) {
        userService.register(user);
        return Result.ok();
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public Result login(
            @ApiParam(name = "user", value = "用户信息", required = true)
            @RequestBody User user) {
        User endUser = userService.login(user);
        return Result.ok().data("user", endUser);
    }

}

