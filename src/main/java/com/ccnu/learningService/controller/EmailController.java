package com.ccnu.learningService.controller;


import com.ccnu.learningService.config.SwaggerConfig;
import com.ccnu.learningService.entity.Email;
import com.ccnu.learningService.entity.User;
import com.ccnu.learningService.service.EmailService;
import com.ccnu.learningService.service.UserService;
import com.ccnu.learningService.utils.EmailUtil;
import com.ccnu.learningService.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 邮件 前端控制器
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Api(tags = { SwaggerConfig.TAG_2 })
@RestController
@RequestMapping("/learningService/email")
@CrossOrigin
public class EmailController {

    @Resource
    UserService userService;

    @Resource
    EmailService emailService;

    @PostMapping("/send")
    public Result sendMail(@RequestBody Email email){
        User user = userService.getById(email.getStuId());
        if(EmailUtil.sendSimpleMail(user.getEmail(),email.getContent())){
            emailService.saveOrUpdate(email);
        }else{
            return Result.ok().message("发送失败，请检查接收者邮箱是否正确");
        }
        return Result.ok().message("发送成功");
    }
}

