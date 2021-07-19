package com.ccnu.learningService.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日常表现
 * </p>
 *
 * @author gzh
 * @since 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Performance对象", description="日常表现")
public class Performance implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日常表现ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "学生ID")
    private String stuId;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "作业成绩")
    private BigDecimal homeworkScore;

    @ApiModelProperty(value = "回答问题数量")
    private Integer answerNum;

    @ApiModelProperty(value = "发帖数量")
    private Integer postNum;

    @ApiModelProperty(value = "回帖数量")
    private Integer replyNum;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
