package com.ccnu.learningService.service.impl;

import com.ccnu.learningService.entity.Email;
import com.ccnu.learningService.mapper.EmailMapper;
import com.ccnu.learningService.service.EmailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements EmailService {

}
