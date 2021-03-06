package com.mycup.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Garre
 * @create 2020-04-18 9:32
 */
@Data
public class CourseConfirmVo {
    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "课程讲师")
    private String teacherName;

    @ApiModelProperty(value = "二级分类")
    private String childSubject;

    @ApiModelProperty(value = "课程标题")
    private String courseName;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "一级分类")
    private String parentSubject;
}
