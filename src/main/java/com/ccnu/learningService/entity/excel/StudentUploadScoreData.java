package com.ccnu.learningService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StudentUploadScoreData {

    @ExcelProperty(index = 0)
    private String courseTitle;

    @ExcelProperty(index = 1)
    private BigDecimal score;
}
