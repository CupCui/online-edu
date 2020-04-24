package com.mycup.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Garre
 * @create 2020-04-17 14:08
 */
@Data
public class SectionVo {
    @ApiModelProperty(value = "小节ID")
    private String id;

    @ApiModelProperty(value = "节点名称")
    private String title;
}
