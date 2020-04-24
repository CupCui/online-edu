package com.mycup.edu.handler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Garre
 * @create 2020-04-11 9:42
 */

// extends Exception        // 如果有事务是不能回滚的
// extends RuntimeException        // 如果有事务可以回滚
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "自定义异常类")
public class EduException extends RuntimeException {
    @ApiModelProperty(value = "异常状态码")
    private Integer code;
    @ApiModelProperty(value = "异常信息")
    private String message;

}
