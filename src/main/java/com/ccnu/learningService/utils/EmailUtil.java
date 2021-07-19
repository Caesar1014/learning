package com.ccnu.learningService.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

@Component
public class EmailUtil {
    @Resource
    private JavaMailSender javaMail;
    private static JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    public void setParamValue(String value) {
        fromEmail = value;
    }

    public static String fromEmail;

    @PostConstruct
    public void init() {
        javaMailSender = javaMail;
    }
    public static boolean sendSimpleMail(String toUserEmail,String content) {
        try{
            // 构建一个邮件对象
            SimpleMailMessage message = new SimpleMailMessage();
            // 设置邮件主题
            message.setSubject("学习分析系统消息");
            // 设置邮件发送者，这个跟application.yml中设置的要一致
            message.setFrom(fromEmail);
            // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
            message.setTo(toUserEmail);
            // 设置邮件抄送人，可以有多个抄送人
            message.setCc(fromEmail);
            // 设置隐秘抄送人，可以有多个
            message.setBcc(fromEmail);
            // 设置邮件发送日期
            message.setSentDate(new Date());
            // 设置邮件的正文
            message.setText(content);
            // 发送邮件
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println("邮件-系统错误:"+e.getMessage());
            return false;
        }
        return true;
    }
}
