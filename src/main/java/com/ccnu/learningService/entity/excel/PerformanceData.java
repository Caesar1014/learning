package com.ccnu.learningService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PerformanceData {

    @ExcelProperty(index = 0)
    private String cardNum;

    @ExcelProperty(index = 1)
    private String courseTitle;

    @ExcelProperty(index = 2)
    private BigDecimal homeworkScore;

    @ExcelProperty(index = 3)
    private Integer answerNum;

    @ExcelProperty(index = 4)
    private Integer postNum;

    @ExcelProperty(index = 5)
    private Integer replyNum;
}
