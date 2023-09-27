package com.formssi.zengzl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@Data
@ApiModel("比赛")
@TableName("match_item")
public class MatchItem {

    @ApiModelProperty("比赛ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("比赛名称")
    private String matchName;

    @ApiModelProperty("报名开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyStartTime;

    @ApiModelProperty("报名结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyEndTime;

    @ApiModelProperty("排名规则1升序2降序")
    private Integer rankRule;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

}
