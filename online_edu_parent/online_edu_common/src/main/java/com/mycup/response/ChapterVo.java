package com.mycup.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Garre
 * @create 2020-04-17 14:07
 */
@Data
public class ChapterVo {
    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "小节列表")
    private List<SectionVo> children = new ArrayList<>();
}
