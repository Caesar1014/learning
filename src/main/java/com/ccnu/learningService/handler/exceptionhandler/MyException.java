package com.ccnu.learningService.handler.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor //生成有参构造
@NoArgsConstructor  //生成无参构造
public class MyException extends RuntimeException {

    private Integer code;   //状态码
    private String msg;     //异常信息
}