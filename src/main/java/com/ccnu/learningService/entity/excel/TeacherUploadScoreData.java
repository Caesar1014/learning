package com.ccnu.learningService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TeacherUploadScoreData {

    @ExcelProperty(index = 0)
    private String cardNum;

    @ExcelProperty(index = 1)
    private String courseTitle;

    @ExcelProperty(index = 2)
    private BigDecimal score;
}
