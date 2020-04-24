package com.mycup.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Garre
 * @create 2020-04-17 9:10
 */
@Data
public class QueryCourse {
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "视频状态 Draft未发布  Normal已发布")
    private String status;
}
