package com.mycup.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Garre
 * @create 2020-04-10 16:25
 */
@Data
public class TeacherCondition {
    private String name;
    private Integer level;
    private String beginTime;
    private String endTime;
}
