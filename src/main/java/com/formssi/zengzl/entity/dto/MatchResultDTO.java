package com.formssi.zengzl.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchResultDTO {

    @ApiModelProperty("用户ID")
    @NotNull(message = "userId不为空")
    private Long userId;

    @ApiModelProperty("赛事ID")
    @NotNull(message = "赛事ID不为空")
    private Long matchId;

    @ApiModelProperty("成绩")
    @NotNull(message = "score不为空")
    private BigDecimal score;

}
