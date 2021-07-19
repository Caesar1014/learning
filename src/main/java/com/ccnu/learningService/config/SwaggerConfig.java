package com.ccnu.learningService.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String TAG_1 = "课程管理";
    public static final String TAG_2 = "邮件管理";
    public static final String TAG_3 = "平时表现管理";
    public static final String TAG_4 = "资源管理";
    public static final String TAG_5 = "风险管理";
    public static final String TAG_6 = "成绩管理";
    public static final String TAG_7 = "用户管理";
    public static final String TAG_8 = "短文本分析";

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }


    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("环境探测助手API文档")
                .description("本文档描述了环境探测助手接口定义")
                .version("1.0")
                .contact(new Contact("gzh", "http://pluto.com", "1641955581@qq.com"))
                .build();
    }
}
