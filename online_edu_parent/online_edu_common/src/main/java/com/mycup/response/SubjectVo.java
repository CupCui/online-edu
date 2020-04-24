package com.mycup.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Garre
 * @create 2020-04-14 15:30
 */
@Data
public class SubjectVo {
    @ApiModelProperty(value = "课程类别id")
    private String id;
    @ApiModelProperty(value = "课程名称")
    private String title;
    @ApiModelProperty(value = "子课程课程")
    private List<SubjectVo> children = new ArrayList<>();
}
